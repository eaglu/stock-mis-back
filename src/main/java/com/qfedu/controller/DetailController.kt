package com.qfedu.controller

import com.github.pagehelper.PageHelper
import com.qfedu.base.AjaxResult
import com.qfedu.base.AjaxResultUtil
import com.qfedu.base.PageQuery
import com.qfedu.entity.Detail
import com.qfedu.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/detail")
class DetailController {
    @Autowired
    lateinit var detailService: DetailService

    @RequestMapping("/list")
    @ResponseBody
    fun list(@RequestBody detailQuery: PageQuery<Detail>): AjaxResult {
        val page = PageHelper.offsetPage<Any>(detailQuery.startRow,detailQuery.limit)
        try {
            val detailList = detailService.list(detailQuery.queryCondition)
            return AjaxResultUtil.pageOK(page.total, detailList)
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("/add")
    @ResponseBody
    fun add(@RequestBody entity: Detail): AjaxResult {
        try {
            val rsCount: Int = detailService.add(entity)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }

    @RequestMapping("/edit")
    @ResponseBody
    fun edit(@RequestBody entity: Detail): AjaxResult {
        try {
            val rsCount: Int = detailService.edit(entity)
            return AjaxResultUtil.ok(rsCount)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return AjaxResultUtil.fail(null, "获取数据异常")
    }
}
