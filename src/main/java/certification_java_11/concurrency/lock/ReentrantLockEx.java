package certification_java_11.concurrency.lock;


import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockEx {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockEx rle = new ReentrantLockEx();
        ReentrantLock rl = new ReentrantLock();
        Resource res = rle.new Resource(0);

        Runnable r = () -> {
            rl.lock();
                try {
                    res.inc();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            rl.unlock();
        };

        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(100);

//        System.out.println(rl.tryLock());     // dont finish main thread
//        rl.lockInterruptibly();
        System.out.println(rl.isLocked());
        System.out.println(rl.getHoldCount());


        System.out.println(res.i);

    }


    class Resource {
        int i;

        public Resource(int i) {
            this.i = i;
        }

        void inc() {
            i++;
        }
    }


}
