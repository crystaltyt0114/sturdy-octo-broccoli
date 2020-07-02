package com.tytont.test2020.spring;

public class SpringHelp {
	public static Class<? extends Object> getNativeClass(Class<? extends Object> proxyClass){
		return proxyClass.getSuperclass();
	}
}
