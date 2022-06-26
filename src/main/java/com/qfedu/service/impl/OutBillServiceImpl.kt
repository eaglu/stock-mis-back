package com.qfedu.service.impl

import com.qfedu.entity.*
import com.qfedu.mapper.*
import com.qfedu.service.OutBillService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.mybatis.mapper.entity.Example
import java.util.*

@Service
class OutBillServiceImpl : OutBillService{
    @Autowired
    private lateinit var outBillMapper : OutBillMapper
    @Autowired
    private lateinit var goodsRepositoryMapper : GoodsRepositoryMapper
    @Autowired
    private lateinit var detailMapper : DetailMapper
    @Autowired
    private lateinit var repoMapper : RepoMapper

    override fun list(queryCondition: OutBillSearch): List<OutBill>? {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        println("BillId=${queryCondition.billId}")
        val example = Example(InBill::class.java)
        val criteria =example.createCriteria()
        if(queryCondition.galleryId != null){
            criteria.andEqualTo("galleryId",queryCondition.galleryId)
        }
        if(queryCondition.operatorId != null){
            criteria.andEqualTo("operatorId",queryCondition.operatorId)
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
        criteria.andLessThan("deleted", queryCondition.deleted+1)
        return outBillMapper.selectByExample(example)
    }

    override fun add(entity: OutBill?): Int {
        entity!!.date = Date()
        entity.deleted = 0
        return outBillMapper.insert(entity)
    }

    override fun delete(entity: OutBill?): Int {
        return outBillMapper.updateByPrimaryKeySelective(entity)
    }

    override fun edit(entites: List<OutBill?>): Int {
        var outBillOld = entites[1]
        var outBillNew = entites[0]
        if(outBillOld!!.galleryId == outBillNew!!.galleryId) {
            return outBillMapper.updateByPrimaryKeySelective(outBillNew)
        }else{
            val queryCondition = DetailSearch()
            queryCondition.billId = outBillOld.billId;
            queryCondition.deleted = 0;
            val detailList = detailMapper.select(queryCondition)
            var sum = 0L
            detailList!!.forEach {
                sum += it.amount
            }
            var queryConditionRepo = Repo()
            queryConditionRepo.id = outBillNew.galleryId
            val inventory = repoMapper.findRepoById(queryConditionRepo).inventory;
                var rsCount = 0
                val goodsRepoServiceImpl = GoodsRepositoryServiceImpl()
                val oldRepoId = outBillOld.galleryId
                val newRepoId = outBillNew.galleryId
                rsCount += outBillMapper.updateByPrimaryKeySelective(outBillNew)
                detailList.forEach{
                    var goodsRepo = GoodsRepository()
                    goodsRepo.goodsId = it.goodsId
                    goodsRepo.repositoryId = oldRepoId
                    var entity = goodsRepositoryMapper.selectByPrimaryKey(goodsRepo)
                    entity.amount -= it.amount
                    rsCount += goodsRepositoryMapper.updateByPrimaryKeySelective(entity)
                    goodsRepo.repositoryId = newRepoId
                    var goodsRepoTmp = goodsRepositoryMapper.selectByPrimaryKey(goodsRepo)
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

    override fun recover(entity: OutBill?): Int {
        entity!!.deleted = 0
        return outBillMapper.updateByPrimaryKeySelective(entity)
    }
}
