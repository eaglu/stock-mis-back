package com.qfedu.service;

import com.qfedu.entity.Repo;
import com.qfedu.entity.User;

import java.util.List;

public interface RepoService {

    List<Repo> list(Repo quertCondition);

    Integer add(Repo entity);

    List<Repo> repos();
}
