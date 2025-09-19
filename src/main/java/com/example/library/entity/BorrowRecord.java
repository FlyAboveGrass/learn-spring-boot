// 每一行代码都要有注释

// 借阅记录实体类包
package com.example.library.entity;

// 导入 Lombok 注解，自动生成 getter/setter,用于简化代码
import lombok.Data;

// 导入 java.sql.Date，用于存储日期
import java.sql.Date;

// 导入 java.math.BigDecimal，用于存储金额
import java.math.BigDecimal;

// 导入借阅状态枚举
import com.example.library.constants.BorrowStatus;


// 借阅记录实体类
// 字段名与数据库表 borrow_record 的列名自动驼峰映射​（如 borrow_id → borrowId）
@Data // 使用 Lombok 注解，简化代码。自动生成 getter/setter（类似 TS 的 class）
public class BorrowRecord {
    private Integer borrowId; // 借阅记录ID
    private Integer userId; // 用户ID，外键关联user表
    private Integer bookId; // 图书ID，外键关联book表
    private Date borrowDate; // 借阅日期
    private Date dueDate; // 应还日期
    private Date returnDate; // 实际归还日期，可为空
    private Integer renewCount; // 续借次数，默认0，最大2次
    private BigDecimal fineAmount; // 罚金金额，默认0.00
    private BorrowStatus status; // 借阅状态，枚举类型：ACTIVE(借阅中)、RETURNED(已归还)
}
