import algorithms.numerical.PrimeNumbers;
import ds.Array;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sieve {

    private static PrimeNumbers pn = new PrimeNumbers();

    Set s100 = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97));
    Set s101 = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97));

    @Test
    void v1a() {
        BitSet bitSet = pn.sieveOfEratosthenesV1(100);
        Set<Integer> collect = bitSet.stream().boxed().collect(toSet());
        assertEquals(s100, collect);
    }

    @Test
    void v1b() {
        BitSet bitSet2 = pn.sieveOfEratosthenesV1(101);
        Set<Integer> collect2 = bitSet2.stream().boxed().collect(toSet());
        assertEquals(s101, collect2);
    }

    @Test
    void v2a() {
        BitSet bitSet = pn.sieveOfEratosthenesV2(100);
        Set set = pn.numToSetV2(bitSet);
        assertEquals(s100, set);
    }

    @Test
    void v2b() {
        BitSet bitSet = pn.sieveOfEratosthenesV2(101);
        Set set = pn.numToSetV2(bitSet);
        assertEquals(s101, set);
    }

    @Test
    void v2c() {
        BitSet bitSet = pn.sieveOfEratosthenesV2(250);
        assertEquals(false, bitSet.get(177/2));
        assertEquals(true, bitSet.get(179/2));

    }
}
