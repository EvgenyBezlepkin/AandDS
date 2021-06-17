package java_notes.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceEx {

    ExecutorServiceInner d = new ExecutorServiceInner();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new ExecutorServiceEx().d.run();

    }


    class ExecutorServiceInner {
        // Prepare data
        // 1
        private Runnable r = () -> System.out.println(Thread.currentThread().getName());

        // 2
        private Callable<Integer> c1 = () -> 5;
        private Callable<Integer> c2 = () -> 6;
        private Callable<Integer> c3 = () -> 7;

        // 3
        private Collection<Callable<Integer>> cCol = new ArrayList<>();

        public ExecutorServiceInner() {
            cCol.add(c1);
            cCol.add(c2);
            cCol.add(c3);
        }

        void run() throws InterruptedException, ExecutionException {

            // create executor
            ExecutorService es = Executors.newSingleThreadExecutor();


            /* void execute(Runnable r)*/
            es.execute(d.r);


            /* Future<?> submit(Runnable r)*/
            es.submit(r);


            /* <T> Future<T> submit(Callable<T> task)*/
            Future<Integer> submit = es.submit(c1);
            System.out.println(submit.get());


            /* <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException
               Executes the given tasks, returning a list of Futures holding
               their status and results when all complete.
             */
            List<Future<Integer>> futures = es.invokeAll(cCol);
            printCollectionOfFuture(futures);


            /* <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException
               Executes the given tasks, returning the result of one that has completed successfully (i.e., without throwing an exception), if any do.
             */
            Integer integer = es.invokeAny(cCol);
            System.out.println(integer);

            checkCompleteExecutorService(es);   // false, false


            /* List<Runnable> shutdownNow()
               Attempts to stop all actively executing tasks, halts the processing of waiting tasks,
               and returns a list of the tasks that were awaiting execution.
             */
            List<Runnable> runnables = es.shutdownNow();
            runnables.forEach(x -> System.out.println(x.toString()));


            /*
               Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
               Invocation has no additional effect if already shut down. This method does not wait for previously submitted tasks to complete execution.
               Use awaitTermination to do that.
            */
            es.shutdown();


            checkCompleteExecutorService(es);   // true, false


            /*
               Blocks until all tasks have completed execution, or the timeout occurs, or the current thread is interrupted,
               whichever happens first.
            */
            es.awaitTermination(500, TimeUnit.MILLISECONDS);


            checkCompleteExecutorService(es);   // true, true

        }


        private void printCollectionOfFuture(List<Future<Integer>> futures) {
            futures.forEach(x -> {
                try {
                    System.out.println(x.get());
                } catch (Exception e) {
                }
            });
        }

        private void checkCompleteExecutorService(ExecutorService es) {
            System.out.println(es.isShutdown());
            System.out.println(es.isTerminated());
        }
    }

}
