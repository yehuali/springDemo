package org.spring.aop;

public class ForumServiceImpl implements ForumService {
    @Override
    public void removeTopic(int topicId) {
        //开始对该方法进行性能监视
//        PerformanceMonitor.begin("org.spring.aop.ForumServiceImpl.removeTopic");
        System.out.println("模拟删除Topic记录："+topicId);
        try{
            Thread.currentThread().sleep(20);
        }catch (Exception e){
            throw  new  RuntimeException(e);
        }
        //结束对该方法的性能监视
//        PerformanceMonitor.end();
    }

    @Override
    public void removeForum(int forumId) {
//        PerformanceMonitor.begin("org.spring.aop.ForumServiceImpl.removeForum");
        System.out.println("模拟删除Forum记录："+forumId);
        try{
            Thread.currentThread().sleep(20);
        }catch (Exception e){
            throw  new  RuntimeException(e);
        }
//        PerformanceMonitor.end();


    }
}
