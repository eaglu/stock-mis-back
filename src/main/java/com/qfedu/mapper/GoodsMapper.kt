package com.qfedu.mapper

import com.qfedu.entity.Goods
import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.MySqlMapper

interface GoodsMapper : Mapper<Goods>, MySqlMapper<Goods>
