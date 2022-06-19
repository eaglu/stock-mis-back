package com.qfedu.service;

import com.qfedu.entity.Goods;

import java.util.List;

public interface GoodsService {


    Integer updateGoods(Goods goods);

    Goods findGoodsById(Goods goods);

    Integer insertGoods(Goods goods);

    Integer deleteGoods(Integer id);
    List<Goods> findGoodsByLike(Goods goods);

    List<Goods> findGoodsAll();
}
