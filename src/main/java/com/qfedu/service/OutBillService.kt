package com.qfedu.service

import com.qfedu.entity.InBill
import com.qfedu.entity.OutBill
import com.qfedu.entity.OutBillSearch

interface OutBillService {
    fun list(queryCondition: OutBillSearch): List<OutBill>?

    fun add(entity: OutBill?): Int

    fun edit(entities: List<OutBill?>): Int

    fun delete(entity: OutBill?): Int

    fun recover(entity: OutBill?): Int
}
