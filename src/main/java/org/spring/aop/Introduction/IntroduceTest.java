package org.spring.aop.Introduction;

import org.junit.Test;
import org.spring.aop.ForumService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntroduceTest {
    @Test
    public void introduce(){
        String configPath = "org/spring/aop/Introduction/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService)ctx.getBean("forumService");
        //默认情况下，未开启性能监视功能
        forumService.removeForum(10);
        forumService.removeTopic(1022);

        Monitorable monitorable = (Monitorable) forumService;
        monitorable.setMonitorActive(true);

        forumService.removeForum(10);
        forumService.removeTopic(1022);
    }
}
