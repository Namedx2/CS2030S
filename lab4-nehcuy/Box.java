/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

public class Box<T> {
  // Variable declaration
  private final T item;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  // Constructor
  public Box(T obj) {
    this.item = obj;
  }

  // Methods

  @Override
  public boolean equals(Object obj) {
    if (this == obj) { // Do comparison if of same type
      return true;
    }
    if (obj instanceof Box<?>) {
      Box<?> castObj = (Box<?>) obj; // Type casts obj to type Box<?>
      if (this.item == null) {
        return !castObj.isPresent();
      }
      return this.item.equals(castObj.item);
    }
    return false;
  }

  @Override
  public String toString() {
    if (this.item == null) {
      return "[]";
    } else {
      return "[" + this.item.toString() + "]";
    }
  }

  public static <T> Box<T> of(T obj) {
    if (obj == null) {
      return null;
    } else {
      return new Box<T>(obj);
    }
  }

  public static <T> Box<T> empty() {
    /**
     * EMPTY_BOX only contains null which is of
     * type T, so it can be passed into the wildcard
     * during type casting.
     */

    @SuppressWarnings("unchecked")
    Box<T> temp = (Box<T>) EMPTY_BOX;
    return temp;
  }

  public boolean isPresent() {
    return this.item != null;
  }

  public static <T> Box<T> ofNullable(T obj) {
    if (obj == null) {
      return empty();
    } else {
      return of(obj);
    }
  }

  public Box<T> filter(BooleanCondition<? super T> pred) {
    if (!this.isPresent()) {
      return empty();
    } else if (pred.test(this.item)) {
      return this;
    } else {
      return empty();
    }
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> mapper) {
    if (this.item == null) {
      return empty();
    } 
    return new Box<U>(mapper.transform(this.item));
  }
}
