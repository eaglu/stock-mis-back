package com.qfedu.service.impl

import com.qfedu.entity.*
import com.qfedu.mapper.DetailMapper
import com.qfedu.mapper.GoodsRepositoryMapper
import com.qfedu.mapper.InBillMapper
import com.qfedu.mapper.RepoMapper
import com.qfedu.service.InBillService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.mybatis.mapper.entity.Example
import java.util.*

@Service
class InBillServiceImpl : InBillService {
    @Autowired
    private lateinit var inBillMapper : InBillMapper
    @Autowired
    private lateinit var goodsRepositoryMapper : GoodsRepositoryMapper
    @Autowired
    private lateinit var detailMapper : DetailMapper
    @Autowired
    private lateinit var repoMapper : RepoMapper

    override fun list(queryCondition: InBillSearch): List<InBill>? {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        val example = Example(InBill::class.java)
        val criteria =example.createCriteria()
        //由于前端页面的仓库设置为下拉栏，故获得的仓库id为精确值
        if(queryCondition.galleryId != null){
            criteria.andEqualTo("galleryId",queryCondition.galleryId)
        }

        if(StringUtils.isBlank(queryCondition.billId)){
            queryCondition.billId = null
        }else{
            criteria.andLike("billId","%${queryCondition.billId}%")
        }
        if(StringUtils.isBlank(queryCondition.username)){
            queryCondition.username = null
        }else{
            criteria.andLike("username","%${queryCondition.username}%")
        }
        if(StringUtils.isBlank(queryCondition.userCompany)){
            queryCondition.userCompany = null
        }else{
            criteria.andLike("userCompany","%${queryCondition.userCompany}%")
        }
        if (queryCondition.startDate != null && queryCondition.endDate != null) {
            criteria.andBetween("date", queryCondition.startDate,queryCondition.endDate);
        }
        //此处使用andLessThan是为了实现对已删除数据的查找，由于lessThan为小于，故需要+1以避免选择区间不符合预期
        criteria.andLessThan("deleted", queryCondition.deleted+1)
        return inBillMapper.selectByExample(example)
    }

    override fun add(entity: InBill?): Int {
        //设置插入时间
        entity!!.date = Date()
        //设置删除标记，默认为0，代表未删除
        entity.deleted = 0
        return inBillMapper.insert(entity as InBill?)
    }

    override fun edit(entites: List<InBill?>): Int {
        var inBillOld = entites[1]
        var inBillNew = entites[0]
        //判断是否更换了仓库，若未更换，则可以直接更新入库单基本表
        if(inBillOld!!.galleryId == inBillNew!!.galleryId) {
            return inBillMapper.updateByPrimaryKeySelective(inBillNew)
        }else{
            //首先获取与更改前的基本表相对应的明细信息队列
            val queryCondition = DetailSearch()
            queryCondition.billId = inBillOld.billId;
            queryCondition.deleted = 0;
            val detailList = detailMapper.select(queryCondition)
            //该变量用于明细内商品总的数量
            var sum = 0L
            //此处为lambda表达式。
            detailList!!.forEach {
                sum += it.amount
            }
            //查询转入仓库的信息
            var queryConditionRepo = Repo()
            queryConditionRepo.id = inBillNew.galleryId
            val inventory = repoMapper.findRepoById(queryConditionRepo).inventory;
            //若商品总数量大于转入仓库库存，返回-1，表示操作不合法
            if(sum > inventory){
                return  -1
            }else {
                //该变量统计受影响的行数
                var rsCount = 0
                val oldRepoId = inBillOld.galleryId
                val newRepoId = inBillNew.galleryId
                //更新入库单基本表
                rsCount += inBillMapper.updateByPrimaryKeySelective(inBillNew)
                //遍历明细列表
                detailList.forEach{
                    //获取当前明细的商品与转出仓库的信息
                    var goodsRepo = GoodsRepository()
                    goodsRepo.goodsId = it.goodsId
                    goodsRepo.repositoryId = oldRepoId
                    var entity = goodsRepositoryMapper.selectByPrimaryKey(goodsRepo)
                    //由于是移出，所以需要减少数量，it为当前的明细信息对象
                    entity.amount -= it.amount
                    //更新关联表信息
                    rsCount += goodsRepositoryMapper.updateByPrimaryKeySelective(entity)
                    //获取当前明细的商品与转入仓库的信息
                    goodsRepo.repositoryId = newRepoId
                    var goodsRepoTmp = goodsRepositoryMapper.selectByPrimaryKey(goodsRepo)
                    //首先需要判断转入仓库内是否已有该商品
                    if (goodsRepoTmp != null) {
                        //若已存在，对该条记录的amount属性进行更新
                        goodsRepoTmp.amount += it.amount
                        rsCount += goodsRepositoryMapper.updateByPrimaryKeySelective(goodsRepoTmp)
                    } else { //不存在，插入新的记录
                        rsCount += goodsRepositoryMapper.insert(goodsRepo)
                    }
                }
                return rsCount
            }
        }
    }

    override fun delete(entity: InBill?): Int {
        entity!!.deleted = 1
        return inBillMapper.updateByPrimaryKeySelective(entity)
    }

    override fun recover(entity: InBill?): Int {
        entity!!.deleted = 0
        return inBillMapper.updateByPrimaryKeySelective(entity)
    }
}
