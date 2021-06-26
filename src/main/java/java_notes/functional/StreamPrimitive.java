package java_notes.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamPrimitive {

    public static void main(String[] args) {

        additionalCreateMethods();

        // common operations
        streamInterBoxed();
        streamSummaryStatistics();
        typeToTypeMethods();

    }

    private static void streamSummaryStatistics() {
        System.out.println(getIntStream().summaryStatistics());
    }

    private static void typeToTypeMethods() {
        // asDoubleStream
        ArrayList<Double> collect = getIntStream().asDoubleStream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        print(collect);

        // mapToDouble
        ArrayList<Double> collect1 = getIntStream().mapToDouble(x -> x + 1).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        print(collect1);

        // mapToObj
        ArrayList<String> collect2 = getIntStream().mapToObj(String::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        print(collect2);
    }

    private static void streamInterBoxed() {
        List<Integer> collect = getIntStream().boxed().collect(Collectors.toList());
        print(collect);
    }

    private static void additionalCreateMethods() {
        IntStream.range(1, 10).count();         // 1-9
        IntStream.rangeClosed(1, 10).count();   // 1-10
    }

    private static void print(List<?> collect) {
        collect.forEach(System.out::print);
        System.out.println();
    }

    private static IntStream getIntStream() {
        return IntStream.of(1,2,3);
    }
}
