package com.qfedu.service.impl;

import com.qfedu.entity.Goods;
import com.qfedu.entity.GoodsRepository;
import com.qfedu.mapper.GoodsMapper;
import com.qfedu.mapper.GoodsRepositoryMapper;
import com.qfedu.service.GoodsRepositoryService;
import com.qfedu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsRepositoryServiceImpl implements GoodsRepositoryService {
    @Autowired
    private GoodsRepositoryMapper goodsRepositoryMapper;
    @Autowired
    private GoodsService goodsService;

    @Override
    public Integer insertGoods(GoodsRepository goodsRepository) {
        Integer rsCount;
        int tempAmount = goodsRepository.getAmount();
        List<GoodsRepository> list = goodsRepositoryMapper.findGoodsRepositoryByRepositoryAndGoods(goodsRepository);
        if (list.size() > 0) {
            tempAmount = tempAmount + list.get(0).getAmount();
            goodsRepository.setAmount(tempAmount);
            rsCount = goodsRepositoryMapper.updateGoodsRepository(goodsRepository);
        } else {
            rsCount = goodsRepositoryMapper.insertGoods(goodsRepository);
        }
        return rsCount;
    }

    @Override
    public List<Map<Goods, Integer>> findGoodsRepositoryByRepository(GoodsRepository goodsRepository) {
        List<Map<Goods, Integer>> list = new ArrayList<Map<Goods, Integer>>();
        Map<Goods, Integer> map = new HashMap<Goods, Integer>();

        List<GoodsRepository> goodsRepositoryList = goodsRepositoryMapper.findGoodsRepositoryByRepository(goodsRepository);
        for (GoodsRepository goodsRepository1 : goodsRepositoryList) {
            int id = goodsRepository1.getGoodsId();
            Goods goods = new Goods();
            goods.setId(id);
            Goods goods1 = goodsService.findGoodsById(goods);
            if (goods1!=null){
                map.put(goods1, goodsRepository1.getAmount());
                list.add(map);
            }
        }
        return list;
    }
}
