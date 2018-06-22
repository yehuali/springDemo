package org.spring.aop;

public class PerformanceMonitor {
    /**通过一个ThreadLocal保存与调用线程相关的性能监视信息
     * ThreadLocal是将非线程安全类改造为线程安全类的“法宝”
     */
    private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();
    //启动对某一目标方法的性能监视 method为目标类方法全县定名
    public static void begin(String method){
        System.out.println("begin monitor....");
        MethodPerformance mp = new MethodPerformance(method);
        performanceRecord.set(mp);
    }

    public static void end(){
        System.out.println("end monitor....");
        MethodPerformance mp = performanceRecord.get();
        mp.printPerformance();
    }
}
