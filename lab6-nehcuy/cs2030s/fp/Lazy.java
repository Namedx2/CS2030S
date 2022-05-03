package cs2030s.fp;

/**
 * Generic Lazy class that produces a value using lazy evaluation.
 * CS2030S Lab 6
 * AY20/21 Semester 2
 *
 * @author    Yuchen (Group 10B)
 * @param <T> The type of the variable to be processed within the Lazy class.
 */
public class Lazy<T> {
  /** Lazy class field for storing unevaluated Lazy. */
  private Producer<? extends T> producer;
  /** Lazy class field for storing wrapped value in Lazy. */
  private Maybe<T> value;

  /**
   * Private constructor of Lazy for inputs of type T.
   *
   * @param v The value of type T to be passed in.
   */
  private Lazy(T v) {
    this.value = Maybe.some(v);
  }

  /**
   * Private constructor of Lazy for inputs of type Producer.
   *
   * @param p The producer to be passed in.
   */
  private Lazy(Producer<? extends T> p) {
    this.producer = p;
    this.value = Maybe.none();
  }

  /**
   * Initializes the Lazy object with a given value.
   *
   * @param <T> Type of value.
   * @param v   The value to be initialised by Lazy.
   * @return    An instance of Lazy. 
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<>(v);
  }

  /**
   * Takes in a producer that produces the value when needed.
   *
   * @param <T> Type of value to be produced when evaluated.
   * @param s   The value to be produced when evaluated.
   * @return    An instance of Lazy.
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<>(s);
  }

  /**
   * Functional method that is called when the value is needed; 
   * if the value is already available, return that value; 
   * otherwise, compute the value and return it.
   *
   * @return The evaluation of value of type T passed into Lazy.
   */
  public T get() {
    T obj = this.value.orElseGet(this.producer);
    this.value = Maybe.some(obj);
    return obj;
  }

  /**
   * Returns "?" if the value is not yet available; returns the string 
   * representation of the value otherwise.
   *
   * @return Returns a string representation of the value of type T.
   */
  @Override
  public String toString() {
    Transformer<T, String> mapper = val -> String.valueOf(val);
    return this.value.map(mapper).orElse("?");
  }

  /**
   * Intialises new Lazy with a Producer to delay the evaluation of 
   * value until get() is called.
   *
   * @param <U>    Type of new Lazy object.
   * @param mapper Object to be mapped into a Lazy object.
   * @return       New Lazy instance.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> mapper) {
    Producer<U> t = () -> mapper.transform(this.get());
    return Lazy.of(t);
  }

  /**
   * Intialises new Lazy with a Producer that calls get() twice to
   * obtain the value in the inner Lazy and delays the evaluation of
   * value until get() is called.
   *
   * @param <U>    Type of new Lazy object.
   * @param mapper Object to be mapped into a Lazy object.
   * @return       New Lazy instance.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> mapper) {
    Producer<U> t = () -> mapper.transform(this.get()).get();
    return Lazy.of(t);
  }

  /**
   * Initialises new Lazy with a Producer to delay the test 
   * to see if the value passes the test or not.
   *
   * @param pred Predicate condition to be filtered.
   * @return     New Lazy instance of type Boolean.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> pred) {
    Producer<Boolean> t = () -> pred.test(this.get());
    return Lazy.of(t);
  }

  /**
   * Overrides the Object::equals and returns true only when both 
   * objects being compared are Lazy and the values within are
   * equals according to their equals() methods.
   *
   * @param obj Object to be compared with the value of this instance of Lazy.
   * @return    Boolean according to comparison.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Lazy<?>) {
      Lazy<?> castObj = (Lazy<?>) obj; // Type casting obj to type Lazy
      if (this.get() == null) {
        return castObj.get() == null;
      }
      return this.get().equals(castObj.get());
    }
    return false;
  }

  /**
   * Combines two Lazy objects and returns a single Lazy object.
   *
   * @param <S> Type of Lazy to be combined with this Lazy instance.
   * @param <R> Type of resultant Lazy instance.
   * @param obj Lazy object to be combined with this Lazy instance.
   * @param com Combiner to be used.
   * @return    New Lazy instance.
   */
  public <S, R> Lazy<R> combine(Lazy<S> obj, Combiner<? super T, ? super S, ? extends R> com) {
    return Lazy.of(() -> com.combine(this.get(), obj.get()));
  }
}
