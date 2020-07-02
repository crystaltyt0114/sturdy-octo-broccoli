package com.tytont.test2020.spring;

import org.springframework.context.ApplicationContext;

/**
 * 从Spring上下文取出Bean工具类
 *
 * @author ZhuPengWei
 * @date 2018/6/27 18:04
 */
public class SpringApplicationContextUtil {

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringApplicationContextUtil.applicationContext == null) {
            SpringApplicationContextUtil.applicationContext = applicationContext;
        }

    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}

