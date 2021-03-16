package ds;

import java.util.Arrays;
import java.util.HashMap;

public class Array {

    public static void main(String[] args) {
        String[] a = new String[]{"1","2","3", "1", "1", "2"};
        String[] b = new String[]{"1","2"};
        matchingStrings(a, b);

    }

    static int[] matchingStrings(String[] strings, String[] queries) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : strings) {
            if (map.containsKey(s)) {
                Integer integer = map.get(s);
                map.put(s, ++integer);
            } else {
                map.put(s, 1);
            }
        }
        int i = 0;
        int[] arr = new int[queries.length];
        for (String s : queries) {
            arr[i] = map.getOrDefault(s, 0);
            i++;
        }
        return arr;
    }

}
