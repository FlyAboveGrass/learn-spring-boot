package com.example.library.common;
import lombok.Data;

// 导入 Jackson 注解，用于序列化/反序列化 JSON
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 如果数据为 null，则不返回该字段
public class ApiResponse<T> {
  private int code;
  private String msg;
  private T data;

  // 私有构造方法
  private ApiResponse(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
  
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(200, "", data);
  }

  public static <T> ApiResponse<T> success(T data, String msg) {
    return new ApiResponse<>(200, msg, data);
  }

  public static <T> ApiResponse<T> error(int code, String msg) {
    return new ApiResponse<>(code, msg, null);
  }
}
