Spring容器启动三大要件：1.Bean定义信息 2.Bean实现类 3.Spring本身
  基于XML配置，则Bean定义信息和Bean实现类本身是分离的
  
@Component("userDao")  <---->  <bean id="userDao" class="xxxxx">
@Repository:用于对DAO实现类进行标注
@Service:用于对service实现进行标注
@Controller:用于对Controller实现类进行标注

定义命名空间： xmlns:context="http://www.springframework.org/schema/context"
               xsi:schemaLocation="http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd"
               
<context:componnet-scan/> 的use-default-filters属性默认值为true
 --->表示默认会对标注@Component @Controller @Service 及 @Reposity的Bean进行扫描
     必须将use-default-filters属性设置为false
     
@Autowired默认按类型（byType）匹配的方式在容器查找匹配的Bean
  1.如果没有匹配，spring启动报NoSuchBean异常  ---->@Autowired(required=false)避免报错
  2.使用@Qualifier指定注入Bean的名称（如果容器中有一个以上匹配的Bean）
    -->Spring容器大部分Bean都是单实例的，一般不需要
  3.对类中集合类的变量或方法入参进行@Autowired标注，将容器中类型匹配的所有Bean都注入进来
  4.@Lazy注解必须同时标注在属性及目标Bean上，才能实施延迟依赖注入
  
  