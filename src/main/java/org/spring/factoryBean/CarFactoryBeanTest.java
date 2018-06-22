package org.spring.factoryBean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class CarFactoryBeanTest {
    /**
     *Spring通过反射机制发现CarFactoryBean实现FactoryBean接口
     *   --->Spring容器调用接口方法CarFactoryBean#getObject()返回工厂类创建的对象
     * 如果希望获取CarFactoryBean的实例，需要在beanName前加上"&"前缀
     */
    public static void main(String[] args) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:org/spring/factoryBean/beans.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
//        Car car =  (Car)factory.getBean("car1");
        CarFactoryBean carFactoryBean =  (CarFactoryBean)factory.getBean("&car1");
        System.out.println(carFactoryBean.getCarInfo());
    }
}
