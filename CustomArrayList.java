import java.util.*;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private E[] elementData;

    public CustomArrayList() {
        elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) elementData = (E[]) new Object[initialCapacity];
        else if (initialCapacity == 0) elementData = (E[]) new Object[]{};
        else throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public boolean add(E element) {
        if (elementData.length == size) grow();
        elementData[size++] = element;
        return true;
    }


    @Override
    public void add(int index, E element) {
        checkIndex(index);
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(CustomCollection<? extends E> c) {
        if (c.isEmpty()) return false;
        if (c.size() == size) grow();
        for (E element : c) add(element);
        return true;
    }

    public void grow() {
        elementData = Arrays.copyOf(elementData, elementData.length << 1);
    }

    @Override
    public void clear() {
        for (E element : elementData) element = null;
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        elementData[index] = element;
    }

    @Override
    public E get(int index) {
        return elementData[index];
    }

    @Override
    public boolean isEmpty() {
        return elementData.length == 0;
    }

    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) return i;
        }
        throw new NoSuchElementException("CustomArrayList does not contain: " + element);
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    @Override
    public boolean remove(Object o) {
        E element = (E) o;
        if (!contains(element)) throw new NoSuchElementException("CustomArrayList does not contain: " + element);
        else {
            int index = indexOf(element);
            remove(index);
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>(elementData);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size));
    }

    public void checkIndex(int index) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(c, 0, size - 1);
    }

    // QuickSort implementation
    private void quickSort(Comparator<? super E> c, int low, int high) {
        if (low < high) {
            int pi = partition(c, low, high);
            quickSort(c, low, pi - 1);
            quickSort(c, pi + 1, high);
        }
    }

    private int partition(Comparator<? super E> c, int low, int high) {
        E pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (c.compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E buf = get(i);
        set(i, get(j));
        set(j, buf);
    }
}
