1.反射机制实例化Bean需提供大量的配置信息，采用FactoryBean工厂类接口定制实例化Bean的逻辑
2.当配置文件中<bean>的class属性配置的实现类是FactoryBean时，通过方法返回的不是FactoryBean本身，
   而是FactoryBean#getObject()方法所返回的对象
   --->相当于FactoryBean#getObject()代理了getBean()方法
   