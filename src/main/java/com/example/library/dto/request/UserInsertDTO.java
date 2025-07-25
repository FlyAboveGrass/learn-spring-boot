package com.example.library.dto.request;

import org.hibernate.validator.constraints.Length;

import com.example.library.constants.UserRole;
import com.example.library.validation.UniqueEmail;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data // 使用 Lombok 注解，自动生成 getter 和 setter 方法。没有 getter/setter，Spring 反序列化时无法赋值
public class UserInsertDTO {
  @NotBlank(message = "用户名不能为空")
  private String name;

  @NotBlank(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  @UniqueEmail(message = "邮箱已存在") // 自定义校验注解。邮箱不能重复注册
  private String email;

  @NotBlank(message = "密码不能为空")
  @Length(min = 8, message = "密码长度必须在8个字符以上")
  private String password;

  @NotBlank(message = "手机号不能为空")
  @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
  private String phone;

  @NotNull(message = "角色不能为空")
  private UserRole role;
}

