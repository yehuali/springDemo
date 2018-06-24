package org.spring.aop.exceptionAdvice;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ExceptionAdviceTest {
     @Test
    public void test(){
        String config = "org/spring/aop/exceptionAdvice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        ForumService forumService =  (ForumService)ctx.getBean("forumServicr");
        forumService.removeForum(10);
    }


}
