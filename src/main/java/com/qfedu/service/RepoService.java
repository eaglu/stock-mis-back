package com.qfedu.service;

import com.qfedu.entity.Repo;

import java.util.List;

public interface RepoService {
    Integer insertRepo(Repo repo);

    Integer updateRepo(Repo repo);
    Integer deleteRepo(Integer id);

    List<Repo> findRepoLikeName(Repo repo);

    Repo findRepoById(Repo repo);

    List<Repo> findRepoAll();

}
