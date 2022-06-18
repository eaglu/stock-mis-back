package com.qfedu.controller

import cn.hutool.json.XMLTokener.entity
import com.github.pagehelper.PageHelper
import com.qfedu.base.AjaxResult
import com.qfedu.base.AjaxResultUtil
import com.qfedu.base.PageQuery
import com.qfedu.entity.Bill
import com.qfedu.entity.BillSearch
import com.qfedu.entity.Detail
import com.qfedu.service.BillService
import com.qfedu.service.GoodsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/inbill")
class InBillController {
    @Autowired
    lateinit var billService: BillService

    @RequestMapping("/list")
    @ResponseBody
    fun list(@RequestBody billQuery: PageQuery<BillSearch>): AjaxResult {
        val page = PageHelper.offsetPage<Any>(billQuery.startRow,billQuery.limit)
        try {
            val billList = billService.list(billQuery.queryCondition)
            return AjaxResultUtil.pageOK(page.total, billList)
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("/add")
    @ResponseBody
    fun add(@RequestBody entity: Bill): AjaxResult {
        try {
            val rsCount: Int = billService.add(entity)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("edit")
    @ResponseBody
    fun edit(@RequestBody entity: Bill): AjaxResult {
        try {
            val rsCount: Int = billService.edit(entity)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("/delete")
    @ResponseBody
    fun delete(@RequestBody entity: Bill): AjaxResult {
            entity.deleted = 1
            val rsCount: Int = billService.delete(entity)
        return if(rsCount!=0) {
            AjaxResultUtil.ok(rsCount)
        } else {
            AjaxResultUtil.fail(null, "获取数据异常")
        }
    }

//    @RequestMapping("/last")
//    @ResponseBody
//    fun latest(): AjaxResult {
//        try {
//            val entity: Bill = billService.getLast()
//            println(entity)
//            return AjaxResultUtil.ok(entity)
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//        return AjaxResultUtil.fail(null, "获取数据异常")
//    }
}
