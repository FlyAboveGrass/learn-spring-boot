package com.example.library.dto.request;

import java.sql.Timestamp;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookUpdateDTO {
  private Integer bookId;
  
  private String isbn;

  private String title;

  private String author;

  private String publisher;
  
  @Past(message = "出版日期不能为未来日期")
  private Timestamp publishDate;

  private Integer categoryId;

  @Min(value = 0, message = "总副本数不能小于0")
  private Integer totalCopies;

  private String status;

  private String location;

  private Integer libraryId;
}
