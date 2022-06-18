package com.qfedu.service.impl;

import com.qfedu.entity.Repo;
import com.qfedu.entity.User;
import com.qfedu.mapper.RepoMapper;
import com.qfedu.service.RepoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {

    @Autowired
    private RepoMapper repoMapper;

    @Override
    public List<Repo> list(Repo quertCondition) {
//json传过来的空字符串会导致tkmybatis查询时携带值为空的查询条件
        if (StringUtils.isBlank(quertCondition.getName())) {
            quertCondition.setName(null);
        }
        List list = repoMapper.select(quertCondition);
        return list;
    }

    @Override
    public Integer add(Repo entity) {
        entity.setOperatorId(1);
        entity.setOperatorName("千锋虎哥");
        entity.setOperatorTime(new Date());
        Integer rsCount = repoMapper.insert(entity);
        return rsCount;
    }

    @Override
    public List<Repo> repos() {
        return repoMapper.selectAll();
    }
}
