package com.example.library.mapper;

import com.example.library.dto.request.UserInsertDTO;
import com.example.library.dto.request.UserUpdateDTO;
import com.example.library.entity.User;
// 导入 MyBatis 注解。用于注解 Mapper 接口
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper // 标记为 MyBatis Mapper 接口（类似定义 API 函数）
public interface UserMapper {
  // 查询所有用户
  @Select("SELECT * FROM user")
  List<User> findAll();

  // 根据用户 ID 查询用户
  @Select("SELECT * FROM user WHERE user_id = #{userId}")
  User findById(@Param("userId") Integer userId);

  // 插入新用户，userId 由数据库自增生成，createdAt 由数据库自动生成，status 默认用 UserStatus.INACTIVE 作为默认
  @Insert("INSERT INTO user (name, email, password, phone, role) VALUES (#{name}, #{email}, #{password}, #{phone}, #{role})")
  @Options(useGeneratedKeys = true, keyProperty = "userId") // 自动生成主键, 获取自增 ID
  void insert(UserInsertDTO userInsertDTO);

  // 更新用户
  // 使用 mybatis 的 xml 文件更新方式
  void update(UserUpdateDTO userUpdateDTO);

  // 删除用户
  @Delete("DELETE FROM user WHERE user_id = #{userId}")
  void delete(@Param("userId") Integer userId);

  // 根据邮箱查找用户
  @Select("SELECT * FROM user WHERE email = #{email}")
  User findByEmail(@Param("email") String email);
}