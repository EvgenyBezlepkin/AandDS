package certification_java_11.functional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStandard {

    public static void main(String[] args) {

        createStream();

        streamFinalReduce();
        streamFinalCollect();
        streamFinalMatch();

        streamInterFilter();
        streamInterFlatMap();


    }

    private static void streamInterFlatMap() {
        List<Integer> collect = Stream
                .of(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3))
                .flatMap(Collection::stream)
                .map(x -> x + 1)
                .collect(Collectors.toList());
        collect.forEach(x -> System.out.print(x));
    }

    private static void streamFinalMatch() {
        boolean b1 = streamOf().allMatch(x -> x > 0);
        System.out.println(b1);
        boolean b2 = streamOf().anyMatch(x -> x > 0);
        System.out.println(b2);
        boolean b3 = streamOf().noneMatch(x -> x > 5);
        System.out.println(b3);
    }

    private static void streamInterFilter() {
        long count = streamOf().filter(x -> x == 2).count();
        System.out.println(count);
    }

    private static void streamFinalCollect() {
        //R collect(Collector collector)
        Set<Integer> collect = streamOf().collect(Collectors.toSet());
        System.out.println(collect);
        //R collect(Supplier<> supplier, BiConsumer<> accumulator, BiConsumer<> combiner)
        ArrayList<Object> collect1 = streamOf().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        collect1.forEach(x -> System.out.println(x));
    }

    private static void streamFinalReduce() {
        /*
        Identity – an element that is the initial value of the reduction operation and the default result if the stream is empty
        Accumulator – a function that takes two parameters: a partial result of the reduction operation and the next element of the stream
        Combiner – a function used to combine the partial result of the reduction operation when the reduction is parallelized
        or when there's a mismatch between the types of the accumulator arguments and the types of the accumulator implementation
         */

        // Optional<Integer> reduce(BinaryOperator<Integer> accum)
        Optional<Integer> reduce = streamOf().reduce(Integer::sum);
        System.out.println(reduce);


        //Integer reduce(Integer identity, BinaryOperator<Integer> acum)
        Integer reduce1 = streamOf().reduce(10, (x, y) -> x + y);
        System.out.println(reduce1);


        // Object reduce(Object indentity, BiFunction<Object, ? super Integer, Object> accum, BinaryOperator<Object> combiner)
        // if we use sequential streams and the types of the accumulator arguments and the types of its implementation match, we don't need to use a combiner.
        // When a stream executes in parallel, the Java runtime splits the stream into multiple substreams.
        // In such cases, we need to use a function to combine the results of the substreams into a single one.
        Integer reduce2 = streamOf().parallel().reduce(1, (x, y) -> x + y, (x, y) -> x * y);
        System.out.println(reduce2);
    }

    private static Stream<Integer> streamOf() {
        return Stream.of(1, 2, 3);
    }


    private static void createStream() {
        /*
        Prepare data
        */
        Collection<Integer> col = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Integer[] iArr = {1, 2, 3, 4, 5};

        /*
        Create stream
         */

        // 1
        Stream<Object> empty = Stream.empty();
        Stream<Integer> listStream = Stream.of(1, 2, 3);
        Stream<Integer> generate = Stream.generate(() -> 1);
        Stream<Integer> iterate1 = Stream.iterate(2, x -> x + 6);                   // infinite
        //Stream.iterate(2, x -> x > 2, x -> x +1);
        //Stream<Object> objectStream = Stream.ofNullable(1);

        // 2
        Stream<Integer> stream1 = Arrays.stream(iArr);

        // 3
        Stream<Integer> stream = col.stream();
        Stream<Integer> integerStream = col.parallelStream();
    }
}
