package com.example.library.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 借书请求DTO
 */
@Data
public class BorrowBookDTO {
    @NotNull(message = "用户ID不能为空")
    @Min(value = 1, message = "用户ID必须大于0")
    private Integer userId; // 用户ID

    @NotNull(message = "图书ID不能为空")
    @Min(value = 1, message = "图书ID必须大于0")
    private Integer bookId; // 图书ID

    // 借阅日期，不传默认为现在
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Date borrowDate; // 借阅日期

    // 应还日期，不传默认为14天后
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Date dueDate; // 应还日期

    /**
     * 获取借阅日期，如果为null则返回当前日期
     */
    public Date getBorrowDate() {
        return borrowDate != null ? borrowDate : Date.valueOf(LocalDate.now());
    }

    /**
     * 获取应还日期，如果为null则返回14天后
     */
    public Date getDueDate() {
        return dueDate != null ? dueDate : Date.valueOf(LocalDate.now().plusDays(14));
    }
}
