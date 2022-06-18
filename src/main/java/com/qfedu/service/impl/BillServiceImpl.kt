package com.qfedu.service.impl

import com.qfedu.base.TimeLimit
import com.qfedu.entity.Bill
import com.qfedu.entity.BillSearch
import com.qfedu.entity.Detail
import com.qfedu.mapper.BillMapper
import com.qfedu.service.BillService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.mybatis.mapper.entity.Example
import java.util.*

@Service
class BillServiceImpl : BillService{
    @Autowired
    private lateinit var billMapper : BillMapper

    override fun list(queryCondition: BillSearch): List<Bill>? {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        val example = Example(Bill::class.java)
        val criteria =example.createCriteria()
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
        criteria.andEqualTo("deleted", 0)
        return billMapper.selectByExample(example)
    }

    override fun add(entity: Bill?): Int {
        entity!!.date = Date()
        entity.deleted = 0
        return billMapper.insert(entity)
    }

    override fun delete(entity: Bill?): Int {
        return billMapper.updateByPrimaryKeySelective(entity)
    }

    override fun edit(entity: Bill?): Int {
        return billMapper.updateByPrimaryKeySelective(entity)
    }

}
