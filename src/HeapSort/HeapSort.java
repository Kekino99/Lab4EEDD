package HeapSort;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapSort {

    /* An binary arborescence array of maxheap, which the root is always higher than its childs
     * With index i i*2+1 is the left child and i*2+2 is the right child
     * Its equilibrated.
     */
    private static class Heap<E> {
        private final ArrayList<E> elements;
        private final Comparator<? super E> comparator;
        private int heapSize = 0;

        private Heap(ArrayList<E> elements, Comparator<? super E> comparator) {
            this.elements = elements;
            this.comparator = comparator;
        }

        /* Index should always be greater than 0 and less than heapSize to make sense
         * If index doesn't makes sense, then an unwanted index could be returned
         */
        private static int parent(int index) {
            if (index % 2 == 0) {
                return (index - 2) / 2;
            } else {
                return (index - 1) / 2;
            }
        }

        private static int left(int index) {
            return index * 2 + 1;
        }

        private static int right(int index) {

            return index * 2 + 2;
        }

        private boolean hasLeft(int index) {
            return left(index) < heapSize;
        }

        private boolean hasRight(int index) {
            return right(index) < heapSize;
        }

        private boolean hasGreaterChild(int index) {
            return hasLeft(index) && comparator.compare(elements.get(left(index)), elements.get(index)) > 0 ||
                    hasRight(index) && comparator.compare(elements.get(right(index)), elements.get(index)) > 0;
        }

        private boolean hasParent(int index) {
            return index != 0;
        }

        /* Heap Size must be lesser than ArrayList.size() when called.
         *
         */
        private void add() {
            heapSize++;
            validatePosition(heapSize - 1);
        }

        private void remove() {
            swap(0, heapSize - 1);
            heapSize--;
            rootAtMax();
        }

        private void validatePosition(int index) {
            while (hasParent(index) && comparator.compare(elements.get(index), elements.get(parent(index))) > 0) {
                swap(index, parent(index));
                index = parent(index);
            }
        }

        private void rootAtMax() {
            int index = 0;
            while (hasGreaterChild(index)) {
                int maxOf;
                if (hasLeft(index) && hasRight(index)) {
                    maxOf = maxOf(left(index), right(index));
                } else if(hasLeft(index)){
                    maxOf = left(index);
                } else {
                    maxOf= right(index);
                }
                swap(index, maxOf);
                index= maxOf;
            }
        }

        private int maxOf(int index1, int index2) {
            if(comparator.compare(elements.get(index1), elements.get(index2))<0){
                return index2;
            } else {
                return index1;
            }
        }

        private void swap(int index1, int index2) {
            E temp = elements.get(index1);
            elements.set(index1, elements.get(index2));
            elements.set(index2, temp);
        }

    }

    /**
     * It sorts an array using HeapSort.
     * @param list the list that will be sorted. It sorts it in the same ArrayList that passed, overwritetting it
     * @param cmp passes the comparator that will be used to sort the ArrayList. It will sort as a0<=a1<=a2...<=an. If
     *            you want it in the reverse way, just use methods provided by the Comparator or make another
     *            Comparator.
     * @param <E> generic used to make anything sortable. As long as you can make a comparator that supers the elements
     *           of ArrayList it will work.
     */
    public static <E> void sort(ArrayList<E> list, Comparator<? super E> cmp) {
        Heap<E> order = new Heap<>(list, cmp);

        for (int i = 0; i < list.size(); i++) {
            order.add();
        }

        for (int i = 0; i < list.size(); i++) {
            order.remove();
        }
    }

    /**
     * It sorts an ArrayList using HeapSort
     * @param list the list that will be sorted. Its elements needs to extend Comparable<? super E>
     * @param <E> Generic used to make anything that extends some class Comparable sortable. Internally, it will call
     *           the sort method using Comparator.
     */
    public static <E extends Comparable<? super E>> void sort(ArrayList<E> list) {
        sort(list, Comparator.naturalOrder());
    }
}

