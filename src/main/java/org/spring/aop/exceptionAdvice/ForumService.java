package org.spring.aop.exceptionAdvice;

import java.sql.SQLException;

public class ForumService {
    public void removeForum(int forumId){
        //do something
        throw new RuntimeException("运行异常。");
    }

    public void updateForum(Forum forum) throws Exception{
        //do something
        throw  new SQLException("数据更新操作异常");
    }
}
