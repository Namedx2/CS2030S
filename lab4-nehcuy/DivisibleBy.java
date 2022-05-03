/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Yuchen (Group 10B)
 */

public class DivisibleBy implements BooleanCondition<Integer> {
  // Variable fields
  private final int x;

  // Constructor
  public DivisibleBy(int num) {
    this.x = num;
  }

  // Interface methods

  @Override
  public boolean test(Integer y) {
    return y % this.x == 0;
  }
}
