package com.tytont.test2020.controller;

import javax.servlet.http.HttpServletRequest;

import com.tytont.test2020.model.SysUser;
import com.tytont.test2020.model.WxUser;
import com.tytont.test2020.spring.Constants;

public class BaseController {
	public static SysUser getSysUser(HttpServletRequest request) {
		return (SysUser) request.getSession().getAttribute(Constants.SESSION_SYS_USER);
	}

	public static WxUser getWxUser(HttpServletRequest request) {
		return (WxUser) request.getSession().getAttribute(Constants.SESSION_WX_USER);
	}
}
