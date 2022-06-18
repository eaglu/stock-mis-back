package com.qfedu.service.impl

import com.qfedu.entity.Detail
import com.qfedu.entity.Goods
import com.qfedu.mapper.DetailMapper
import com.qfedu.mapper.GoodsMapper
import com.qfedu.service.DetailService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.mybatis.mapper.entity.Example
import java.util.*

@Service
class DetailServiceImpl : DetailService{
    @Autowired
    private lateinit var detailMapper : DetailMapper

    override fun list(queryCondition: Detail): List<Detail>? {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        print(queryCondition)
        return detailMapper.select(queryCondition)
    }

    override fun add(entity: Detail?): Int {
        return detailMapper.insert(entity)
    }

    override fun edit(entity: Detail?): Int {
        return detailMapper.insert(entity)
    }
}
