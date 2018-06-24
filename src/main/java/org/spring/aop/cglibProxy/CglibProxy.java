package org.spring.aop.cglibProxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.spring.aop.PerformanceMonitor;

import java.lang.reflect.Method;

/**
 * CGLib采用动态创建子类的方式生成代理对象，所以不能对目标类的final或private方法进行代理
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        PerformanceMonitor.begin(obj.getClass().getName() +"."+method.getName());
        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj,args);
        PerformanceMonitor.end();
        return result;
    }
}
