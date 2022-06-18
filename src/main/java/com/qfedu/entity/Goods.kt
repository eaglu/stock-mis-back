package com.qfedu.entity

import java.math.BigDecimal
import java.util.Date
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "t_goods")
class Goods {
    @Id
    var id: Int? = null
    @Column(name = "goods_name")
    var goodsName: String? = null
    @Column(name = "price")
    var price: BigDecimal? = null
    @Column(name = "stock")
    var stock: Long? = null

    @Column(name = "operator_id")
    var operatorId: Int? = null
    @Column(name = "operator_name")
    var operatorName: String? = null
    @Column(name = "operator_time")
    var operatorTime: Date? = null

}
