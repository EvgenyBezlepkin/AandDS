package java_notes.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorsEx {

    /*
    public interface Executor {
        void execute(Runnable command);
    }
    */

    public static void main(String[] args) {

        ArrayList<String> al = new ArrayList<>();
        
        newSingleThreadExecutor(al);
        newCachedThreadPool();
        newFixedThreadPool();

        newSingleThreadScheduledExecutor();
        newScheduledThreadPool();

    }

    private static void newScheduledThreadPool() {
        // Creates a thread pool that can schedule commands to run after a
        // given delay, or to execute periodically.
        ExecutorService e2 = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 50; i++) {
            e2.execute(() -> {
                System.out.print("1");
            });
        }
        e2.shutdown();
    }

    private static void newSingleThreadScheduledExecutor() {
        // Creates a single-threaded executor that can schedule commands
        // to run after a given delay, or to execute periodically.
        ExecutorService e2 = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 50; i++) {
            e2.execute(() -> {
                System.out.print("1");
            });
        }
        e2.shutdown();
    }

    private static void newFixedThreadPool() {
        // Creates a thread pool that reuses a fixed number of threads
        // operating off a shared unbounded queue.
        ExecutorService e2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            e2.execute(() -> {
                System.out.print("1");
            });
        }
        e2.shutdown();
    }

    private static void newCachedThreadPool() {
        // Creates a thread pool that creates new threads as needed, but
        // will reuse previously constructed threads when they are available
        ExecutorService e2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            e2.execute(() -> {
                System.out.print("1");
            });
        }
        e2.shutdown();
    }

    private static void newSingleThreadExecutor(ArrayList<String> al) {
        // Tasks are guaranteed to execute sequentially,
        // and no more than one task will be active at any given time.
        ExecutorService e1 = null;
        try {
            e1 = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 5; i++) {
                e1.execute(() -> al.add("hello"));
            }
            al.forEach(System.out::print);
        } finally {
            if (e1 != null) {
                e1.shutdown();
            }
        }
        e1.shutdown();
    }

    private static void threadPoolExecutor() {
        new ThreadPoolExecutor(
                1,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );
    }


}
