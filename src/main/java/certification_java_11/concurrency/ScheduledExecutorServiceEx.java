package certification_java_11.concurrency;

import java.util.concurrent.*;

public class ScheduledExecutorServiceEx {

    ExecutorServiceInner d = new ExecutorServiceInner();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new ScheduledExecutorServiceEx().d.run();

    }


    class ExecutorServiceInner {

        private Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };


        void run() throws InterruptedException, ExecutionException {

            ScheduledExecutorService es = Executors.newScheduledThreadPool(2);

//            ScheduledFuture<Integer> schedule = es.schedule(c1, 2, TimeUnit.SECONDS);
//            System.out.println(schedule.get());

            es.scheduleAtFixedRate(r, 2, 2, TimeUnit.SECONDS);

        }
    }
}
