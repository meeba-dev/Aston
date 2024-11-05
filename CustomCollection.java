public interface CustomCollection<E> extends Iterable<E> {
    void clear();
    int size();
    boolean isEmpty();
    boolean addAll(CustomCollection<? extends E> c);
}