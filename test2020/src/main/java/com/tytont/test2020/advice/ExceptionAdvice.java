package com.tytont.test2020.advice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSONObject;
import com.tytont.test2020.mvc.ResponseInfo;
import com.weixin.core.exception.BusinessException;

@ControllerAdvice
public class ExceptionAdvice {

	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
	public ModelAndView handleViewException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		ex.printStackTrace();
		boolean isJsonRequest = isAjaxRequest(request);
		if (isJsonRequest) {
			ModelAndView view = new ModelAndView();
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(ResponseInfo.fail(ex.getMessage())), Map.class);
			jsonView.setAttributesMap(map);
			view.setView(jsonView);
			return view;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("errors");
			modelAndView.addObject("msg", ex.getMessage());
			return modelAndView;
		}
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleRuntimeException(HttpServletRequest request, HttpServletResponse response, RuntimeException ex) {
		ex.printStackTrace();
		boolean isJsonRequest = isAjaxRequest(request);
		if (isJsonRequest) {
			ModelAndView view = new ModelAndView();
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(ResponseInfo.fail(ex.getMessage())), Map.class);
			jsonView.setAttributesMap(map);
			view.setView(jsonView);
			return view;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("errors");
			modelAndView.addObject("msg", ex.getMessage());
			return modelAndView;
		}
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler(BusinessException.class)
	public ModelAndView handleViewBusinessException(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
		ex.printStackTrace();
		boolean isJsonRequest = isAjaxRequest(request);
		if (isJsonRequest) {
			ModelAndView view = new ModelAndView();
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(ResponseInfo.fail(ex.getMessage())), Map.class);
			jsonView.setAttributesMap(map);
			view.setView(jsonView);
			return view;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("errors");
			modelAndView.addObject("msg", ex.getMessage());
			return modelAndView;
		}
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

}
