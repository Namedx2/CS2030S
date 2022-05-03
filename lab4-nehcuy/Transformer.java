/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

public interface Transformer<T, U> {
  // Abstract method
  public U transform(T obj);
}
