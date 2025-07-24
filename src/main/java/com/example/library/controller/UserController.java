package com.example.library.controller;

import com.example.library.dto.request.UserUpdateDTO;
import com.example.library.entity.User;
import com.example.library.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
  // 使用 @Valid 注解，用于验证请求体中的数据。配合 GlobalExceptionHandler 返回验证错误信息
  public void update(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
    userService.update(userUpdateDTO);
  }
  
  // 删除用户
  @PostMapping("/delete/{userId}")
  public void delete(@PathVariable Integer userId) {
    userService.delete(userId);
  }
}
