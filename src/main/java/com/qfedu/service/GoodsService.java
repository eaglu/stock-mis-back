package com.qfedu.service;

import com.qfedu.entity.Goods;
import com.qfedu.entity.QueryGoods;

import java.util.List;
import java.util.Map;

public interface GoodsService {


    Integer updateGoods(Goods goods);

    Goods findGoodsById(Goods goods);

    Integer insertGoods(Goods goods);

    Integer deleteGoods(Integer id);
    List<Goods> findGoodsByLike(QueryGoods queryGoods);

    List<Goods> findGoodsAll();

    List<Map<String, String>> getGoodsName();

    List<String> getColor(Goods goods);

    List<String> getSize(Goods goods);
}
