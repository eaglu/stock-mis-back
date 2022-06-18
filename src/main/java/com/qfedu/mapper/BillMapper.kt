package com.qfedu.mapper

import com.qfedu.entity.Bill
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.MySqlMapper

interface BillMapper : Mapper<Bill>, MySqlMapper<Bill>{
}
