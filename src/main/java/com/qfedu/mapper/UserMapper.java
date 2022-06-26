package com.qfedu.mapper;

import com.qfedu.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    @Select("select distinct company from t_user order by company")
    public List<String> getCompanyList();

    @MapKey("name")
    @Select("select distinct name , code as goodsId from goods order by name;")
    List<Map<String, Integer>> getUserIdList();
}
