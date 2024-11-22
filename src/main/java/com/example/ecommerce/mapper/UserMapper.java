package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    // 根据 token 查询用户
    @Select("SELECT * FROM users WHERE token = #{token}")
    User findByToken(String token);

    // 更新用户信息（这里主要用于更新登录状态和token等信息）
//    @Update("UPDATE users SET token = #{token}, isLoggedIn = #{isLoggedIn} WHERE id = #{id}")
    @Update("UPDATE users SET balance = #{balance} WHERE id = #{id}")
    void updateUser(User user);

    User[] findAllLoggedInUsers();

    @Select("SELECT * FROM users WHERE id = #{userId}")
    User findById(int userId);
}