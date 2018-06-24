Spring通过org.springframework.aop.Pointcut接口描述切点
Pointcut由ClassFilter和MethodMatcher构成
   1.通过ClassFilter定位到某些特定类上
   2.通过MethodMatcher定位到某些特定方法上
   

切点类型
1.静态方法切点
  StaticMethodMatcherPointcut是静态方法切点的抽象基类，默认情况下它匹配所有的类
  StaticMethodMatcherPointcut两个主要子类：
   （1）NameMatchMethodPointcut:提供简单字符串匹配方法签名
   （2）AbstractRegexpMethodPointcut：使用正则表达式匹配方法签名

2.动态方法切点：DynamicMethodMatcherPointcut是动态方法切点的抽象基类，默认匹配所有的类
3.注解切点：AnnotationMatchingPointcut支持在Bean中直接通过Java5.0注解标签定义的切点
4.表达式切点：ExpressionPointcut接口主要是为了支持AspectJ切点表达式语法而定义的接口
5.流程切点：ControlFlowPointcut实现类表示控制流程切点
    根据程序执行堆栈的信息查看目标方法是否由某一个方法直接或间接发起调用
    

切面类型
1.Advisor:一般切面，代表横切的连接点的所有目标类的所有方法
2.PointcutAdvisor:代表具有切点的切面，包含Advice和Pointcut两个类
3.IntroductionAdvisor:代表引介切面

PointcutAdvisor主要6个具体的实体类
1.DefaultPointcutAdvisor：任意Pointcut和Advice
2.NameMatchMethodPointcutAdvisor：按方法名定义切点的切面
3.RegexpMethodPointcutAdvisor:按正则表达式匹配方法名进行切点定义的切面
4.StaticMethodMatcherPointcutAdvisor:静态方法匹配其切点定义的切面

   