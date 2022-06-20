package com.qfedu.mapper;


import com.qfedu.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    @Insert("INSERT into goods (name,code,color,size,amount,delete_flag) " +
            "VALUES(#{name},#{code},#{color},#{size},#{amount}," +
            "#{deleteFlag})")
    Integer insertGoods(Goods goods);

    @Update("UPDATE goods SET name =#{name},code=#{code}," +
            "color=#{color},size=#{size,}," +
            "amount=#{amount} WHERE id=#{id}")
    Integer updateGoods(Goods goods);

    @Update("UPDATE goods SET delete_flag=1 WHERE id=#{id}")
    Integer deleteGoods(Integer id);


    @Select("SELECT * FROM goods WHERE id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "color", property = "color"),
            @Result(column = "size", property = "size"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    Goods findGoodsById(Goods goods);

    @Select("SELECT * FROM goods WHERE name=#{name}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "color", property = "color"),
            @Result(column = "size", property = "size"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Goods> findGoodsByName(Goods goods);

    @Select("SELECT * FROM goods WHERE name like CONCAT('%',#{name},'%')" +
            "or code like CONCAT('%',#{code},'%') " +
            "or color like CONCAT('%',#{color},'%')" +
            "or size=#{size}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "color", property = "color"),
            @Result(column = "size", property = "size"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Goods> findGoodsByLike(Goods goods);

    @Select("SELECT * FROM goods")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "color", property = "color"),
            @Result(column = "size", property = "size"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Goods> findGoodsAll();

    @MapKey("name")
    @Select("select distinct name , code as goodsId from goods order by name;")
    List<Map<String, String>> getGoodsName();


    @Select("select distinct color from goods where name = #{name}")
    List<String> getColor(Goods goods);

    @Select("select distinct size from goods where name = #{name} and color = #{color}")
    List<String> getSize(Goods goods);
}
