Spring MVC处理请求的整体过程
（1）始于客户端发出一个Http请求，匹配DispatcherServlet的请求映射路径（在web.xml中指定）
（2）DispatcherServlet根据请求的信息及HandlerMapping的配置找到处理请求的处理器（Handler）
     springMVC中并没有定义一个Handler接口，任何一个Object都可以成为请求处理器
（3）HandlerAdapter对Handler进行封装，以统一的适配器接口调用Handler
（4）处理器完成业务逻辑的处理后返回一个ModelAndView（包含视图逻辑名和模型数据信息）
（5）由ViewResolver完成逻辑视图名到真实视图对象（View）的解析工作
（6）使用View对象对ModelAndView中的模型数据进行视图渲染


HttpMessageConverter<T>:负责将请求信息转换为一个对象（类型为T）
 1.RequestMappingHandlerAdapter使用HttpMessageConverter将请求信息转换为对象，或将对象转换为响应信息
 2.Spring为HttpMessageConverter<T>提供众多的实现类
 3.RequestMappingHandlerAdapter默认装配以下HttpMessageConverter
   3.1 StringHttpMessageConverter
   3.2 ByteArrayHttpMessageConverter
   3.3 SourceHttpMessageConverter
   3.4 AllEncompassingFormHttpMessageConverter

使用HttpMessageConverter<T>
 1.使用@RequestBody 或 @ResponseBody对处理方法进行标注
 2.使用HttpEntity<T> 或 ResponseEntity<T> 作为处理方法的入参或返回值 
     