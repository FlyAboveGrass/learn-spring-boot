// 每一行代码都要有注释

// 用户实体类包
package com.example.library.entity;

// 导入 Lombok 注解，自动生成 getter/setter,用于简化代码
import lombok.Data;

// 导入 java.sql.Timestamp，用于存储时间
import java.sql.Timestamp;


// 用户实体类
// 字段名与数据库表 user 的列名自动驼峰映射​（如 user_id → userId）
@Data // 使用 Lombok 注解，简化代码。自动生成 getter/setter（类似 TS 的 class）
public class Book {
    private Integer bookId; // 图书ID
    private String isbn; // ISBN号
    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社
    private Timestamp publishDate; // 出版日期
    private Integer categoryId; // 分类ID
    private Integer totalCopies; // 总副本数
    private Integer availableCopies; // 可用副本数
    private String status; // 状态。'AVAILABLE', 'BORROWED', 'MAINTENANCE', 'LOST'
    private String location; // 书架位置
    private Integer libraryId; // 所属图书馆
}