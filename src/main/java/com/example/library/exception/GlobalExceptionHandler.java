package com.example.library.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.library.common.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

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

  // 处理参数校验异常
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    FieldError fieldError = bindingResult.getFieldErrors().get(0);

    String errorMessage = fieldError.getDefaultMessage();

    return ApiResponse.error(1, errorMessage);
  }

  // 处理 JSON 反序列化异常
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ApiResponse<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    log.error("JSON 反序列化异常", e);
    Throwable cause = e.getCause();
    if (cause instanceof InvalidFormatException) {
      InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
      if (invalidFormatException.getTargetType().isEnum()) {
        return ApiResponse.error(1, "无效的枚举类型");
      }
    }

    return ApiResponse.error(1, "请求参数格式错误");
  }


  // @ExceptionHandler(BusinessException.class) // 捕获业务异常
  // public ApiResponse<Void> handleBusinessException(BusinessException e) {
  //   log.error("业务异常", e);
  //   return ApiResponse.success(null, e.getMessage());
  // }
}
