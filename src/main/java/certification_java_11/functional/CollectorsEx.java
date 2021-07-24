package certification_java_11.functional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsEx {

    public static void main(String[] args) {

        counting();
        groupingBy();
        joining();
        mapping();
        maxBy();    // minBy
        partitioningBy();
        sumTypes(); // averaging, summing, summarizing
        toCollection(); // toSet, toList
        toMap();

    }


    private static void sumTypes() {
        Integer sum = Stream.of("1", "2", "3", "4")
                .collect(Collectors.summingInt(Integer::parseInt));
        System.out.println(sum);
// 10

        Double average = Stream.of("1", "2", "3", "4")
                .collect(Collectors.averagingInt(Integer::parseInt));
        System.out.println(average);
// 2.5

        DoubleSummaryStatistics stats = Stream.of("1.1", "2.34", "3.14", "4.04")
                .collect(Collectors.summarizingDouble(Double::parseDouble));
        System.out.println(stats);
// DoubleSummaryStatistics{count=4, sum=10.620000, min=1.100000, average=2.655000, max=4.040000}
    }


    private static void partitioningBy() {
        // Map<Boolean, List<T>> partitioningBy(Predicate<T> predicate)
        Map<Boolean, List<String>> map1 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(s -> s.length() <= 2));
        map1.entrySet().forEach(System.out::println);


        // Map<Boolean, D>> partitioningBy(Predicate<T> predicate, Collector<?> downstream)
        Map<Boolean, String> map2 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(
                        s -> s.length() <= 2,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.joining())
                ));
        map2.entrySet().forEach(System.out::println);
    }


    private static void mapping() {
        //  mapping(Function<T, U> mapper, Collector<?U, A, R> downstream)
        String s1 = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.mapping(
                                x -> Integer.toString(x),
                                Collectors.joining("-")
                        )
                );
        System.out.println(s1);
    }


    private static void joining() {
        // String joining()
        String s1 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining());


        // String> joining(CharSequence delimiter)
        String s2 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining("-"));
        System.out.println(s2);
    }


    private static void groupingBy() {
        Map<Integer, List<String>> map1 = Stream
                .of("ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(String::length));


        //  Collector<T, ?, Map<K, D>> groupingBy(Function<T, K> classifier, Collector<?> downstream)
        Map<Integer, String> collect = Stream
                .of("ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(String::length, Collectors.mapping(
                        String::toUpperCase,
                        Collectors.joining())));


        // Function<T, K> classifier, Supplier<M> mapFactory, Collector<?> downstream
        Map<Integer, List<String>> map3 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        LinkedHashMap::new,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.toList())
                ));
        map3.entrySet().forEach(System.out::println);
    }


    private static void maxBy() {
        getStream().collect(Collectors.maxBy(Comparator.naturalOrder()));
    }


    private static void toMap() {
        // Function<T, K> keyMapper, Function<T, U> valueMapper
        Map<Integer, Integer> collect3 = getStream().collect(Collectors.toMap(x -> x-1, x -> x*x));

        // Function<T, K> keyMapper, Function<T, U> valueMapper, BinaryOperator<U> mergeFunction
        // в случае, когда встречается два одинаковых ключа, позволяет объединить значения в mergeFunction
        Map<Integer, Integer> collect4 = Stream.of(1,2,2,3,4,5).collect(Collectors.toMap(x -> x-1, x -> x*x, (x, y) -> 100));

        // Function<T, K> keyMapper, Function<T, U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapFactory
        // позволяет указывать, какой именно класс Map использовать
        Map<Integer, String> collect5 = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b),
                        LinkedHashMap::new
                ));
        printViaForEach(collect5);
    }


    private static void toCollection() {
        List<Integer> collect = getStream().collect(Collectors.toList());
        Set<Integer> collect1 = getStream().collect(Collectors.toSet());
        ArrayDeque<Integer> collect2 = getStream().collect(Collectors.toCollection(ArrayDeque::new));
    }


    private static void counting() {
        Long collect = getStream().collect(Collectors.counting());
        print(collect);
    }


    private static Stream<Integer> getStream() {
        return Stream.of(1,2,3,4,5);
    }


    private static void print(Object o) {
        System.out.println(o);
    }


    private static void printViaForEach(Map<?, ?> collect3) {
        collect3.entrySet().forEach(System.out::println);
    }


}
