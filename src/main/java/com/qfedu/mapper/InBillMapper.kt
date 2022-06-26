package com.qfedu.mapper

import com.qfedu.entity.InBill
import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.MySqlMapper

interface InBillMapper : Mapper<InBill>, MySqlMapper<InBill> {
}
