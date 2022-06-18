package com.qfedu.controller;


import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qfedu.base.AjaxResult;
import com.qfedu.base.AjaxResultUtil;
import com.qfedu.base.PageQuery;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestBody User user, HttpServletRequest request) {
        try {
            User dbUser = userService.login(user);
            dbUser.setPassword("");//返回ui数据时，需要清掉数据
            String token = IdUtil.fastSimpleUUID();
            dbUser.setToken(token);
            request.getServletContext().setAttribute("usertoken-" + token, dbUser);
            return AjaxResultUtil.ok(dbUser);
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResultUtil.fail(null, "用户名或密码错误");
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public AjaxResult list(@RequestBody PageQuery<User> userPageQuery) {
        //在controller就将分页相关数据设置到PageHelper中，在使用mybatis执行查询时会自动对查询语句进行拦截,增加分页相关条件
        Page page = PageHelper.offsetPage(userPageQuery.getStartRow(), userPageQuery.getLimit());
        try {
            List<User> uesrList = userService.list(userPageQuery.getQueryCondition());
            return AjaxResultUtil.pageOK(page.getTotal(), uesrList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("add")
    @ResponseBody
    public AjaxResult add(@RequestBody User user) {
        try {
            Integer rsCount = userService.add(user);
            return AjaxResultUtil.ok(rsCount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }

    @RequestMapping("companies")
    @ResponseBody
    public AjaxResult getCompanies(){
        try{
            List<String> companies = userService.getCompanies();
            return AjaxResultUtil.ok(companies);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return AjaxResultUtil.fail(null, "获取数据异常");
    }
}
