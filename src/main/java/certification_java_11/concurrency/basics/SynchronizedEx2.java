package certification_java_11.concurrency.basics;


public class SynchronizedEx2 {

    public static void main(String[] args) {

        SynchronizedEx2 s = new SynchronizedEx2();

        CommonResource2 cr = s.new CommonResource2();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(s.new CountThread2(cr));
            t.setName("Thread " + i);
            t.start();
        }
    }


    class CommonResource2 {
        int x = 0;

        synchronized void increment() {
            x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
                x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    class CountThread2 implements Runnable {

        private final CommonResource2 res;

        CountThread2(CommonResource2 res) {
            this.res = res;
        }

        public void run() {
            res.increment();
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



