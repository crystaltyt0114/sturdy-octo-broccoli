package com.tytont.test2020.mvc;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseInfo implements Serializable {
	private int code;
	private String message;
	private Object data;

	public ResponseInfo() {
		super();
	}

	public ResponseInfo(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static ResponseInfo success(String message) {
		ResponseInfo info = new ResponseInfo();
		info.setMessage(message);
		info.setCode(1);
		return info;
	}

	public static ResponseInfo fail(String message) {
		ResponseInfo info = new ResponseInfo();
		info.setMessage(message);
		info.setCode(0);
		return info;
	}

	public static ResponseInfo success(String message, Object data) {
		ResponseInfo info = new ResponseInfo();
		info.setMessage(message);
		info.setCode(1);
		info.setData(data);
		return info;
	}

	public ResponseInfo data(Object t) {
		this.setData(t);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
