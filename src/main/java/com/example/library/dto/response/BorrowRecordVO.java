package com.example.library.dto.response;

import lombok.Data;
import com.example.library.constants.BorrowStatus;

import java.sql.Date;
import java.math.BigDecimal;

/**
 * 借阅记录视图对象（VO - Value Object）
 * 用于查询时返回包含关联信息的完整数据
 */
@Data
public class BorrowRecordVO {
    // 借阅记录信息
    private Integer borrowId; // 借阅记录ID
    private Date borrowDate; // 借阅日期
    private Date dueDate; // 应还日期
    private Date returnDate; // 实际归还日期
    private Integer renewCount; // 续借次数
    private BigDecimal fineAmount; // 罚金金额
    private BorrowStatus status; // 借阅状态
    
    // 用户信息
    private UserInfo userInfo; // 用户信息
    // 书籍信息
    private BookInfo bookInfo; // 书籍信息
    
    /**
     * 用户信息内部类
     */
    @Data
    public static class UserInfo {
        private Integer userId; // 用户ID
        private String userName; // 用户名
    }
    
    /**
     * 书籍信息内部类
     */
    @Data
    public static class BookInfo {
        private Integer bookId; // 图书ID
        private String bookTitle; // 书名
    }
}
