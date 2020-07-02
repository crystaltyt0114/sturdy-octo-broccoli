package com.tytont.test2020.utils;

import java.io.Serializable;

import lombok.Data;

/**
 * 返回信息
 * @author Administrator
 *
 */
@Data
public class ResultInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private Object data;

	public ResultInfo() {
		super();
	}

	public ResultInfo(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static ResultInfo success(String message) {
		ResultInfo info = new ResultInfo();
		info.setMessage(message);
		info.setCode(1);
		return info;
	}

	public static ResultInfo fail(String message) {
		ResultInfo info = new ResultInfo();
		info.setMessage(message);
		info.setCode(0);
		return info;
	}

	public static ResultInfo success(String message, Object data) {
		ResultInfo info = new ResultInfo();
		info.setMessage(message);
		info.setCode(1);
		info.setData(data);
		return info;
	}

	public ResultInfo data(Object t) {
		this.setData(t);
		return this;
	}

}
