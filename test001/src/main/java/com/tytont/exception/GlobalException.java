package com.tytont.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String runtimeException(RuntimeException exception) {
		System.out.println(exception);
		return "系统运行时发生错误";
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView NullPointerException(NullPointerException exception) {
		System.out.println(exception);
		return new ModelAndView("500");
	}

}
