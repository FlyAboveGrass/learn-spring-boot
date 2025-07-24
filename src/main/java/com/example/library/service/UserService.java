package com.example.library.service;

import com.example.library.dto.request.UserUpdateDTO;
import com.example.library.entity.User;
import com.example.library.mapper.UserMapper;
// 导入 Spring 的注解, 用于注入 UserMapper, 并自动装配
import org.springframework.beans.factory.annotation.Autowired;
// 导入 Spring 的注解, 用于标记这是一个服务类
import org.springframework.stereotype.Service;
import java.util.List;

@Service // 标记为 Spring 的业务组件（类似 Vue 的 useXXX 函数）
public class UserService {
  @Autowired
  private UserMapper userMapper;

  // 查询所有用户
  public List<User> findAll() {
    return userMapper.findAll();
  }

  // 根据用户 ID 查询用户
  public User findById(Integer userId) {
    return userMapper.findById(userId);
  }

  // 插入新用户
  public void insert(User user) {
    // 可在此添加业务逻辑（如密码加密）
    userMapper.insert(user);
  }

  // 更新用户
  public void update(UserUpdateDTO userUpdateDTO) {
    // userMapper.update(userUpdateDTO);
    User user = userMapper.findById(userUpdateDTO.getUserId());
    if (user == null) {
      throw new RuntimeException("用户不存在");
    }
    
    userMapper.update(userUpdateDTO);
  }

  // 删除用户
  public void delete(Integer userId) {
    userMapper.delete(userId);
  }
}
