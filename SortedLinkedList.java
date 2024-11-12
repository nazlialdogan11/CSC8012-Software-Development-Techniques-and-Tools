import java.util.LinkedList;

public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {

    //Sorting and adding elements
    @Override
    public boolean add(E element) {
        if (isEmpty()) {
            return super.add(element);
        } else {
            int index = 0;
            while (index < size() && element.compareTo(get(index)) > 0) {
                index++;
            }
            super.add(index, element);
            return true;
        }
    }
}
