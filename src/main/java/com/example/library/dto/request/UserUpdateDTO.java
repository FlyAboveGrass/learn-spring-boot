package com.example.library.dto.request;

import lombok.Data;

import com.example.library.constants.UserRole;

import jakarta.validation.constraints.*;

@Data
public class UserUpdateDTO {
  @NotNull(message = "用户ID不能为空") // 不能为
  private Integer userId;

  @Pattern(regexp = "\\S+", message = "名字不能为全空白字符串")
  private String name;

  @Pattern(regexp = "\\S+", message = "密码不能为全空白字符串")
  private String password;

  @Email(message = "邮箱格式不正确")
  private String email;

  @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
  private String phone;

  @NotNull(message = "角色不能为空")
  private UserRole role;
}
