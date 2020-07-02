package com.tytont.test;

import lombok.extern.log4j.Log4j;

@Log4j
public class LombokTest {

	public static void main(String[] args) {
		log.info("打印");
		User user = new User();
		user.setUsername("张三");
		user.setSex("男");
		System.out.println(user.getUsername());
	}
}
