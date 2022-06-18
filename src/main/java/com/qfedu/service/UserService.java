package com.qfedu.service;

import com.qfedu.entity.User;

import java.util.List;

public interface UserService {
    User login(User user);

    List<User> list(User queryCondition);

    Integer add(User user);

    List<String> getCompanies();
}
