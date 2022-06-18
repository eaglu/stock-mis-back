package com.qfedu.service

import com.qfedu.entity.Goods
import com.qfedu.entity.User

interface GoodsService {

    fun list(queryCondition: Goods): List<Goods>?

    fun add(entity: Goods?): Int
}
