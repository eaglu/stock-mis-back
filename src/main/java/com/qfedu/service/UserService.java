package com.qfedu.service;

import com.qfedu.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(User user);

    Integer register(User user);
    List<User> list(User queryCondition);
    Integer add(User user);
    Integer edit(User userCondition);
    Integer delete(User user);
    Integer restore(User user);
    Integer reset(User user);
    User selectByUserName(String username);

    List<String> getCompanyList();

    List<Map<String, Integer>> getUserIdList();
}
