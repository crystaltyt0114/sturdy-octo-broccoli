package com.tytont.controller;

import java.util.concurrent.Semaphore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Springboot 线程同步之Semaphore 的简单使用
 * @author Administrator
 *
 */

@Controller
public class SemaphoreController {

	//定义信号资源包的总数 只有2个
	Semaphore semaphore = new Semaphore(2);

	@GetMapping("/request")
	@ResponseBody
	public String Resquest() {
		//设置这个接口可用的资源数
		int availablePermits = semaphore.availablePermits();
		if (availablePermits > 0) {
			System.out.println("抢到资源");
		} else {
			System.out.println("资源已被占用，稍后再试");
			return "Resource is busy！";
		}
		try {
			//请求占用一个资源
			semaphore.acquire(1);
			System.out.println("资源正在被使用");
			//放大资源占用时间，便于观察
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release(1);//释放一个资源
			System.out.println("-----------释放资源包----------");
		}
		return "Success";
	}

}
