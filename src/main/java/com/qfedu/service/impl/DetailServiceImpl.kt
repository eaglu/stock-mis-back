package com.qfedu.service.impl

import com.qfedu.entity.Detail
import com.qfedu.entity.DetailSearch
import com.qfedu.entity.InBill
import com.qfedu.mapper.DetailMapper
import com.qfedu.mapper.GoodsMapper
import com.qfedu.service.DetailService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.mybatis.mapper.entity.Example
import javax.annotation.Resource

@Service
class DetailServiceImpl : DetailService{
    @Autowired
    private lateinit var detailMapper : DetailMapper
    override fun list(queryCondition: DetailSearch): List<Detail>? {
        val example = Example(Detail::class.java)
        val criteria =example.createCriteria()
        criteria.andEqualTo("billId",queryCondition.billId)
        if(!StringUtils.isBlank(queryCondition.goodsName)){
            criteria.andLike("goodsName","%${queryCondition.goodsName}%")
        }else{
            queryCondition.goodsName = null
        }
        if(!StringUtils.isBlank(queryCondition.goodsCode)){
            criteria.andLike("goodsCode","%${queryCondition.goodsCode}%")
        }else{
            queryCondition.goodsCode = null
        }
        if(!StringUtils.isBlank(queryCondition.goodsColor)){
            criteria.andLike("goodsColor","%${queryCondition.goodsColor}%")
        }else{
            queryCondition.goodsColor = null
        }
        if(!StringUtils.isBlank(queryCondition.goodsSize)){
            criteria.andLike("goodsSize","%$queryCondition.goodsSize}%")
        }else{
            queryCondition.goodsSize = null
        }
        criteria.andLessThan("deleted", queryCondition.deleted+1)
        criteria.andBetween("amount", queryCondition.minAmount,queryCondition.maxAmount);
        return detailMapper.selectByExample(example)
    }

    override fun add(entity: Detail?): Int {
        entity!!.deleted = 0
        return detailMapper.insert(entity)
    }

    override fun edit(entity: Detail?): Int {
        return detailMapper.updateByPrimaryKeySelective(entity)
    }

    override fun delete(entity: Detail?): Int {
        entity!!.deleted = 1
        return detailMapper.updateByPrimaryKeySelective(entity)
    }

    override fun recover(entity: Detail?): Int {
        entity!!.deleted = 0
        return detailMapper.updateByPrimaryKeySelective(entity)
    }
}
