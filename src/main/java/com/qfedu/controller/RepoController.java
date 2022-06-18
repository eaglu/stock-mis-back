package com.qfedu.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.base.AjaxResult;
import com.qfedu.base.AjaxResultUtil;
import com.qfedu.base.PageQuery;
import com.qfedu.entity.Repo;
import com.qfedu.entity.User;
import com.qfedu.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/repo")
public class RepoController {
    @Autowired
    private RepoService repoService;

    @RequestMapping("list")
    @ResponseBody
    public AjaxResult list(@RequestBody PageQuery<Repo> repoPageQuery) {
        //在controller就将分页相关数据设置到PageHelper中，在使用mybatis执行查询时会自动对查询语句进行拦截,增加分页相关条件
        Page page = PageHelper.offsetPage(repoPageQuery.getStartRow(), repoPageQuery.getLimit());
        try {
            List<Repo> repoList = repoService.list(repoPageQuery.getQueryCondition());
            return AjaxResultUtil.pageOK(page.getTotal(), repoList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("add")
    @ResponseBody
    public AjaxResult add(@RequestBody Repo entity) {
        try {
            Integer rsCount = repoService.add(entity);
            return AjaxResultUtil.ok(rsCount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("repos")
    @ResponseBody
    public AjaxResult nameList(){
        try {
            List<Repo> repos = repoService.repos();
            return AjaxResultUtil.ok(repos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }
}
