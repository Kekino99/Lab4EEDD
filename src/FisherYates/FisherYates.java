package FisherYates;

import java.util.ArrayList;
import java.util.Random;

/**
 * Debugging class. Imports methods to create arrays and "dissort" arrays to make tests easier.
 * Could be done in test package (?). Made it with ArrayList to make it easier
 */
public class FisherYates {

    /**
     * Creates a sorted ArrayList of Integers as the serie {x ∈ ℤ | 0<=x<index}
     * @param index the maximum of the ArrayList. It doesn't belong to the ArrayList.
     * @return returns a sorted ArrayList of Integers
     */
    public static ArrayList<Integer> sortedArray(int index) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            array.add(i);
        }
        return array;
    }

    /**
     * "Unsorts" an ArrayList
     * @param array the method will unsort the array
     */
    public static void fisherYates(ArrayList<Integer> array) {
        Random random = new Random();
        for (int k = array.size() - 1; k > 0; k--) {
            int al = Math.abs(random.nextInt() % k);
            Integer temp = array.get(k);
            array.set(k, array.get(al));
            array.set(al, temp);
        }
    }

    /**
     * Creates a inversed sorted ArrayList of Integers as the series {x ∈ ℤ | 0<=x<index}
     * @param index the maximum of the ArrayList. It doesn't belong to the ArrayList
     * @return returns an inversed sorted ArrayList of Integers
     */
    public static ArrayList<Integer> invertedSortedArray(int index) {
        ArrayList<Integer> array = new ArrayList<>();
        while (index > 0) {
            array.add(index-1);
            index--;
        }
        return array;
    }
}
