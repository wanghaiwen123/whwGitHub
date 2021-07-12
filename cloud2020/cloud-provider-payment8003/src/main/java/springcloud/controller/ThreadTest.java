package springcloud.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadTest {


    public static void main(String[] args) {
//        Threaddd th = new Threaddd();
//        Threaddd th1 = new Threaddd();
//        th.setName("我是主线程");
//        th1.setName("我是线程");
//        th.start();
//        th1.start();

        ThreadRunn threadRunn = new ThreadRunn();
        Thread th2 = new Thread(threadRunn);
        Thread th3 = new Thread(threadRunn);
        th2.setName("我是主");
        th3.setName("我是分");
        th2.start();
        th3.start();
    }

}
class Threaddd extends Thread{
    private static int i=200;
    @Override
    public  void run() {
        while (true){
            if (i > 0) {
                System.out.println("线程:"+getName()+"  "+i);
                i--;
            }else{
                break;
            }

        }
    }
}

class ThreadRunn implements Runnable{
    private static int i=200;
    @Override
    public  void run() {
        while (true){
            if (i > 0) {
                System.out.println("线程:"+Thread.currentThread().getName()+"  "+i);
                i--;
            }else{
                break;
            }

        }
    }
}