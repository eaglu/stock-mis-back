package com.qfedu.service

import com.qfedu.entity.Detail
import com.qfedu.entity.Goods

interface DetailService {

    fun list(queryCondition: Detail): List<Detail>?

    fun add(entity: Detail?): Int

    fun edit(entity: Detail?): Int
}
