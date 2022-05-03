package cs2030s.fp;

/**
 * Represent a function that consumes a value.
 * CS2030S Lab 7
 * AY21/22 Semester 2
 *
 * @param <T> The type of the value produced.
 */
@FunctionalInterface
public interface Consumer<T> {
  /**
   * The functional method to consume a value.
   *
   * @param t the value to consume
   */
  void consume(T t);
}
