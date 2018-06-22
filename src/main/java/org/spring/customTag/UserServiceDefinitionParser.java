package org.spring.customTag;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.w3c.dom.Element;
//用户服务标签解析类
public class UserServiceDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        //创建BeanDefinitionBuilder创建Bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserService.class);
        //获取自定义标签的属性
        String dao = element.getAttribute("dao");
        beanDefinitionBuilder.addPropertyReference("userDao",dao);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //注册Bean定义
        parserContext.registerComponent(new BeanComponentDefinition(beanDefinition,"userService"));

        return null;
    }
}
