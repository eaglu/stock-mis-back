package com.qfedu.service.impl;

import com.qfedu.entity.Goods;
import com.qfedu.entity.GoodsRepository;
import com.qfedu.mapper.GoodsMapper;
import com.qfedu.mapper.GoodsRepositoryMapper;
import com.qfedu.service.GoodsRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsRepositoryServiceImpl implements GoodsRepositoryService {
    @Autowired
    private GoodsRepositoryMapper goodsRepositoryMapper;
    private GoodsMapper goodsMapper;

    @Override
    public Integer insertGoods(GoodsRepository goodsRepository) {
        Integer rsCount;
        int tempAmount = goodsRepository.getAmount();
        List<GoodsRepository> list = goodsRepositoryMapper.findGoodsRepositoryByRepositoryAndGoods(goodsRepository);
        if (list.size() > 0) {
            tempAmount=tempAmount+list.get(0).getAmount();
            goodsRepository.setAmount(tempAmount);
            rsCount=goodsRepositoryMapper.updateGoodsRepository(goodsRepository);
        }else {
            rsCount=goodsRepositoryMapper.insertGoods(goodsRepository);
        }
        return rsCount;
    }

    @Override
    public List<Map<Goods,Integer>> findGoodsRepositoryByRepository(GoodsRepository goodsRepository) {
        List<Map<Goods,Integer>> list = null;
        Map<Goods,Integer> map = null;
        Goods goods=null;
        List<GoodsRepository> goodsRepositoryList=goodsRepositoryMapper.findGoodsRepositoryByRepository(goodsRepository);
        for (GoodsRepository goodsRepository1 : goodsRepositoryList){
            goods.setId(goodsRepository1.getGoodsId());
            Goods goods1=goodsMapper.findGoodsById(goods);
            map.put(goods1,goodsRepository1.getAmount());
        }
        return list;
    }
}
