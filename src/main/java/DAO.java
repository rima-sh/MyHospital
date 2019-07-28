public interface DAO<T> {
    public T Get (String id);
    public boolean Add (T obj);
    public boolean Remove(T obj);
}
