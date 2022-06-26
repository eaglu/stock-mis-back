package com.qfedu.service

import com.qfedu.entity.Detail
import com.qfedu.entity.DetailSearch

interface DetailService {

    fun list(queryCondition: DetailSearch): List<Detail>?

    fun add(entity: Detail?): Int

    fun edit(entity: Detail?): Int

    fun delete(entity: Detail?): Int

    fun recover(entity: Detail?): Int
}
