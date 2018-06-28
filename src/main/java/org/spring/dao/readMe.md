DAO异常体系都继承于DataAccessException(继承于NestedRuntimeException)
NestedRuntimeException异常以嵌套的方式封装了源异常
  -->getCause()：获取原始的异常信息

Spring以分类手法建立了异常分类目录 
  --->可以选择感兴趣的异常加以处理

Spring对新的持久化技术提供支持--->为其定义一个对应的子异常就可以（符合"开闭原则"）
（1）准备资源
（2）启动事务
（3）在事务中执行具体的数据访问操作
（4）提交/回滚事务
（5）关闭资源，处理异常
--->将数据访问中固定和变化的部分分开，同时保证模板类是线程安全的
   变化部分通过回调接口开放出来：数据访问和结果返回
   
持久化技术的支持类都继承于dao.support.DaoSupport类（实现了InitializingBean接口）
在afterPropertiesSet()接口方法中检查模板对象和数据源是否被正确设置
===============================================================================================================

ACID:原子性 一致性 隔离性  持久性
数据库管理系统一般采用重执行日志来操作原子性、一致性和持久性
重执行日志记录了数据库变化的每一个动作
-->发生错误，数据库可根据重执行日志撤销已经执行的操作

数据库管理系统采用数据库锁机制保证事务的隔离性
1.数据并发的问题
  -->数据读问题：
        脏读：读到其他事务尚未提交的数据
        不可重复读:读到已经提交事务的更改数据（更改或删除） --->数据添加行级锁
        幻象读：读到其他已经提交事务新增数据 ---> 表级锁
     数据更新问题：第一类丢失更新和第二类丢失更新
     
数据库锁机制
锁定对象：表锁定和行锁定
并发事务锁定：
        共享锁定：防止独占锁定，但允许其他的共享锁定
        独占锁定：防止其他的独占锁定，也防止其他的共享锁定
        
 事务隔离级别：
    直接使用锁管理麻烦，数据库为用户提供自动锁机制
    --->用户指定会话的事务隔离级别 
        如果一个资源上的锁数目太多，自动进行锁升级提高系统的运行性能
  
   READ UNCOMMITED
   READ COMMITTED
   REPEATABLE READ
   SERIALIZABLE
   
Savepoint接口允许用户将事务分割为多个阶段，用户可以指定回滚到事务的特定保存点

=======================================================================================
ThreadLocal为每个使用该变量的线程分配一个独立的变量副本
 1.InheritableThreadLocal继承于ThreadLocal
    --->自动为子线程复制一份从父线程那里继承而来的本地变量
    
 ThreadLocal接口方法
   1.void set(Object value):设置当前线程的线程局部变量的值
   2.public Object get():返回当前线程所对应的线程局部变量
   3.public void remove()
   4.protected Object initialValue():返回该线程局部变量的初始值，延迟调用，在get()或set()执行，并且仅执行一次
   
 同步机制采用了“以时间换空间”的方式，访问串行化，对象共享化
 ThrealLocal采用了"以空间换时间"的方式，访问并行化，对象独享化
 
 ===================================================================================
 
在单数据源的情况下，Spring直接使用底层的数据源管理事务
在多数据源的情况下，Spring通过引用应用服务器中的JNDI资源完成JTA事务
--->采用相同的事务管理模型

事务管理关键抽象
在Spring事务管理SPI(Service Provider Interface)的抽象层主要包括3个接口
PlatformTransactionManager：根据TransactionDefinition提供的事务属性配置信息创建事务
  1.TransactionStatus getTransaction(TransactionDefinition definition)：该方法根据事务定义信息从事务环境中返回一个已存在的事务，或者创建一个新的事务
  2.commit(TransactionStatus status):根据事务的状态提交事务
  3.rollback(TransactionStatus status):将事务回滚
