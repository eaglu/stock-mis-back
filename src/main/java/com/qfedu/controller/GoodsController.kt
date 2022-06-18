package com.qfedu.controller

import com.github.pagehelper.PageHelper
import com.qfedu.base.AjaxResult
import com.qfedu.base.AjaxResultUtil
import com.qfedu.base.PageQuery
import com.qfedu.entity.Goods
import com.qfedu.service.GoodsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/goods")
class GoodsController {
    @Autowired
    lateinit var goodsService: GoodsService

    @RequestMapping("/list")
    @ResponseBody
    fun list(@RequestBody goodsQuery: PageQuery<Goods>): AjaxResult{
        val page = PageHelper.offsetPage<Any>(goodsQuery.startRow,goodsQuery.limit)
        try {
            val goodsList = goodsService.list(goodsQuery.queryCondition)
            return AjaxResultUtil.pageOK(page.total, goodsList)
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("add")
    @ResponseBody
    fun add(@RequestBody goods: Goods):AjaxResult{
        try {
            val rsCount: Int = goodsService.add(goods)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }
}
