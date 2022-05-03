package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * Generic Maybe class that is a wrapper around a value that might 
 * be missing; it represents either some value, or none.
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Yuchen (Group 10B)
 * @param <T> The type of the value to be wrapped.
 */
public abstract class Maybe<T> {
  // Factory None
  private static final Maybe<?> NONE = new None();

  // Concrete Maybe Methods
  public static <T> Maybe<T> none() {
    /*
     * NONE only consists of an instance
     * of None which contains nothing, 
     * hence can be passed into wildcard
     * during type casting.
     */

    @SuppressWarnings("unchecked")
    Maybe<T> temp = (Maybe<T>) NONE;
    return temp;
  }

  public static <T> Maybe<T> some(T val) {
    return new Some<T>(val);
  }

  public static <T> Maybe<T> of(T val) {
    if (val == null) {
      return none();
    } else {
      return new Some<T>(val);
    }
  }

  // Abstract Maybe Methods
  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> pred);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> mapper);

  public abstract <U> Maybe<U> flatMap(
      Transformer<? super T, ? extends Maybe<? extends U>> mapper); 

  public abstract T orElse(T obj);

  public abstract T orElseGet(Producer<? extends T> obj);

  // Nested static class None
  private static class None extends Maybe<Object> {
    // None Methods
    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof None;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<Object> pred) {
      return none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<Object, ? extends U> mapper) {
      return none();
    }

    @Override
    public <U> Maybe<U> flatMap(
        Transformer<Object, ? extends Maybe<? extends U>> mapper) {
      return none();
    }

    @Override
    public Object orElse(Object obj) {
      return obj;
    }

    @Override
    public Object orElseGet(Producer<?> obj) {
      return obj.produce();
    }
  }

  // Nested static class Some
  private static final class Some<T> extends Maybe<T> {
    // Some Variables
    private final T val;

    // Some Constructor
    private Some(T t) {
      this.val = t;
    }

    // Some Methods
    @Override
    public String toString() {
      return "[" + this.val + "]";
    }

    @Override
    protected T get() {
      return this.val;
    }

    // Null checking of Some
    public boolean isNull() {
      return this.val == null;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Some<?>) {
        Some<?> castObj = (Some<?>) obj; // Type casts obj to type Some
        if (this.isNull()) {
          return castObj.val == null;
        }
        return this.val.equals(castObj.val);
      }
      return false;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> pred) {
      if (!this.isNull() && !pred.test(this.val)) {
        return none();
      } else {
        return this;
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> mapper) {
      return some(mapper.transform(this.val));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> mapper) {
      /*
       * Since transform can only convert a value
       * into a subclass of Maybe<? extends U>,
       * it is fine to type cast it to Maybe of type U.
       */

      @SuppressWarnings("unchecked")
      Maybe<U> temp = (Maybe<U>) mapper.transform(this.val);
      return temp;
    }

    @Override
    public T orElse(T obj) {
      return this.val;
    }

    @Override
    public T orElseGet(Producer<? extends T> obj) {
      return this.val;
    }
  }
}
