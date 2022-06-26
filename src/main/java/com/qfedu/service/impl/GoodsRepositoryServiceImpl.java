package com.qfedu.service.impl;

import com.qfedu.entity.Goods;
import com.qfedu.entity.GoodsRepository;
import com.qfedu.mapper.GoodsMapper;
import com.qfedu.mapper.GoodsRepositoryMapper;
import com.qfedu.service.GoodsRepositoryService;
import com.qfedu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsRepositoryServiceImpl implements GoodsRepositoryService {
    @Autowired
    private GoodsRepositoryMapper goodsRepositoryMapper;
    @Autowired
    private GoodsService goodsService;
    @Override
    public Integer insert(GoodsRepository entity) {
        //查询goods_repository中是否已存在与entity主键相同的表
        GoodsRepository goodsRepoTmp = goodsRepositoryMapper.selectByPrimaryKey(entity);
        if(goodsRepoTmp!=null) {
            //若已存在，对该条记录的amount属性进行更新
            Integer amount = goodsRepoTmp.getAmount();
            goodsRepoTmp.setAmount(entity.getAmount() + amount);
            return goodsRepositoryMapper.updateByPrimaryKeySelective(goodsRepoTmp);
        }else {//不存在，插入新的记录
            return goodsRepositoryMapper.insert(entity);
        }
    }

    @Override
    public Integer edit(List<GoodsRepository> goodsRepoList) {
        GoodsRepository gRNew = goodsRepoList.get(0);
        GoodsRepository gROld = goodsRepoList.get(1);
        //临时变量，用于获取数据库中gROld对应的商品记录
        GoodsRepository gRTmp = new GoodsRepository();
        gRTmp.setGoodsId(gROld.getGoodsId());
        gRTmp.setRepositoryId(gROld.getRepositoryId());
        GoodsRepository gR = goodsRepositoryMapper.selectOne(gRTmp);
        //新的商品编号与旧商品编号相同，若相同，代表仅仅是商品数量上的变化
        if(Objects.equals(gRNew.getGoodsId(), gROld.getGoodsId())){
            Integer amount = gROld.getAmount() - gRNew.getAmount();
            gROld.setAmount(gR.getAmount() - amount);
            return goodsRepositoryMapper.updateByPrimaryKeySelective(gR);
        }else {
            //若不相同，表示商品种类发生了改变，需要同时更新新老商品的数量
            gR.setAmount(gR.getAmount()-gROld.getAmount());
            Integer rsCount = goodsRepositoryMapper.updateByPrimaryKeySelective(gR);
            return rsCount + this.insert(gRNew);
        }
    }

    @Override
    public GoodsRepository findByKey(GoodsRepository goodsRepository) {
        return goodsRepositoryMapper.selectByPrimaryKey(goodsRepository);
    }
}
