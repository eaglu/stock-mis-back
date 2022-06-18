package com.qfedu.service

import com.qfedu.base.TimeLimit
import com.qfedu.entity.Bill
import com.qfedu.entity.BillSearch
import com.qfedu.entity.Detail

interface BillService {
    fun list(queryCondition: BillSearch): List<Bill>?

    fun add(entity: Bill?): Int

    fun edit(entity: Bill?): Int

    fun delete(entity: Bill?): Int

}
