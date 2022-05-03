/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

import java.lang.Math;

public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  // Variable fields
  private final int integer;

  // Constructor 
  public LastDigitsOfHashCode(int k) {
    this.integer = k;
  }

  // All interface methods

  @Override
  public Integer transform(Object x) {
    if (this.integer == 0) {
      return null;
    } else { // Takes modulo of 10^k to get last k digits.
      int mod = (int) Math.pow(10, this.integer);
      return Math.abs(x.hashCode()) % mod;
    }
  }
}
