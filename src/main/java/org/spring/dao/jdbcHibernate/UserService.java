package org.spring.dao.jdbcHibernate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    private HibernateTemplate hibernateTemplate;
    private ScoreService scoreService;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
        this.hibernateTemplate = hibernateTemplate;
    }

    @Autowired
    public void setScoreService(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    public void logon(String userName){
        //通过Hibernate技术访问数据
        System.out.println("befor updateLastLogonTime()..");
        updateLastLogonTime(userName);
        System.out.println("end updateLastLogonTime()..");

        //通过JDBC技术访问数据
        System.out.println("before scoreService.addScore()..");
        scoreService.addScore(userName,20);
        System.out.println("end socreService.addScore()..");
    }

    //默认情况下,Hibernate对数据的更改只记录在一级缓存中，等到事务提交或显示调用flush()方法时才会将一级缓存中的数据同步到数据库中
    public void updateLastLogonTime(String userName){
        User user = hibernateTemplate.get(User.class,userName);
        user.setLastLogonTime(1111);
        hibernateTemplate.update(user);

        hibernateTemplate.flush();
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("org/spring/dao/jdbcHibernate/jdbcHibernate.xml");
        UserService service = (UserService)ctx.getBean("userService");
        service.logon("zhangjie");
    }
}
