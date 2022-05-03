/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

public class BoxIt<T> implements Transformer<T, Box<T>> {
  
  @Override
  public Box<T> transform(T item) {
    return Box.of(item);
  }
}
