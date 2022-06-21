package com.qfedu.mapper;

import com.qfedu.entity.Repo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RepoMapper {
    @Insert("INSERT into repo (name,inventory,delete_flag) " +
            "VALUES(#{name},#{inventory},#{deleteFlag})")
    Integer insertRepo(Repo repo);

    @Update("UPDATE repo SET name =#{name}" +
            ",inventory=#{inventory} WHERE  id=#{id}")
    Integer updateRepo(Repo repo);

    @Update("UPDATE repo SET delete_flag=1 " +
            "WHERE id=#{id}")
    Integer deleteRepo(Integer id);



    @Select("SELECT * FROM repo WHERE id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    Repo findRepoById(Repo repo);

    @Select("SELECT * FROM repo WHERE name=#{name}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Repo> findRepoByName(Repo repo);

    @Select("SELECT * FROM repo WHERE name like CONCAT('%',#{name},'%')")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Repo> findRepoLikeName(Repo repo);

    @Select("SELECT * FROM repo")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column ="inventory",property = "inventory"),
            @Result(column = "delete_flag", property = "deleteFlag")
    })
    List<Repo> findRepoAll();
}
