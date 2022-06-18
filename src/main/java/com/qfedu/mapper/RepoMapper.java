package com.qfedu.mapper;


import com.qfedu.entity.Repo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface RepoMapper extends Mapper<Repo>, MySqlMapper<Repo> {
}
