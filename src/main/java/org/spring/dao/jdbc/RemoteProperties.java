package org.spring.dao.jdbc;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;

/**
 * 把配置文件放在远程的git或svn这类云平台之上
 * BeanFactoryPostProcessor类的postProcessBeanFactory方法调用是在Bean定义解析后，因此当前的beanFactory参数中有所有bean定义
 */
public class RemoteProperties implements InitializingBean,FactoryBean<Properties> {

    private String url = null;

    private Properties properties = new Properties();

    @Override
    public Properties getObject() throws Exception {
        return properties;
    }

    @Override
    public Class<?> getObjectType() {
        return properties.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadProperty();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void loadProperty(){
        ZkConfigManager  zkManager = new ZkConfigManager();
        Config config = zkManager.getConfig();
        properties.put("jdbc.driverClass",config.getJdbcDriverClassName());
        properties.put("jdbc.url",config.getJdbcUrl());
        properties.put("jdbc.username",config.getJdbcUserName());
        properties.put("jdbc.password",config.getJdbcPassword());
    }
}
