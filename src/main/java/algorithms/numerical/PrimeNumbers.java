package algorithms.numerical;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class PrimeNumbers {

    private static int mill = 1_000_000_000;


    public static void main(String[] args) {
        PrimeNumbers pn = new PrimeNumbers();
        BitSet bitSet = pn.sieveOfEratosthenesV2(250);
        Set set = pn.numToSetV2(bitSet);
        System.out.println(set);

        System.out.println(bitSet.get(177/2));
    }


    public Set numToSetV2(BitSet bitSet2) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 1; i < bitSet2.size(); i++) {
            if (bitSet2.get(i)) {
                int p = i * 2+1;
                    hs.add(p);
            }
        }
        hs.add(2);
        return hs;
    }

    // классический алгоритм решето Эратосфера
    // время работы O(nlogn)
    // потребляемая память n
    public BitSet sieveOfEratosthenesV1(int count) {
        long start = System.currentTimeMillis();
        BitSet bs = new BitSet(count);
        bs.set(2, count+1, true);
        for (int p = 2; p * p <= count; p++) {
            if (bs.get(p)) {
                for (int i = p * p; i <= count; i += p) {
                    bs.set(i, false);
                }
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println((stop - start) / 1000);
        return bs;
    }

    // оптимизированное решето (убраны четные числа)
    // время работы O(nlogn) (в два раза меньше чисел)
    // потребляемая память n/2

    //  проверять числа на четность - четные исключать
    // остальные делить на два (деление будет сохранять единицу у несетных чисел)
    public BitSet sieveOfEratosthenesV2(int count) {
        int count2 = count / 2 +1;
        long start = System.currentTimeMillis();
        BitSet bs = new BitSet();
        bs.set(0, count2, true);
        int maxFactor = (int) Math.sqrt(count);
        for (int p = 3; p <= maxFactor; p += 2) {
            if (bs.get(p / 2)) {
                for (int i = p * p; i < count; i += 2 * p) {
                    bs.set(i / 2, false);
                }
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println((stop - start) / 1000);
        return bs;
    }

}
