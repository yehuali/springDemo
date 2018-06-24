package org.spring.aop.cglibProxy;

import org.junit.Test;
import org.spring.aop.ForumServiceImpl;

public class ForumServiceTest {
    @Test
    public void proxy(){
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl)proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }
}
