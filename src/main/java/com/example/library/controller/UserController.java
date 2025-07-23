package com.example.library.controller;

import com.example.library.entity.User;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
// web.bind.annotation 用于处理 HTTP 的请求
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  // 查询所有用户
  @GetMapping("/findAll")
  public List<User> findAll() {
    return userService.findAll();
  }

  // 根据用户 ID 查询用户
  @GetMapping("/{userId}")
  public User findByUser(@PathVariable Integer userId) {
    return userService.findById(userId);
  }

  // 插入新用户
  @PostMapping("/insert")
  public void insert(@RequestBody User user) {
    userService.insert(user);
  }

  // 更新用户
  @PostMapping("/update")
  public void update(@RequestBody User user) {
    userService.update(user);
  }
  
  // 删除用户
  @PostMapping("/delete/{userId}")
  public void delete(@PathVariable Integer userId) {
    userService.delete(userId);
  }
}
