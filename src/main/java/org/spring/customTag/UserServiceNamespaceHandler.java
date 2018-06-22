package org.spring.customTag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 将UserServiceDefinitionParser解析器注册到Spring命名空间解析器
 * Spring如何解析自定义标签
 *   在源码resource目录创建META-INF文件夹，并在其中创建spring.handlers和spring.schemas
 *   --->告诉spring自定义标签的文档结构及解析它的类
 */
public class UserServiceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user-service",new UserServiceDefinitionParser());
    }
}
