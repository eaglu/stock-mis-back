package com.qfedu.mapper

import com.qfedu.entity.Detail
import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.MySqlMapper

interface DetailMapper : Mapper<Detail>, MySqlMapper<Detail>
