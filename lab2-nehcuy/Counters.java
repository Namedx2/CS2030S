/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class Counters {
  // Variable declarations
  private boolean available;
  private int counterId;
  private static int numOfCounters = 0;

  // Constructor
  public Counters() {
    this.available = true;
    this.counterId = numOfCounters++;
  }

  // Methods
  public boolean getAvailability() {
    return this.available;
  }

  public void setAvailable() {
    this.available = true;
  }

  public void setUnavailable() {
    this.available = false;
  }

  @Override
  public String toString() {
    return "S" + this.counterId;
  }
}
