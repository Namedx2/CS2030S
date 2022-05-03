/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

public class LongerThan implements BooleanCondition<String> {
  // Variable fields
  private final int x;

  // Constructor
  public LongerThan(int num) {
    this.x = num;
  }

  // Interface methods

  @Override
  public boolean test(String string) {
    return string.length() > this.x;
  }
}
