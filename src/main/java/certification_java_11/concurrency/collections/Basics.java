package certification_java_11.concurrency.collections;

import java.util.Map;
import java.util.concurrent.*;

public class Basics{

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        concurrentHashMap();
        concurrentLinkedQueue();
        skipList();
        copyOnWrite();

    }

    private static void copyOnWrite() {
    /*
    CopyOnWriteArrayList создает клонированную копию нижележащей коллекции для каждой операции обновления,
    в определенный момент обе они будут синхронизироваться автоматически
    CopyOnWriteArraySet реализован при помощи CopyOnWriteArrayList
    Подходит для приложений, в которых:
        Размеры коллекций небольшие.
        Операции read-only значительно превосходят операции, изменяющие объекты.
        Потокобезопасность.
        Мутативные операции требуют копирования всего базового массива.
        Итераторы опираются на моментальный снимок массива, который был сделан во время создания итератора.
        любые изменения, внесенные позже в список, не будут видны уже созданному итератору.
        Итератор не поддерживает remove ()
     */

        CopyOnWriteArrayList<String> cowal = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> cowas = new CopyOnWriteArraySet<>();

        cowal.add("1");
        cowal.add("2");
        cowal.add("3");

        for (String s : cowal) {
            cowal.add("4");
        }
        System.out.println(cowal.size());
    }

    private static void skipList() {
    /*
    Список с пропусками (Skip List) — вероятностная структура данных, основанная на нескольких параллельных отсортированных связных списках с эффективностью,
    сравнимой с двоичным деревом.
    В основе списка с пропусками лежит расширение отсортированного связного списка дополнительными связями,
    добавленными в случайных путях таким образом, чтобы поиск по списку мог быстро пропускать части этого списка.
    Вставка, поиск и удаление выполняются за логарифмическое случайное время.
    Обладает методами карты и дерева.
    Элементы отсортированы.
     */

        ConcurrentSkipListMap<Integer, String> cslm = new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<String> csls = new ConcurrentSkipListSet<>();
    }


    private static void concurrentLinkedQueue() {
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();

        clq.add("q");

        clq.forEach(x -> {
            clq.add("w");
            clq.poll();
        });
    }

    private static void concurrentHashMap() {

     /*
        1 Hashtable.
            При доступе к элементам таблицы производилась её полная блокировка. Все методы Hashtable были синхронизированными.
        2 Collections.synchronizedMap
            Only one thread is allowed to make change in case of SynchronizedHashMap.
            A lock is required for read operation
            Returns Iterator, which fails-fast on concurrent modification.
        3 ConcurrentHashMap
            По умолчанию карта разделена на 16 сегментов по которым происходит блокировка, поэтому 16 потоков могут одновременно изменять карту.
            Любое количество потоков могут делать операцию чтения.
            При создании указывается требуемый concurrencyLevel(влияет на использование картой памяти и количество сегментов в карте)
            Значения (value) карты объявлены как volatile
            ConcurrentHashMap doesn’t throw a ConcurrentModificationException if one thread tries to modify it while another is iterating over it (Fail-safe iterator)
            Не принимает нулл ни в качестве ключа, ни в качестве значения.
     */

        ConcurrentHashMap<Integer, String> chm = new ConcurrentHashMap<>();
        chm.put(1, "one");

        for (Map.Entry<Integer, String> e : chm.entrySet()) {
            if (e.getKey().equals(1)) {
                chm.put(2, "two");
            }
        }
        chm.forEach((k, v) -> System.out.println(k + " ->" + v));
    }
}
