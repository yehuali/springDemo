package org.spring.aop.proxy;

import org.spring.aop.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {
    private Object target;

    public PerformanceHandler(Object target) {//target为目标业务类
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() +"."+method.getName());
        //通过反射方法调用业务类的目标方法
        Object obj = method.invoke(target,args);
        PerformanceMonitor.end();
        return obj;
    }
}
