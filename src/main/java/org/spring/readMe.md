如果用户使用Spring的WebApplicationContext,则可使用另外3种Bean的作用域：request、session和globalSession
利用Http请求监听器进行配置
  <listener>
    <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
  </listener>
  
  ContextLoaderListener 实现了ServletContextListener（负责监听Web容器启动和关闭事件）
  RequestContextListener 实现了ServletRquestListener监听器接口（负责监听Http请求事件，每一次请求都会通知该监听器）
  
  1.request作用域
     每次HTTP请求调用bean时，Spring容器会创建一个新的bean，请求处理完毕后，就会销毁这个Bean
  2.session作用域
      Session中的所有Http请求都共享同一个Bean。当Http Session结束后，实例才被销毁
  3.gloabalSession作用域
     组成Portlet Web应用的所有子Protlet共享
     
 作用域依赖问题
    Web相关作用域的Bean注入singleton或prototype的Bean中
    ---》需要Spring AOP进行额外的设置
   
   <bean name="car" class="xxxxx" scope="request">
        <aop:scoped-proxy/>  创建代理
   </bean>
   
   <bean id="boss" class="xxx1">
        <property name="car" ref="car" />
   </bean>
     1.spring AOP将启用动态代理智能地判断boss Bean位于哪个Http请求线程中，并从对应的Http请求线程域中获取对应的car Bean
     2.注入boss 中的car已经不是原来的car，而是car的动态代理对象
       --->spring在动态代理子类中判断当前boss需要取哪个Http请求相关的car
     3.一个Http请求对应一个独立的线程
     4.Java语言只能对接口提供自动代理，对类代理需要CGLib的支持
     


   
     
  
