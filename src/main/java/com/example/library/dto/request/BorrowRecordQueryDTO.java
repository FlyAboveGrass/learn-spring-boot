package com.example.library.dto.request;

import lombok.Data;
import com.example.library.constants.BorrowStatus;


/**
 * 借阅记录查询DTO
 */
@Data
public class BorrowRecordQueryDTO {
    private Integer userId; // 用户ID，可选
    private Integer bookId; // 图书ID，可选
    private BorrowStatus status; // 借阅状态，可选
    private Integer borrowId; // 借阅记录ID，可选
}
