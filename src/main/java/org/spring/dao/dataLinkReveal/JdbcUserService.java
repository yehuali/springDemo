package org.spring.dao.dataLinkReveal;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

@Service("jdbcUserService")
public class JdbcUserService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *方法中显示获得连接，不是方法事务上下文线程绑定的连接，若不手工释放，将被永久占用，造成连接泄露
     */
    @Transactional
    public void logon(String userName){
        try{
            //直接从数据源获取连接，后续程序没有显示释放该连接
            Connection conn =  jdbcTemplate.getDataSource().getConnection();
            String sql = "UPDATE t_user SET last_logon_time=? WHERE user_name = ?";
            jdbcTemplate.update(sql,23244,userName);

            //模拟程序代码的执行时间
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //以异步线程的方式执行JdbcUserService#logon()方法，以模拟多线程的环境
    public static void asynchrLogon(JdbcUserService userService,String userName){
        UserServiceRunner runner = new UserServiceRunner(userService,userName);
        runner.start();
    }

    private static class UserServiceRunner extends Thread{
        private JdbcUserService userService;
        private String userName;

        public UserServiceRunner(JdbcUserService userService,String userName){
            this.userService = userService;
            this.userName = userName;
        }
        public void run(){
            userService.logon(userName);
        }
    }

    //让主执行线程睡眠一段指定的时间
    public static void sleep(long time){
        try {
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //汇报数据源的连接占用情况
    public static void reportConn(BasicDataSource basicDataSource){
        System.out.println("连接数[active:idle]-["+basicDataSource.getNumActive()+":"+basicDataSource.getNumIdle()+"]");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("org/spring/dao/dataLinkReveal/applicationContext.xml");
        JdbcUserService userService = (JdbcUserService) ctx.getBean("jdbcUserService");

        BasicDataSource basicDataSource = (BasicDataSource)ctx.getBean("dataSource");

        //汇报数据源初始连接占用情况
        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.asynchrLogon(userService,"tom");//启动一个异步线程A
        JdbcUserService.sleep(500);

        //此时线程A正在执行JdbcUserService#logon()方法
        JdbcUserService.reportConn(basicDataSource);
        JdbcUserService.sleep(2000);

        //此时线程A所执行的JdbcUserService#logon()方法已经执行完毕
        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.asynchrLogon(userService,"john");//启动一个异步线程B
        JdbcUserService.sleep(500);

        //此时线程B正在执行JdbcUserService#logon()方法
        JdbcUserService.reportConn(basicDataSource);
        JdbcUserService.sleep(2000);

        //此时线程A和B都已完成JdbcUserService#logon()方法的执行
        JdbcUserService.reportConn(basicDataSource);
    }
}
