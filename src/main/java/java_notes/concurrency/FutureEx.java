package java_notes.concurrency;

import java.util.concurrent.*;

public class FutureEx {

    public static void main(String[] args)  {

        Callable<Integer> c = () -> {
            Thread.sleep(5000);
            return 5;
        };

        ExecutorService es = null;
        try {
            es = Executors.newSingleThreadExecutor();

            Future<Integer> submit = es.submit(c);

            submit.cancel(true);

            if (!submit.isCancelled()) {
                System.out.println(submit.get());
            }

        } catch (Exception e) {
            System.out.println("Exception");
        } finally {
            if (es != null) {
                es.shutdown();
            }
        }
        


    }
}
