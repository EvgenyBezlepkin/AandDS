package java_notes.concurrency.basics;


public class SynchronizedEx1 {

    public static void main(String[] args) {

        SynchronizedEx1 s = new SynchronizedEx1();

        CommonResource1 cr = s.new CommonResource1();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(s.new CountThread1(cr));
            t.setName("Thread " + i);
            t.start();
        }
    }


    class CommonResource1 {
        int x = 0;

        synchronized void increment(){
            x=1;
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
                x++;
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
        }
    }

    class CountThread1 implements Runnable {

        private final CommonResource1 res;
        CountThread1(CommonResource1 res) {
            this.res = res;
        }

        public void run() {
            // синхронизация на ресурсе дает доступ к этому ресурсу только одному потоку при вызове метода run(),
            // остальные потоки войдут в режим ожидания пока синхронизированный поток не будет завершен
            synchronized (res) {
                res.x = 1;
                for (int i = 0; i < 4; i++) {
                    System.out.println(Thread.currentThread().getName() + " -> " + res.x++);
                    sleep(100);
                }
            }
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



