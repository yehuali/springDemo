package org.spring.aop.Introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.spring.aop.PerformanceMonitor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * 引介增强接口IntroductionInterceptor，该接口没有定义任何方法
 * --->Spring为该接口提供了DelegatingIntroductionInterceptor实现类，扩展该类定义自己的引介增强类
 */
public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {
   private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<Boolean>();

    @Override
    public void setMonitorActive(boolean active) {
        MonitorStatusMap.set(active);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;
        if(MonitorStatusMap.get() != null && MonitorStatusMap.get()){
            PerformanceMonitor.begin(mi.getClass().getName()+"."+mi.getMethod());
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        }else{
            obj =super.invoke(mi);
        }
        return obj;
    }
}
