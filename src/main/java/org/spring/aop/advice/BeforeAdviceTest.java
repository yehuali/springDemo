package org.spring.aop.advice;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class BeforeAdviceTest {
    @Test
    public void before(){
        Waiter target = new NaiveWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        /**
         * 用户可以使用该方法添加多个增强，形成一个增强链
         * addAdvice(int,Advice)增强添加到增强链的具体位置
         */
        pf.addAdvice(advice);

        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }

    @Test
    public void xmlConfigBefore(){
        String configPath = "org/spring/aop/advice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter");
        waiter.greetTo("John");

    }


}
