import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        String s = "first:12,23,34,;second:43,32,21,;third:1,;";

        Map<String, Set<Integer>> stringListMap = parseDefaultMedia(s);
        stringListMap.forEach((k,v) -> System.out.println(k + " " + v));

        String fourth = addMedia(s, "fourth", 23);
//        System.out.println(fourth);
//        Map<String, Set<Integer>> stringSetMap = parseDefaultMedia(fourth);
//        stringSetMap.forEach((k,v) -> System.out.println(k + " " + v));

        System.out.println("---------------");
        String fourth2 = addMedia(fourth, "fourth", 34);
        Map<String, Set<Integer>> stringSetMap2 = parseDefaultMedia(fourth2);
        stringSetMap2.forEach((k,v) -> System.out.println(k + " " + v));
    }

    public static Map<String, Set<Integer>> parseDefaultMedia(String s) {

        Map<String, Set<Integer>> map = new HashMap<>();
        String[] split = s.split(";");
        for (String per : split) {
            String[] arr = per.split(":");
            String key = arr[0];
            String[] valuesStr = arr[1].split(",");
            Set<Integer> list = Arrays.stream(valuesStr).map(Integer::parseInt).collect(Collectors.toSet());
            map.put(key, list);
        }
        return map;
    }

    public static String addMedia(String s, String selector, Integer val) {

        Map<String, Set<Integer>> mediaMap = parseDefaultMedia(s);
        Set<Integer> set = new HashSet<>();

        if (mediaMap.containsKey(selector)) {
            Set<Integer> integers = mediaMap.get(selector);
            integers.add(val);
            mediaMap.put(selector, integers);
        } else {
            set.add(val);
            mediaMap.put(selector, set);
        }

        // translate to str
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Set<Integer>> item : mediaMap.entrySet()) {
            String key = item.getKey();
            sb.append(key).append(":");
            for (Integer i : item.getValue()) {
                sb.append(i).append(",");
            }
            sb.append(";");
        }
        return sb.toString();
    }


}
