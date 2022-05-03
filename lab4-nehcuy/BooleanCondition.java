/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

public interface BooleanCondition<T> {
  // Abstract method
  public boolean test(T obj);
}
