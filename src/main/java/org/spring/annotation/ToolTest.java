package org.spring.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

public class ToolTest {
    @Test
    public void tool(){
        Class clazz = ForumService.class;
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println(methods.length);
        for(Method method : methods){
            NeedTest nt = method.getAnnotation(NeedTest.class);
            if(nt != null){
                if(nt.value()){
                    System.out.println(method.getName() + "()需要测试");
                }else {
                    System.out.println(method.getName() +"()不需要测试");
                }
            }
        }
    }
}
