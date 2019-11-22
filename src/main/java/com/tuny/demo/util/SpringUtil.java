package com.tuny.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 字符串帮助类
 *
 * @author wangzhaofeng
 * @date 2018/8/12 下午12:20
 */
//@Component
//@DependsOn("springContextHolder")
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    // 非@import显式注入，@Component是必须的，且该类必须与main同包或子包
    // 若非同包或子包，则需手动import 注入，有没有@Component都一样
    // 可复制到Test同包测试

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
        System.out.println("---------------com.ilex.jiutou.util.Test.Main.SubPackage.SpringUtil---------------");
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);

    }

    /**
     * 通过class获取Bean.
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> classType){
        return getApplicationContext().getBean(classType);
    }

    /**
     * 通过name,以及Class返回指定的Bean
     * @param name
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name,Class<T> classType){
        return getApplicationContext().getBean(name, classType);
    }

}


