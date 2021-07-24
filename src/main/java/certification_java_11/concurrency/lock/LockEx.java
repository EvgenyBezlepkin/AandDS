package certification_java_11.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockEx {

    /*
    There are few differences between the use of synchronized block and using Lock:
    A synchronized block doesn't support the fairness, but Lock supports.
    A thread is blocked if it can't get an access to the synchronized block. The Lock API provides tryLock().
    A thread which is in “waiting” state can't be interrupted. The Lock API provides a lockInterruptibly().
     */

    /*
    void lock() – acquire lock if it's available; otherwise thread is blocked until the lock is released
    void lockInterruptibly() – it allows the blocked thread to be interrupted
    boolean tryLock() – non-blocking version of lock() method
    void unlock() – unlocks the Lock instance
     */

    public static void main(String[] args) {

        LockEx s = new LockEx();

        ReentrantLock rl = new ReentrantLock();
        CommonResource cr = s.new CommonResource();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(s.new CountThread(cr, rl));
            t.setName("Thread " + i);
            t.start();
        }
    }

    class CommonResource {
        int x = 0;

    }

    class CountThread implements Runnable {

        private final CommonResource res;
        private final ReentrantLock rl;

        public CountThread(CommonResource res, ReentrantLock rl) {
            this.res = res;
            this.rl = rl;
        }

        public void run() {
            rl.lock();
            res.x = 1;
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + " -> " + res.x++);
                sleep(100);
            }
            rl.unlock();
        }

        private void sleep(int x) {
            try {
                Thread.sleep(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}

