package com.qfedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.base.AjaxResult;
import com.qfedu.base.AjaxResultUtil;
import com.qfedu.base.PageQuery;
import com.qfedu.entity.Goods;
import com.qfedu.entity.QueryGoods;
import com.qfedu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("findGoodsAll")
    @ResponseBody
    public AjaxResult findGoodsAll(@RequestBody PageQuery<Goods> goodsPageQuery) {
        Page page = PageHelper.offsetPage(goodsPageQuery.getStartRow(), goodsPageQuery.getLimit());
        try {
            List<Goods> goodsList = goodsService.findGoodsAll();
            return AjaxResultUtil.pageOK(page.getTotal(), goodsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("findGoodsByLike")
    @ResponseBody
    public AjaxResult findGoodsByLike(@RequestBody PageQuery<QueryGoods> goodsPageQuery) {
            Page page = PageHelper.offsetPage(goodsPageQuery.getStartRow(), goodsPageQuery.getLimit());
        try {
            List<Goods> goodsList = goodsService.findGoodsByLike(goodsPageQuery.getQueryCondition());
            return AjaxResultUtil.pageOK(page.getTotal(), goodsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("deleteGoods")
    @ResponseBody
    public AjaxResult deleteGoods(@RequestBody Integer id) {
        Integer rsCount = goodsService.deleteGoods(id);
        return AjaxResultUtil.ok(rsCount);
    }
    @RequestMapping("recoverGoods")
    @ResponseBody
    public AjaxResult recoverGoods(@RequestBody Integer id) {
        Integer rsCount = goodsService.recoverGoods(id);
        return AjaxResultUtil.ok(rsCount);
    }
    @RequestMapping("/insertGoods")
    @ResponseBody
    public AjaxResult insertGoods(@RequestBody Goods goods) {
        try {
            Integer rsCount = goodsService.insertGoods(goods);
            if (rsCount>0){
                return AjaxResultUtil.ok(rsCount);
            }else {
                return AjaxResultUtil.fail(null, "该商品已存在");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("/updateGoods")
    @ResponseBody
    public AjaxResult updateGoods(@RequestBody Goods goods) {
        try {
            Integer rsCount = goodsService.updateGoods(goods);
            if (rsCount>0){
                return AjaxResultUtil.ok(rsCount);
            }else {
                return AjaxResultUtil.fail(null, "该商品已存在");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("goodsName")
    @ResponseBody
    public AjaxResult getGoodsName() {
        try {
            List<Map<String, String>> goodsName = goodsService.getGoodsName();
            return AjaxResultUtil.ok(goodsName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("color")
    @ResponseBody
    public AjaxResult getColor(@RequestBody Goods goods) {
        try {
            List<String> color = goodsService.getColor(goods);
            return AjaxResultUtil.ok(color);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("size")
    @ResponseBody
    public AjaxResult getSize(@RequestBody Goods goods) {
        try {
            List<String> size = goodsService.getSize(goods);
            return AjaxResultUtil.ok(size);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("getGoods")
    @ResponseBody
    public AjaxResult getGoods(@RequestBody Goods queryCondition) {
        try {
            Goods goods = goodsService.getGoods(queryCondition);
            return AjaxResultUtil.ok(goods);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }
}
