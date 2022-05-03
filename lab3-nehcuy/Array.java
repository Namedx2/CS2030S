/**
 * The Array<T> for CS2030S 
 *
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab3
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {

    /**
     * The only way to put an object into the array 
     * is through the Shop constructor and we can
     * only put object of type T inside.
     * So it is safe to cast 'Comparable[]' to 'T[]'.
     */
    @SuppressWarnings("rawtypes")
    T[] temp = (T[]) new Comparable[size];
    this.array = temp; 
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T min = null;
    int len = this.array.length;
    T[] temp = this.array;
    for (int i = 0; i < len; ++i) {
      if (min == null) {
        min = temp[i];
      }
      if (min.compareTo(temp[i]) > -1) {
        min = temp[i];
      }
    }
    return min;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
