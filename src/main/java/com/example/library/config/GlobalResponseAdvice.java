package com.example.library.config;


// 导入需要的包
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;

import com.example.library.common.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    // 支持所有返回值类型（排除特定注解或类型）
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    // 在返回前封装数据
    public Object beforeBodyWrite(@SuppressWarnings("null") Object body, @NonNull MethodParameter returnType, @NonNull MediaType mediaType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> converterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        
        // 1. 若已是 ApiResponse 类型，直接返回
        if (body instanceof ApiResponse) {
            return body;
        }
        
        // 2. 处理 String 类型（避免双重转换异常）
        // 如果你在 ResponseBodyAdvice 里把 String 包装成一个对象（比如 ApiResponse.success(body)），但又直接返回这个对象，Spring 会用 MappingJackson2HttpMessageConverter 把它序列化成 JSON。
        // 但如果你直接返回一个 JSON 字符串，Spring 还会再用 StringHttpMessageConverter 把这个字符串当作“普通字符串”输出，结果就会出现“JSON 被当作字符串输出”，导致前端收到的内容是带引号的字符串（比如 "\"{\\\"code\\\":0,\\\"data\\\":...}\""），而不是标准的 JSON 对象。
        if (body instanceof String) {
            try {
                // ObjectMapper 是 Jackson 库中的类，用于将对象转换为 JSON 字符串
                // writeValueAsString 是 ObjectMapper 类中的方法，用于将对象转换为 JSON 字符串
                return new ObjectMapper().writeValueAsString(ApiResponse.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("String 转换失败", e);
            }
        }
        
        // 3. 其他类型统一包装
        return ApiResponse.success(body);
    }
}