package java_notes.concurrency.basics;


public class SynchronizedEx {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Runnable task = () -> {
            synchronized (obj) {
                System.out.println("thread");
            }
        };

        Thread th1 = new Thread(task);
        th1.start();

        // создаем новый поток для блокировки объекта obj
        // в это время в главном потоке делаем синхронизацию (лок) по этому эе объекту
        // в результате первй поток ждем пока не завершатся операции в основном потоке
        synchronized (obj) {
            System.out.println(th1.getState()); // Runnable

            for (int i = 0; i < 8; i++) {
                Thread.currentThread().sleep(1000);
                System.out.print("  " + i);
            }
            System.out.println();
        }

    }
}
