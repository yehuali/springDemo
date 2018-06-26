视图作用：渲染模型数据
实现视图模型和具体实现技术的解耦
  -->Spring在org.springframework.web.servlet定义一个高度抽象的View接口
     1.String getContentType():视图对应的MIME类型，如text/html
     2.void render(Map model,HttpServletRequest request,HttpServletResponse response):将模型数据以某种MIME类型渲染出来

视图对象Bean是无状态的，不会有线程安全问题
不同类型的视图实现技术对应不同的View实现类：位于org.springframework.web.servlet.view包中


视图解析器：实现了ViewResolver接口
-->每种解析策略对应一个具体的视图解析器实现类
View resolveViewName(String viewName,Locale locale)
-->根据逻辑视图名和本地化对象得到一个视图对象


     