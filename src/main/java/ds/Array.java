package ds;

import java.util.*;

public class Array {

    public static void main(String[] args) {
        String[] a = new String[]{"1","2","3", "1", "1", "2"};
        String[] b = new String[]{"1","2"};
        //matchingStrings(a, b);


        int[][] hourglass = new int[][]{
            {1, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0},
            {0, 0, 2, 4, 4, 0},
            {0, 0, 0, 2, 0, 0},
            {0, 0, 1, 2, 4, 0}
        };
        //System.out.println(hourglassSum(hourglass));


        ArrayList<Integer> alI = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        //rotateLeft(2, alI);


        ArrayList<Integer> al1 = new ArrayList<>(Arrays.asList(3, 2, 1, 1, 1));
        ArrayList<Integer> al2 = new ArrayList<>(Arrays.asList(4, 3, 2));
        ArrayList<Integer> al3 = new ArrayList<>(Arrays.asList(1, 1, 4, 1));
        System.out.println(equalStacks(al1, al2, al3));


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


    static int hourglassSum(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int sumArr =  arr[i][j] + arr[i][j+1] + arr[i][j+2] +
                            + arr[i+1][j+1] +
                            + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
                if (sumArr > sum) {
                    sum = sumArr;
                }
            }
        }
        return sum;
    }


    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        ArrayList<Integer> myList= new ArrayList<>(Arrays.asList(new Integer[arr.size()]));
        int pos = arr.size();
        for (int i = 0; i < pos; i++) {
            int ih = (i+d) % pos  ;
            myList.set(i, arr.get(ih));
        }
        System.out.println(myList);
        return myList;
    }

    public static int findMax(int ...i) {
        int max = 0;
        for (int value : i) if (value > max) max = value;
            return max;
    }


    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {

        return 0;
    }


}
