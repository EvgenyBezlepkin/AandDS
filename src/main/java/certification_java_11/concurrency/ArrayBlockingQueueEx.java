package certification_java_11.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueEx {

    public static final int CAPACITY = 3;

    /*
        ArrayBlockingQueue class is a bounded blocking queue backed by an array.
        By bounded, it means that the size of the Queue is fixed and  cannot be changed.
        Attempts to put an element into a full queue will result in the operation blocking (put).
        Attempts to take an element from an empty queue will also be blocked (take).
        There are non-blocking methods such as add (that throws exception while overflowing)
     */
    ArrayBlockingQueue<String> abq = new ArrayBlockingQueue(CAPACITY, true);

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueueEx blockingQueueEx = new ArrayBlockingQueueEx();

        // do blocking thread while take element from empty queue
        //take(blockingQueueEx);

        //putPollExample(blockingQueueEx, 4);
        blockingQueueEx.abq.forEach(x -> System.out.print(x + " , "));

        // fairness
        //fairnessExample(blockingQueueEx);
        blockingQueueEx.abq.forEach(x -> System.out.print(x + " , "));
        
        killThreads();
    }


    private static void take(ArrayBlockingQueueEx blockingQueueEx) {
        Runnable r0 = () -> {
            try {
                blockingQueueEx.abq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println("testing thread - blocking while attempting to take element from an empty queue");
        new Thread(r0).start();
    }

    private static void poll(ArrayBlockingQueueEx blockingQueueEx) {
        Runnable r2 = () -> {
            blockingQueueEx.abq.poll();
        };
        System.out.println("starting thread - removing 1th element");
        new Thread(r2).start();
    }

    private static void put(ArrayBlockingQueueEx blockingQueueEx, int count) {
        Runnable r1 = () -> {
            try {
                for (int i = 1; i <= count; i++) {
                    blockingQueueEx.abq.put(count + "");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r1).start();
    }

    private static void put(ArrayBlockingQueueEx blockingQueueEx, String s) {
        Runnable r1 = () -> {
            try {
                blockingQueueEx.abq.put(s + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println("starting thread - blocking while putting 4th element");
        new Thread(r1).start();
    }

    private static void putPollExample(ArrayBlockingQueueEx blockingQueueEx, int count) throws InterruptedException {
        // do blocking thread
        put(blockingQueueEx, count);
        Thread.sleep(2000);
        // do unlocking due to removing the element
        poll(blockingQueueEx);
        Thread.sleep(2000);
    }

    private static void fairnessExample(ArrayBlockingQueueEx blockingQueueEx) {
        put(blockingQueueEx, 3);
        put(blockingQueueEx, "A");
        put(blockingQueueEx, "B");
        poll(blockingQueueEx);
        poll(blockingQueueEx);
    }

    private static void killThreads() throws InterruptedException {
        Thread.sleep(2000);
        System.exit(0);
    }

}
