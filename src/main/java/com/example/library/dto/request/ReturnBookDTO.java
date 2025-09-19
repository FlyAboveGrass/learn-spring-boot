package com.example.library.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.sql.Date;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * NOTE： Nulls.AS_EMPTY 表示当JSON中的值为null时，将其视为"空值"，具体行为取决于字段类型：
 * 字段类型	AS_EMPTY的行为
 * String	设置为空字符串 ""
 * Collection	设置为空集合 []
 * Map	设置为空Map {}
 * Date	设置为null（因为Date没有"空"的概念）
 * BigDecimal	设置为null
 * 其他对象类型	设置为null
 */


/**
 * 还书请求DTO
 */
@Data
public class ReturnBookDTO {
    @NotNull(message = "借阅记录ID不能为空")
    @Min(value = 1, message = "借阅记录ID必须大于0")
    private Integer borrowId; // 借阅记录ID

    // 归还日期，不传默认为现在
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Date returnDate; // 归还日期

    // 罚金金额，不传时默认为0，系统会自动计算逾期罚金
    @DecimalMin(value = "0.00", message = "罚金金额不能为负数")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private BigDecimal fineAmount; // 罚金金额

    /**
     * 获取归还日期，如果为null则返回当前日期
     */
    public Date getReturnDate() {
        return returnDate != null ? returnDate : Date.valueOf(LocalDate.now());
    }

    /**
     * 获取罚金金额，如果为null则返回0
     */
    public BigDecimal getFineAmount() {
        return fineAmount != null ? fineAmount : BigDecimal.ZERO;
    }
}
