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

        /* Heap Size must be lesser than ArrayList.size when called.
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

        void validatePosition(int index) {
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

    public static <E> void sort(ArrayList<E> list, Comparator<? super E> cmp) {
        Heap<E> order = new Heap<>(list, cmp);

        for (int i = 0; i < list.size(); i++) {
            order.add();
        }

        for (int i = 0; i < list.size(); i++) {
            order.remove();
        }
    }

    public static <E extends Comparable<? super E>> void sort(ArrayList<E> list) {
        sort(list, Comparator.naturalOrder());
    }
}

