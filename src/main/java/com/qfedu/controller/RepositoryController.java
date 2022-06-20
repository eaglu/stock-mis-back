package com.qfedu.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.base.AjaxResult;
import com.qfedu.base.AjaxResultUtil;
import com.qfedu.base.PageQuery;
import com.qfedu.entity.Repository;
import com.qfedu.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/repository")
public class RepositoryController {
    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("findRepositoryAll")
    @ResponseBody
    public AjaxResult findRepositoryAll(@RequestBody PageQuery<Repository> repositoryPageQuery) {
        Page page = PageHelper.offsetPage(repositoryPageQuery.getStartRow(), repositoryPageQuery.getLimit());
        try {
            List<Repository> repositoryList=repositoryService.findRepositoryAll();
            return AjaxResultUtil.pageOK(page.getTotal(), repositoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("insertRepository")
    @ResponseBody
    public AjaxResult deleteGoods(@RequestBody Repository repository) {
        try{
            Integer rsCount=repositoryService.insertGoods(repository);
            if(rsCount>0){
                return AjaxResultUtil.ok(rsCount);
            }else {
                return AjaxResultUtil.fail(null, "该仓库已存在");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("findRepositoryLikeName")
    @ResponseBody
    public  AjaxResult findRepositoryLikeName(@RequestBody PageQuery<Repository> repositoryPageQuery) {
        Page page = PageHelper.offsetPage(repositoryPageQuery.getStartRow(), repositoryPageQuery.getLimit());
        try {
            List<Repository> repositoryList=repositoryService.findRepositoryLikeName(repositoryPageQuery.getQueryCondition());
//            repositoryList.removeIf(repository -> repository.getDeleteFlag()==1);
            return AjaxResultUtil.pageOK(page.getTotal(), repositoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }


    @RequestMapping("deleteRepository")
    @ResponseBody
    public AjaxResult deleteGoods(@RequestBody Integer id) {
        Integer rsCount= repositoryService.deleteRepository(id);
        return AjaxResultUtil.ok(rsCount);
    }

    @RequestMapping("updateRepository")
    @ResponseBody
    public AjaxResult updateRepository(@RequestBody Repository repository){
        try{
            Integer rsCount=repositoryService.updateRepository(repository);
            if(rsCount>0){
                return AjaxResultUtil.ok(rsCount);
            }else {
                return AjaxResultUtil.fail(null, "仓库已存在");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }
}
