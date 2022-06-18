package com.qfedu.service;

import com.qfedu.entity.Repository;

import java.util.List;

public interface RepositoryService {
    Integer insertGoods(Repository repository);

    Integer updateRepository(Repository repository);
    Integer deleteRepository(Integer id);

    List<Repository> findRepositoryLikeName(Repository repository);

    Repository findRepositoryById(Repository repository);

    List<Repository> findRepositoryAll();

}
