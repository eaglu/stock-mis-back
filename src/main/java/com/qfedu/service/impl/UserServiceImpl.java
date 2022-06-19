package com.qfedu.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.qfedu.entity.User;
import com.qfedu.mapper.UserMapper;
import com.qfedu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (User dbUser : list) {
            if (dbUser.getPassword().equals(DigestUtil.md5Hex(user.getPassword()).toUpperCase()))
                return dbUser;
        }
        throw new RuntimeException("用户名或密码错误！");
    }

    @Override
    public Integer register(User user) {
        List<User> list = userMapper.selectAll();
        for (User user1 : list) {
            if (user.getUsername().equals(user1.getUsername()))
                throw new RuntimeException("用户名已存在！");
            if (user.getEmail().equals(user1.getEmail()))
                throw new RuntimeException("此邮箱已注册！");
        }
        user.setPassword(DigestUtil.md5Hex(user.getPassword()).toUpperCase());
        return userMapper.insert(user);
    }

    @Override
    public List<User> list(User userCondition) {
        //json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        if (StringUtils.isBlank(userCondition.getUsername())) {
            userCondition.setUsername(null);
        }
        if (StringUtils.isBlank(userCondition.getEmail())) {
            userCondition.setEmail(null);
        }
        return userMapper.select(userCondition);
    }

    @Override
    public Integer add(User user) {
        List<User> list =  userMapper.selectAll();
        for (User user1 : list) {
            if (user.getUsername().equals(user1.getUsername()))
                throw new RuntimeException("用户已存在！");
        }
        user.setPassword(DigestUtil.md5Hex(DEFAULT_PASSWORD).toUpperCase());
        user.setUserFlag(0);
        user.setDeleteFlag(0);
        return userMapper.insert(user);
    }

    @Override
    public Integer edit(User userCondition) {
        return userMapper.updateByPrimaryKey(userCondition);
    }

    @Override
    public Integer delete(User user) {
        user.setDeleteFlag(1);
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Integer reset(User user) {
        user.setPassword(DigestUtil.md5Hex(DEFAULT_PASSWORD).toUpperCase());
        return userMapper.updateByPrimaryKey(user);
    }
}
