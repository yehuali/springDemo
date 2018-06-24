package org.spring.aop.exceptionAdvice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 常用应用场景：当参与事务的某个DAO发生异常时，事务管理器必须回滚事务
 * ThrowsAdvice没有定义任何方法，是一个标签接口，在运行期间使用反射机制自行判断
 * 方法名必须为afterThrowing
 * 方法入参规定：前三个入参时可选的，最后一个入参时Throwable或其子类
 * ============================================================
 * 标签接口：没有任何方法和属性的接口
 *   1.通过标签接口标识同一类型的类，这些类本身可能并不具有相同的方法，如Advice接口
 *   2.通过标签接口使程序或JVM采取一些特殊处理
 */
public class TransactionManager implements ThrowsAdvice {
    //定义增强逻辑
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
        System.out.println("-------------------------");
        System.out.println("method:"+method.getName());
        System.out.println("抛出异常："+ex.getMessage());
        System.out.println("成功回滚事务。");
    }
}
