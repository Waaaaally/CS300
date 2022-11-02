public interface ListADT <T> {
  // list method group 1 (no position inherent in these methods)
  public void add(T newObject);
  public boolean contains(T findObject);
  public int size();
  public boolean isEmpty();

  // list method group 2 (requires an index)
  public void add(int index, T newObject);
  public T get(int index);
  public int indexOf(T findObject);
  public T remove(int index);
}