TransactionDefinition:描述事务隔离级别、超时时间、是否为只读事务和事务传播规则等控制食物具体行为的事务属性
                      --->可通过XML配置或注解描述
                    1.事务隔离
                       ISOLATION_READ_UNCOMMITTED
                       ISOLATION_READ_COMMITTED
                       ISOLATION_REPEATABLE_READ
                       ISOLATION_SERIALIZABLE
                    2.事务传播：一个事务中执行的所有代码都会运行于同一事务上下文中
                    .....                                  
TransactionStatus：描述激活事务的状态
   1.该接口继承于SavepointManager接口（基于JDBC 3.0保存点的分段事务控制能力提供了嵌套事务的机制）
     `1.1 Object createSavepoint():创建一个保存点对象
      1.2 void rollbackToSavepoint(Object savepoint):将事务回滚到特定的保存点上，被回滚的保存点将自动释放
      1.3 void releaseSavepoint(Object savepoint):释放一个保存点。如果事务提交，则所有的保存点会自动释放，无须手工清除
   2.TransactionStatus扩展方法
      2.1`boolean hasSavepoint():判断当前事务是否在内部创建一个保存点
                                 --->为了支持Spring的嵌套事务而创建的
      2.2 boolean isNewTransaction():判断当前事务是否是一个新的事务
      2.3 boolean isCompleted():判断当前事务是否已经结束
      2.4 boolean isRollbackOnly():判断当前事务是否已经被标识为rollback-only
      2.5 void setRollbackOnly():该标识通知事务管理器只能将事务回滚
      
Spring的事务管理器实现类
 Spring将事务管理委托给底层具体的持久化实现框架来完成
 -->Spring为不同的持久化框架提供了PlatformTransactionManager接口的实现类
 

事务同步管理器
 Spring将JDBC的Connection、Hibernate的session等资源在同一时刻是不能多线程共享的
 -->为了让DAO、Service类做到singleton
    Spring的事务同步管理器类SynchronizationManager使用ThreadLocal为不同事务线程提供了独立的资源副本
    Spring为不同的持久化技术提供了一套从TransactionSynchronizationManager中获取对应线程绑定资源的工具类
    --->从此DAO(必须基于模板类或资源获取工具类创建的DAO) 和 Service(必须采用Spring事务管理机制) 
    
 
Spring建议在业务实现类上使用@Transactional注解
  -->也可以在业务接口上使用@Transactional注解，但注解不能被继承
      --->在业务接口标注的@Transaction注解不会被业务实现类继承
 子类代理：<tx:annotation-driven transaction-manager="txManager" proxy-target-class="true">
 
方法处的注解会覆盖类定义处的注解

通过AspectJ LTW引入事务切面：用于为使用了@Transactional注解的业务类提供事务增强
在类路径META-INF目录下提供如下AspectJ配置文件

=======================================================
mysql下载及安装参考：
https://www.cnblogs.com/lmh2072005/p/5656392.html
https://blog.csdn.net/BruceLeeNumberOne/article/details/80173796
停止：net stop mysql
启动：net start mysql

无法启动：参考https://dev.mysql.com/doc/refman/8.0/en/data-directory-initialization-mysqld.html
删除之前建的data文件夹，由mysql自己创建即可 否则mysql初始化会报错

======================================================================
将日志调至DEBUG模式得出结论：
  在相同线程中进行相互嵌套调用的事务方法工作在相同的事务中
  不同线程下的事务方法工作在独立的事务中
  
 =================================
 Hibernate是一个ORM实现方案，对底层SQL的控制不太方便
 MyBatis通过模板化技术让用户方便地控制SQL，但没有Hibernate那样高的开发效率
 自由度最高的是Spring JDBC
 
 一个应用采用多种数据访问技术：1.采用ORM技术框架 2.采用偏JDBC的底层技术
 --->Spring为每种数据访问技术提供相应的事务管理器
 ORM技术（Hibernate、JPA、JDO）
 JDBC技术(Spring JDBC、Mybatis)
 -->前者的会话(Session)是对后者连接(Connection)的封装，所以只要直接采用前者的事务管理器就可以了
 
 
 

    

      
     
  

   
