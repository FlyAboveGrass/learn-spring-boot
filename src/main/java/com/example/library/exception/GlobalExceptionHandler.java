package com.example.library.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.library.common.ApiResponse;

import lombok.extern.slf4j.Slf4j;

// 使用 @ControllerAdvice 捕获全局异常，并返回标准化错误响应：

@Slf4j // 日志记录
@RestControllerAdvice
public class GlobalExceptionHandler {
  // 处理所有未被捕获的异常
  @ExceptionHandler(Exception.class) // 捕获所有异常
  public ApiResponse<Void> handleException(Exception e) {
    log.error("全局异常", e);
    return ApiResponse.success(null, e.getMessage());
  }

  // @ExceptionHandler(BusinessException.class) // 捕获业务异常
  // public ApiResponse<Void> handleBusinessException(BusinessException e) {
  //   log.error("业务异常", e);
  //   return ApiResponse.success(null, e.getMessage());
  // }
}
