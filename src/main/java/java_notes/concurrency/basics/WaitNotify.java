package java_notes.concurrency.basics;

import java.util.concurrent.locks.Lock;

public class WaitNotify {

    public static void main(String []args) throws InterruptedException {

        Object obj = new Object();

        Runnable task = () -> {
            synchronized(obj) {
                try {
                    obj.wait();     // поток кладется в пул ожидания, связанный с этим объектом
                } catch(InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            System.out.println("thread");
        };

        Thread taskThread = new Thread(task);
        taskThread.start();

        Thread.sleep(3000);

        System.out.println("main");

        synchronized(obj) {
            obj.notify();   // будим произвольный поток из пула ожидания, связанного с этим объектом
        }
    }
}
