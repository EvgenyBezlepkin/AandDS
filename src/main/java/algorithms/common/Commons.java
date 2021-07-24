package algorithms.common;

import java.util.*;

public class Commons {

    public static void main(String[] args) {
        System.out.println(commonLongestPrefix(new String[]{"test", "testa", "te", "test23"}));
    }

    public static String commonLongestPrefix(String[] str) {

        // te
        // test
        // test23
        // testa
        Arrays.sort(str);

        Arrays.stream(str).forEach(System.out::print);

        return "";
    }

}
