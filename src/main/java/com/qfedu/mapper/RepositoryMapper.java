package com.qfedu.mapper;

import com.qfedu.entity.Repository;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RepositoryMapper {
    @Insert("INSERT into repository (name,inventory,delete_flag) " +
            "VALUES(#{name},#{inventory},#{deleteFlag})")
    Integer insertRepository(Repository repository);

    @Update("UPDATE repository SET name =#{name}" +
            ",inventory=#{inventory} WHERE  id=#{id}")
    Integer updateRepository(Repository repository);

    @Update("UPDATE repository SET delete_flag=1 " +
            "WHERE id=#{id}")
    Integer deleteRepository(Integer id);



    @Select("SELECT * FROM repository WHERE id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    Repository findRepositoryById(Repository repository);

    @Select("SELECT * FROM repository WHERE name=#{name}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Repository> findRepositoryByName(Repository repository);

    @Select("SELECT * FROM repository WHERE name like CONCAT('%',#{name},'%')")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Repository> findRepositoryLikeName(Repository repository);

    @Select("SELECT * FROM repository")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Repository> findRepositoryAll();
}
