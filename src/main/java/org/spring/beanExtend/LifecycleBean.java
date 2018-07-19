package org.spring.beanExtend;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * InitializingBean接口、Disposable接口可以和init-method、destory-method配合使用，接口执行顺序优先于配置
 */
public class LifecycleBean implements InitializingBean,DisposableBean {
    private String lifeCycleBeanName;

    public void setLifeCycleBeanName(String lifeCycleBeanName) {
        System.out.println("Enter LifecycleBean.setLifeCycleBeanName(), lifeCycleBeanName = " + lifeCycleBeanName);
        this.lifeCycleBeanName = lifeCycleBeanName;
    }
    //Bean生命周期结束前调用destory()方法做一些收尾工作
    @Override
    public void destroy() throws Exception {
        System.out.println("Enter LifecycleBean.destroy()");
    }
    //在Bean属性都设置完毕后调用afterPropertiesSet()方法做一些初始化的工作
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Enter LifecycleBean.afterPropertiesSet()");
    }

    public void beanStart()
    {
        System.out.println("Enter LifecycleBean.beanStart()");
    }

    public void beanEnd()
    {
        System.out.println("Enter LifecycleBean.beanEnd()");
    }
}
