package com.tytont.test2020.advice;

import java.lang.reflect.Method;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.tytont.test2020.mvc.IgnoreResponseAdvice;
import com.tytont.test2020.mvc.ResponseInfo;

@RestControllerAdvice(basePackages = "com.cpatrol.controller")
public class ResponseInfoAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2, Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
		IgnoreResponseAdvice ignoreResponseAdvice = arg1.getMethodAnnotation(IgnoreResponseAdvice.class);
		if (ignoreResponseAdvice != null) {
			return body;
		}
		ResponseInfo vo = ResponseInfo.success("操作成功");
		if (body == null) {

		} else if (body instanceof ResponseInfo) {
			vo = (ResponseInfo) body;
		} else {
			vo.setData(body);
		}
		return vo;
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		if (arg0.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
			return false;
		}
		Method m = arg0.getMethod();
		if (m != null && m.isAnnotationPresent(IgnoreResponseAdvice.class)) {
			return false;
		}
		return true;
	}

}
