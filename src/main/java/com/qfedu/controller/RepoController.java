package com.qfedu.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.base.AjaxResult;
import com.qfedu.base.AjaxResultUtil;
import com.qfedu.base.PageQuery;
import com.qfedu.entity.Repo;
import com.qfedu.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/repo")
public class RepoController{
    @Autowired
    private RepoService repoService;

    @RequestMapping("findRepoAll")
    @ResponseBody
    public AjaxResult findRepoAll(@RequestBody PageQuery<Repo> repositoryPageQuery) {
        Page page = PageHelper.offsetPage(repositoryPageQuery.getStartRow(), repositoryPageQuery.getLimit());
        try {
            List<Repo> repositoryList= repoService.findRepoAll();
            return AjaxResultUtil.pageOK(page.getTotal(), repositoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("insertRepo")
    @ResponseBody
    public AjaxResult insertRepo(@RequestBody Repo repo) {
        try{
            Integer rsCount= repoService.insertRepo(repo);
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

    @RequestMapping("findRepo")
    @ResponseBody
    public  AjaxResult findRepoLikeName(@RequestBody PageQuery<Repo> repositoryPageQuery) {
        Page page = PageHelper.offsetPage(repositoryPageQuery.getStartRow(), repositoryPageQuery.getLimit());
        try {
            List<Repo> repositoryList= repoService.findRepoLikeName(repositoryPageQuery.getQueryCondition());
//            repositoryList.removeIf(repository -> repository.getDeleteFlag()==1);
            return AjaxResultUtil.pageOK(page.getTotal(), repositoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("findRepoById")
    @ResponseBody
    public  AjaxResult findRepoById(@RequestBody Repo repo) {
        try {
            Repo repoNew= repoService.findRepoById(repo);
            return AjaxResultUtil.ok(repoNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }


    @RequestMapping("deleteRepo")
    @ResponseBody
    public AjaxResult deleteRepo(@RequestBody Integer id) {
        Integer rsCount= repoService.deleteRepo(id);
        return AjaxResultUtil.ok(rsCount);
    }
    @RequestMapping("recoverRepo")
    @ResponseBody
    public AjaxResult recoverRepo(@RequestBody Integer id) {
        Integer rsCount= repoService.recoverRepo(id);
        return AjaxResultUtil.ok(rsCount);
    }
    @RequestMapping("updateRepo")
    @ResponseBody
    public AjaxResult updateRepo(@RequestBody Repo repo){
        try{
            Integer rsCount= repoService.updateRepo(repo);
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
