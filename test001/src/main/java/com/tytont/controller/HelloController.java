package com.tytont.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tytont.service.UserService;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping("/hello")
	public String hello() {
		return "hello !!!!";
	}

	@RequestMapping("/excp")
	public String excp() {
		int i = 1 / 0;
		return "发生错误了";
	}

	@RequiresRoles(value = { "admin" })
	@RequestMapping("getUser1")
	public Object getUser1() {
		return userService.getUser(1);
	}

	@RequiresRoles(value = { "admin" })
	@RequestMapping("getRole")
	public Object getRole() {
		return userService.getRoleByUser(1);
	}

	@RequiresPermissions(value = { "sys:page" })
	@RequestMapping("getUser2")
	public Object getUser2() {
		return userService.page(1, 10);
	}

	@RequestMapping("add")
	public Object add() {
		return userService.add();
	}

	@RequestMapping("update")
	public Object update(String... aString) {
		return userService.update();
	}

}
