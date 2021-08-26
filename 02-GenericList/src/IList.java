public interface IList<T> {
    void add(T entity);
    T delete(int number);
    int getCapacity();
    int size();
    T get(int number);
    boolean isEmpty();
    int indexOf(T entity);
    int lastIndexOf(T entity);
    T[] toArray();
    MyList<T> subList(int a, int b);
    boolean contains(T entity);
    void clear();


}
