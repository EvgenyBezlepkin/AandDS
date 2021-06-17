package java_notes.concurrency;

public class RunnableEx{

    /*
    public interface Runnable {
        public abstract void run();
    }
    */

    public static void main(String[] args) {

        // примеры создания потоков через интерфейс Runnable
        createThreadViaRunnable();

        // примеры создания потоков через класс Thread
        new RunnableEx().new ThredEx().start();

    }

    private static void createThreadViaRunnable() {

        /*      как создать     */
        // 1 имплементировать интерфйес
        class RunnableExInner implements Runnable{
            @Override
            public void run() {
                System.out.println("hello");
            }
        }

        // 2 написать анонимный класс, или лямбду
        Runnable r1 = () -> System.out.println("hello");
        // Runnable r2 = () -> 5;   // exeption


        /*      как запустить     */
        // 1 создать поток через имплементацию
        Thread t1 = new Thread(new RunnableExInner());
        t1.start();
        System.out.println(t1.getName());

        // 2 создать поток через лямбду
        // NB! calling start() rather than run()
        new Thread(r1).start();
    }


    class ThredEx extends Thread {
        @Override
        public void run() {
            // получить имя текущего потока
            System.out.println(this.getName());
            System.out.println(Thread.currentThread().getName());
        }
    }


}
