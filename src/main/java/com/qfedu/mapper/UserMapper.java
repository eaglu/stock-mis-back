package com.qfedu.mapper;

import com.qfedu.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    @Select({
            "select distinct company from t_user;"
    })
    List<String> getCompanies();
}
