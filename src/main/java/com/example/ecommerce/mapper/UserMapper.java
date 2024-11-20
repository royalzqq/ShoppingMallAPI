package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    // 根据用户名和密码查询用户（用于未加密密码的场景，不推荐）
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(String username, String password);

    // 插入新用户
    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void insert(User user);
}
