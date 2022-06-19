package com.qfedu.service.impl;

import com.qfedu.entity.Repository;
import com.qfedu.mapper.RepositoryMapper;
import com.qfedu.service.RepositoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private RepositoryMapper repositoryMapper;

    @Override
    public Integer updateRepository(Repository repository) {
        Integer rsCount= repositoryMapper.updateRepository(repository);
        return rsCount;
    }

    @Override
    public Integer deleteRepository(Integer id) {
        Integer rsCount = repositoryMapper.deleteRepository(id);
        return rsCount;
    }


    @Override
    public Repository findRepositoryById(Repository repository) {
        return repositoryMapper.findRepositoryById(repository);
    }


    @Override
    public Integer insertGoods(Repository repository) {
        Integer rsCount = 0;
        List<Repository> list = repositoryMapper.findRepositoryByName(repository);
        list.removeIf(goods -> goods.getDeleteFlag() == 1);
        if (list.size() == 0) {
            rsCount = repositoryMapper.insertRepository(repository);
        }
        return rsCount;
    }

    @Override
    public List<Repository> findRepositoryLikeName(Repository repository) {
        if (StringUtils.isBlank(repository.getName())) {
            repository.setName(null);
        }
        if (repository.getName() == null) {
            return repositoryMapper.findRepositoryAll();
        } else {
            return repositoryMapper.findRepositoryLikeName(repository);
        }
    }

    @Override
    public List<Repository> findRepositoryAll() {
        return repositoryMapper.findRepositoryAll();
    }
}
