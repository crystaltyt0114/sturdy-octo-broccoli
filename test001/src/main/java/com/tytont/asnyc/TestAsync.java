package com.tytont.asnyc;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestAsync {

	@Async
	public void test() {
		System.out.println("异步方法的id:" + Thread.currentThread().getId());
	}
}
