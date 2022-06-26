package com.qfedu.controller

import com.github.pagehelper.PageHelper
import com.qfedu.base.AjaxResult
import com.qfedu.base.AjaxResultUtil
import com.qfedu.base.PageQuery
import com.qfedu.entity.InBill
import com.qfedu.entity.OutBill
import com.qfedu.entity.OutBillSearch
import com.qfedu.service.OutBillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/outbill")
class OutBillController {
    @Autowired
    lateinit var billService: OutBillService

    @RequestMapping("/list")
    @ResponseBody
    fun list(@RequestBody billQuery: PageQuery<OutBillSearch>): AjaxResult {
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
    fun add(@RequestBody entity: OutBill): AjaxResult {
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
    fun edit(@RequestBody entities: List<OutBill>): AjaxResult {
        try {
            val rsCount: Int = billService.edit(entities)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("/delete")
    @ResponseBody
    fun delete(@RequestBody entity: OutBill): AjaxResult {
        entity.deleted = 1
        val rsCount: Int = billService.delete(entity)
        return if(rsCount!=0) {
            AjaxResultUtil.ok(rsCount)
        } else {
            AjaxResultUtil.fail(null, "获取数据异常")
        }
    }

    @RequestMapping("/recover")
    @ResponseBody
    fun recover(@RequestBody entity: OutBill): AjaxResult {
        try {
            val rsCount: Int = billService.recover(entity)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }
}
