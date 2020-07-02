package com.tytont.timer;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

//@Component
public class TestTimer {

	@Scheduled(fixedDelay = 2000)
	public void t1() {
		System.out.println(new Date());
	}
}
