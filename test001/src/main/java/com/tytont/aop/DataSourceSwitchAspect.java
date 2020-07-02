package com.tytont.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import groovy.util.logging.Slf4j;

@Component
@Aspect
@Order(-100)
@Slf4j
public class DataSourceSwitchAspect {
	
	private static final Logger log = Logger.getLogger(DataSourceSwitchAspect.class);
	

}
