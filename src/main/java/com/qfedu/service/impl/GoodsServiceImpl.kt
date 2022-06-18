package com.qfedu.service.impl

import com.qfedu.entity.Goods
import com.qfedu.mapper.GoodsMapper
import com.qfedu.service.GoodsService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GoodsServiceImpl : GoodsService {
    @Autowired
    private lateinit var goodsMapper : GoodsMapper

    override fun list(queryCondition: Goods): List<Goods>? {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        if(StringUtils.isBlank(queryCondition.goodsName)){
            queryCondition.goodsName = null
        }
        if(StringUtils.isBlank(queryCondition.operatorName)){
            queryCondition.operatorName = null
        }
        return goodsMapper.select(queryCondition)
    }

    override fun add(entity: Goods?): Int {
        entity!!.operatorId = 1
        entity.operatorName = "qfedu"
        entity.operatorTime = Date()
        return goodsMapper.insert(entity)
    }
}
