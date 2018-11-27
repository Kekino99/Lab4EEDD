package HeapSort;
/*
 * Add more tests to make it clear that functions.
 * It works with 100 000.
 */
import FisherYates.FisherYates;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

public class HeapSortTest {

    @org.junit.Test
    public void sort() {
        ArrayList<Integer> toSort = FisherYates.sortedArray(100000);
        ArrayList<Integer> sorted = FisherYates.sortedArray(100000);
        FisherYates.fisherYates(toSort);
        HeapSort.sort(toSort);
        assertEquals(sorted, toSort);

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> stringSorted = new ArrayList<>();
        stringSorted.add("A");
        stringSorted.add("B");
        stringSorted.add("C");
        stringSorted.add("D");
        strings.add("D");
        strings.add("B");
        strings.add("A");
        strings.add("C");
        HeapSort.sort(strings);
        assertEquals(stringSorted, strings);
    }

    @org.junit.Test
    public void sort1() {
        ArrayList<Integer> toSort = FisherYates.sortedArray(100000);
        ArrayList<Integer> sorted = FisherYates.invertedSortedArray(100000);
        FisherYates.fisherYates(toSort);
        HeapSort.sort(toSort, Comparator.reverseOrder());
        assertEquals(sorted, toSort);
    }
}