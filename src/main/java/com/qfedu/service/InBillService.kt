package com.qfedu.service

import com.qfedu.entity.Detail
import com.qfedu.entity.InBill
import com.qfedu.entity.InBillSearch
import com.qfedu.entity.OutBillSearch

interface InBillService {
    fun list(queryCondition: InBillSearch): List<InBill>?

    fun add(entity: InBill?): Int

    fun edit(entities: List<InBill?>): Int

    fun delete(entity: InBill?): Int

    fun recover(entity: InBill?): Int
}
