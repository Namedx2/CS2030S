package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic InfiniteList class that represents an infinite list of items.
 * CS2030S Lab 7
 * AY20/21 Semester 2
 *
 * @author Yuchen (Group 10B)
 * @param <T> Type of object to be used in InfiniteList.
 */
public class InfiniteList<T> {
  /** Head of the InfiniteList. */
  private final Lazy<Maybe<T>> head;

  /** Tail of the InfiniteList. */
  private final Lazy<InfiniteList<T>> tail;

  /** Final class field for caching an instance of sentinel. */
  private static final InfiniteList<?> SENTINEL = new Sentinel();

  /**
   * Nested static Sentinel class that represent an InfiniteList
   * that contains nothing and is used to mark the end of the list.
   */
  private static class Sentinel extends InfiniteList<Object> {
    /** Constructor for Sentinel. */
    private Sentinel() {
      super();
    }

    /**
     * Returns a string representation of a sentinel.
     *
     * @return String representation of a sentinel.
     */
    @Override
    public String toString() {
      return "-";
    }
    
    /**
     * Abstraction for checking if the list is an instance of Sentinel.
     *
     * @return Boolean.
     */
    @Override
    public boolean isSentinel() {
      return true;
    }

    /**
     * Calling head on an empty InfiniteList should simply throw an error.
     *
     * @return No such element exception.
     */
    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }

    /**
     * Calling tail on an empty InfiniteList should simply throw an error.
     *
     * @return No such element exception.
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new java.util.NoSuchElementException();
    }

    /**
     * Calling map on an empty InfiniteList should simply return sentinel.
     *
     * @return A sentinel.
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return sentinel();
    }

    /**
     * Calling filter on an empty InfiniteList should simply return sentinel.
     *
     * @return A sentinel.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return sentinel();
    }

    /**
     * Calling limit on an empty InfiniteList should simply return sentinel.
     *
     * @return A sentinel.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return sentinel();
    }

    /**
     * Calling takeWhile on an empty InfiniteList should simply return sentinel.
     *
     * @return A sentinel.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return sentinel();
    }

    /**
     * Calling toList on an empty InfiniteList
     * should simply return an empty list.
     *
     * @return An empty list.
     */
    @Override
    public List<Object> toList() {
      return List.of();
    }

