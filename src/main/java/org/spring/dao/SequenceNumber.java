package org.spring.dao;

public class SequenceNumber {
    //通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    private static class TestClient extends  Thread{
        private SequenceNumber sn;
        public  TestClient(SequenceNumber sn){
            this.sn = sn;
        }

        public void run(){
            for(int i=0;i<3;i++){
                System.out.println("thread["+Thread.currentThread().getName()+"] sn["+sn.getNextNum()+"]");
            }
        }
    }
    //三个线程共享sn，各自产生序列号
    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }
}
