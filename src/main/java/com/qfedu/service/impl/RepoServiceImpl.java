package com.qfedu.service.impl;

import com.qfedu.entity.Repo;
import com.qfedu.entity.Repo;
import com.qfedu.mapper.RepoMapper;
import com.qfedu.mapper.RepoMapper;
import com.qfedu.service.RepoService;
import com.qfedu.service.RepoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {
    @Autowired
    private RepoMapper repoMapper;

    @Override
    public Integer updateRepo(Repo repo) {
        Integer rsCount= repoMapper.updateRepo(repo);
        return rsCount;
    }

    @Override
    public Integer deleteRepo(Integer id) {
        Integer rsCount = repoMapper.deleteRepo(id);
        return rsCount;
    }
    @Override
    public Integer recoverRepo(Integer id) {
        Integer rsCount = repoMapper.recoverRepo(id);
        return rsCount;
    }

    @Override
    public Repo findRepoById(Repo repo) {
        return repoMapper.findRepoById(repo);
    }


    @Override
    public Integer insertRepo(Repo repo) {
        Integer rsCount = 0;
        List<Repo> list = repoMapper.findRepoByName(repo);
        list.removeIf(goods -> goods.getDeleteFlag() == 1);
        if (list.size() == 0) {
            rsCount = repoMapper.insertRepo(repo);
        }
        return rsCount;
    }

    @Override
    public List<Repo> findRepoLikeName(Repo repo) {
        if (StringUtils.isBlank(repo.getName())) {
            repo.setName(null);
        }
        if (repo.getName() == null) {
            return repoMapper.findRepoAll();
        } else {
            return repoMapper.findRepoLikeName(repo);
        }
    }

    @Override
    public List<Repo> findRepoAll() {
        return repoMapper.findRepoAll();
    }
}
