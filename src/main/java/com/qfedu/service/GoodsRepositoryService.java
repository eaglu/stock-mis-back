package com.qfedu.service;


import com.qfedu.entity.Goods;
import com.qfedu.entity.GoodsRepository;

import java.util.List;
import java.util.Map;

public interface GoodsRepositoryService {
    Integer insertGoods(GoodsRepository goodsRepository);
    List<Map<Goods,Integer>> findGoodsRepositoryByRepository(GoodsRepository goodsRepository);
}
