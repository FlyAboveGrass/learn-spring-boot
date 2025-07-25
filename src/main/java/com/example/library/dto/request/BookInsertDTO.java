package com.example.library.dto.request;

import java.sql.Timestamp;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookInsertDTO {
  @NotBlank(message = "ISBN号不能为空")
  private String isbn;

  @NotBlank(message = "书名不能为空")
  private String title;

  @NotBlank(message = "作者不能为空")
  private String author;

  @NotBlank(message = "出版社不能为空")
  private String publisher;
  
  @Past(message = "出版日期不能为未来日期")
  private Timestamp publishDate;

  @Min(value = 1, message = "非法的分类ID")
  private Integer categoryId = 1;

  @Min(value = 0, message = "总副本数不能小于0")
  private Integer totalCopies = 0;
}
