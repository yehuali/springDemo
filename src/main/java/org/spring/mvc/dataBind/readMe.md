1.数据转换
  Java标准的PropertyEditor：将一个字符串转换为一个Java对象
  --->以便根据界面的输入或配置文件中的配置字符串构造出一个JVM内部的Java对象
 
 1.1ConversionService
    1.1.1利用ConversionServiceFactoryBean在Spring的上下文定义一个ConversionService
           --->在Bean属性配置及Spring MVC处理方法入参绑定等场合使用它进行数据转换
    1.1.2 通过CustomEditorConfigurer注册自定义属性编辑器必须实现PropertyEditor接口
          --->注册到ConversionServiceFactoryBean中的自定义转换器必须实现接口？
      1.1.2.1 Spring支持3种类型转换器：
           Converter<S,T>
           ConverterFactory
           GenericConverter:ConvertiblePair封装了源类型和目标类型
                            TypeDescriptor包含需转换类型对象所在宿主类的信息
    1.1.3 ConversionServiceFactory的converters属性可接受以上3种转换器，并将转换逻辑统一封装到一个ConversionService实例对象中
    
          
                            
             
           
      
    