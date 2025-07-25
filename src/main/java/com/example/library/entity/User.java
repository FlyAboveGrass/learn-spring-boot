// 每一行代码都要有注释

// 用户实体类包
package com.example.library.entity;

// 导入 Lombok 注解，自动生成 getter/setter,用于简化代码
import lombok.Data;

// 导入 java.sql.Timestamp，用于存储时间
import java.sql.Timestamp;

import com.example.library.constants.UserRole;
import com.example.library.constants.UserStatus;

// 用户实体类
// 字段名与数据库表 user 的列名自动驼峰映射​（如 user_id → userId）
@Data // 使用 Lombok 注解，简化代码。自动生成 getter/setter（类似 TS 的 class）
public class User {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole role; // 用户角色，枚举类型，默认 USER（用户）
    private UserStatus status; // 用户状态，枚举类型，默认 INACTIVE（未生效）
    private Timestamp createdAt; // 注册时间
}