    /**
     * Calling reduce on an empty InfiniteList should simply return the identity.
     *
     * @return Identity.
     */
    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    /**
     * Calling count on an empty InfiniteList should simply return 0.
     *
     * @return Count of 0.
     */
    @Override
    public long count() {
      return 0L;
    }
  }

  /** Private Constructor of an empty InfiniteList. */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * Generates an infinite list with a head containing 
   * the delayed evaluation of the producer and a tail 
   * containing the rest of the infinite list with the same head.
   *
   * @param <T>      Type of the value in Lazy.
   * @param producer The producer to be passed in.
   * @return         A single InfiniteList object with a head and tail.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())),
        Lazy.of(() -> generate(producer)));
  }

  /**
   * Produces an infinite list with a head of type T wrapped in a
   * Lazy object and a tail containing the rest of the infinite list
   * that has a function iterated on the subsequent heads.
   *
   * @param <T>  Type of the value in Lazy.
   * @param seed Starting item of the infinite InfiniteList.
   * @param next Delayed evaluation of the next item in list of InfiniteList
   * @return     An infinite list of InfiniteList objects.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(seed, () -> iterate(next.transform(seed), next));
  }

  /** 
   * Private Constructor of InfiniteList for inputs of unwrapped head item.
   * 
   * @param head Unwrapped head item.
   * @param tail Unwrapped tail item.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /** 
   * Private Constructor of InfiniteList for inputs of wrapped head item.
   *
   * @param head Wrapped head item.
   * @param tail Wrapped tail item.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Returns the head of an InfiniteList.
   *
   * @return The head of an InfiniteList.
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * Returns the tail of an InfiniteList.
   *
   * @return The tail of an InfiniteList.
   */
  public InfiniteList<T> tail() {
    return this.head.get()
        .map(ignr -> this.tail.get())
        .orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Applies the given transformation to each element in the list
   * and returns the resulting InfiniteList.
   *
   * @param <R>    Type of new InfiniteList.
   * @param mapper Transformation to be applied.
   * @return       New InfiniteList instance.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    Lazy<Maybe<R>> lazyHead = Lazy.of(() -> this.head.get().map(mapper));
    Lazy<InfiniteList<R>> lazyTail = Lazy.of(() -> this.tail.get().map(mapper));
    return new InfiniteList<>(lazyHead, lazyTail);
  }

  /**
   * Filter out elements in the list that fail a given BooleanCondition.
   *
   * @param predicate Condition to be tested on each element.
   * @return          New InfiniteList instance.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> lazyHead = Lazy.of(() -> this.head.get().filter(predicate));
    Lazy<InfiniteList<T>> lazyTail = Lazy.of(() -> this.tail.get().filter(predicate));
    return new InfiniteList<>(lazyHead, lazyTail);
  }

  /**
   * Returns a factory class field sentinel that marks the end 
   * of an InfiniteList.
   *
   * @param <T> Type of the sentinel.
   * @return    A sentinel instance.
   */
  public static <T> InfiniteList<T> sentinel() {
    /*
     * SENTINEL is only used to signify the end of an
     * InfiniteList and can only contain null, therefore 
     * it can safely be passed into wildcard during type casting.
     */

    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) SENTINEL;
    return temp;
  }

  /**
   * Truncates the InfiniteList to a finite list with at most n elements.
   *
   * @param n Maximum number of elements to truncate list to.
   * @return  New truncated InfiniteList instance.
   */
  public InfiniteList<T> limit(long n) {
    Lazy<InfiniteList<T>> lazyTail = Lazy.of(() -> this.head.get()
        .map(ignr -> this.tail.get().limit(n - 1))
        .orElseGet(() -> this.tail.get().limit(n)));

    return Maybe.of(n)
        .filter(x -> x > 0)
        .map(ignr -> new InfiniteList<>(this.head, lazyTail))
        .orElseGet(() -> sentinel());
  }

  /**
   * Truncates the InfiniteList as soon as it finds
   * an element that evaluates the condition to false.
   *
   * @param predicate Condition to be tested on each element.
   * @return          New truncated InfiniteList instance.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    /*
    Lazy<Maybe<T>> lazyHead = Lazy.of(() -> Maybe.some(this.head()).filter(predicate));

    Lazy<InfiniteList<T>> lazyTail = Lazy.of(() -> lazyHead.get()
        .map(ignr -> this.tail().takeWhile(predicate))
        .orElseGet(() -> sentinel()));

    return new InfiniteList<>(lazyHead, lazyTail);
    */
    
    Lazy<Maybe<T>> lazyHead = Lazy.of(() -> this.head.get().filter(predicate));
    Lazy<InfiniteList<T>> lazyTail = Lazy.of(() -> this.head.get()
        .map(x -> lazyHead.get()
            .map(y -> this.tail.get().takeWhile(predicate))
            .orElseGet(() -> sentinel()))
        .orElseGet(() -> this.tail.get().takeWhile(predicate)));
    return new InfiniteList<>(lazyHead, lazyTail);
  }

  /**
   * Abstraction for checking if the list is an instance of Sentinel.
   *
   * @return Boolean.
   */
  public boolean isSentinel() {
    return false;
  }

  /**
   * Performs a reduction on the elements of the InfiniteList, 
   * using the provided identity value and an associative
   * accumulation function, and returns the reduced value.
   *
   * @param <U>         Type of the identity and result of reduction.
   * @param identity    Identity of the accumulator function.
   * @param accumulator Function for combining two values.
   * @return            Result of the reduction.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return this.tail.get().reduce(this.head.get()
        .map(tail -> accumulator.combine(identity, tail))
        .orElse(identity), accumulator);
  }

  /**
   * Returns the number of elements in the InfiniteList.
   *
   * @return Number of elements in the InfiniteList.
   */
  public long count() {
    return this.reduce(0, (x, y) -> x + 1);
  }

  /**
   * Collects the elements in the InfiniteList into a Java List.
   *
   * @return Java List representation of the InfiniteList.
   */
  public List<T> toList() {
    List<T> lst = new ArrayList<>();
    this.head.get().consumeWith(head -> lst.add(head));
    lst.addAll(this.tail.get().toList());
    return lst;
  }

  /**
   * Returns a string representation of the InfiniteList.
   *
   * @return String representation of the InfiniteList.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
