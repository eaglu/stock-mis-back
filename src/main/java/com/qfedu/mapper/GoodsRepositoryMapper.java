package com.qfedu.mapper;

import com.qfedu.entity.GoodsRepository;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface GoodsRepositoryMapper extends Mapper<GoodsRepository>, MySqlMapper<GoodsRepository> {
//    @Insert("INSERT into goods_repository (goods_id,amount,repository_id) " +
//            "VALUES(#{goodsId},#{amount},#{repositoryId})")
//    Integer insertGoods(GoodsRepository goodsRepository);
//
//    @Select("SELECT * FROM goods_repository WHERE repository_id=#{repositoryId}")
//    @Results({
//            @Result(column = "goods_id", property = "goodsId"),
//            @Result(column = "amount", property = "amount"),
//            @Result(column = "repository_id", property = "repositoryId"),
//    })
//    List<GoodsRepository> findGoodsRepositoryByRepository(GoodsRepository goodsRepository);
//
//    @Select("SELECT * FROM goods_repository WHERE goods_id=#{goodsId} and repository_id=#{repositoryId}")
//    @Results({
//            @Result(column = "goods_id", property = "goodsId"),
//            @Result(column = "amount", property = "amount"),
//            @Result(column = "repository_id", property = "repositoryId"),
//    })
//    List<GoodsRepository> findGoodsRepositoryByRepositoryAndGoods(GoodsRepository goodsRepository);
//
//    @Update("UPDATE goods_repository SET amount=#{amount} where repository_id=#{repositoryId}" +
//            "goods_id=#{goodsId}")
//    Integer updateGoodsRepository(GoodsRepository goodsRepository);
}

