package org.spring.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserJdbcWithoutTransManagerService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addSource(String userName,int toAdd){
        String sql = "UPDATE t_user u set  u.score = u.score + ? where user_name = ?";
        jdbcTemplate.update(sql,toAdd,userName);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("org/spring/dao/jdbc/jdbcWithoutTx.xml");
        UserJdbcWithoutTransManagerService service = (UserJdbcWithoutTransManagerService)ctx.getBean("userService");
        JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();

        //检查数据源autoCommit的设置
        System.out.println("autoCommit:"+basicDataSource.getDefaultAutoCommit());

        //插入一条记录，初始分数为0
        jdbcTemplate.execute("INSERT into t_user(user_name,password,score,last_logon_time) VALUES('tom','123456',10,"+System.currentTimeMillis()+")");

        //调用工作在无事务环境下的服务类方法，将分数添加20分
        service.addSource("tom",20);

        //查看此时用户的分数
        int score = jdbcTemplate.queryForObject("SELECT score From t_user WHERE user_name ='tom'",Integer.class);
        System.out.println("socre:"+score);
        jdbcTemplate.execute("DELETE FROM  t_user WHERE user_name = 'tom'");
    }
}
