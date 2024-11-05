import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> extends CustomCollection<E> {

    int size();
    boolean contains(E element);
    boolean add(E e);
    void add(int index, E element);
    void clear();
    void set(int index, E element);
    E get(int index);
    void remove(int index);
    boolean remove(Object o);
    // QuickSort
    void sort(Comparator<? super E> c);
}
