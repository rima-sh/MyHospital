public interface DAO<T> {
    public T get (String id);
    public boolean add (T obj);
    public boolean remove(T obj);
}
