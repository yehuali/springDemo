package org.spring.aop.proxy;

import org.junit.Test;
import org.spring.aop.ForumService;
import org.spring.aop.ForumServiceImpl;

import java.lang.reflect.Proxy;

public class ForumServiceTest {
    @Test
    public void proxy(){
        //被代理的目标业务类
        ForumService target = new ForumServiceImpl();
        //将目标业务类和横切代码编织到一起
        PerformanceHandler handler = new PerformanceHandler(target);
        /**
         * JDK代理：
         *  根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
         *   1.类加载器
         *   2.创建代理实例所需实现的一组接口
         *   3.整合业务逻辑和横切逻辑的编制器对象
         */

        ForumService proxy = (ForumService)Proxy.newProxyInstance(target.getClass().getClassLoader(),
                                                    target.getClass().getInterfaces(),
                                                    handler);
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }
}
