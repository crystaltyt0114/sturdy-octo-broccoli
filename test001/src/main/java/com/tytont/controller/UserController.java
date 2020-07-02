package com.tytont.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tytont.asnyc.TestAsync;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TestAsync testAsync;

	@Value("${name}")
	private String name;

	@ApiOperation(value = "登录", notes = "系统登录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "用户名", paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", paramType = "form", required = true, dataType = "String") })
	@RequestMapping("login")
	public String login(String username, String password) {
		System.out.println("main thread:" + Thread.currentThread().getId());
		testAsync.test();
		return "loginPage" + name;
	}

	@RequestMapping("loginAction")
	public String loginAction(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			return "login success";
		} catch (Exception e) {
			System.out.println(e);
			return "login failed";
		}

	}
}
