package com.qfedu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageHelper;
import com.qfedu.entity.User;
import com.qfedu.mapper.UserMapper;
import com.qfedu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_PASSWORD = "111111";//默认密码

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {

        User userCondition = new User();
        userCondition.setUsername(user.getUsername());
        List<User> list = userMapper.select(userCondition);
        System.out.println(user.getPassword());
        if (list.size() > 0) {
            User dbUser = list.get(0);
            //数据库的md5与传入数据md5编码进行对比
            if (dbUser.getPassword().equals(DigestUtil.md5Hex(user.getPassword()).toUpperCase())) {
                return dbUser;
            }
        }
        throw new RuntimeException("用户名或密码不存在！");
    }

    @Override
    public List<User> list(User userCondition) {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        if (StringUtils.isBlank(userCondition.getUsername())) {
            userCondition.setUsername(null);
        }
        if (StringUtils.isBlank(userCondition.getNickname())) {
            userCondition.setNickname(null);
        }
        List list = userMapper.select(userCondition);
        return list;
    }

    @Override
    public Integer add(User user) {
        user.setTs(new Date());
        user.setPassword(DigestUtil.md5Hex(DEFAULT_PASSWORD).toUpperCase());
        Integer rsCount = userMapper.insert(user);
        return rsCount;
    }

    @Override
    public List<String> getCompanies() {
        return userMapper.getCompanies();
    }
}
