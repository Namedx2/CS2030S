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

  public int getCounterId() {
    return this.counterId;
  }

  public void setAvailable() {
    this.available = true;
  }

  public void setUnavailable() {
    this.available = false;
  }

  public String toString() {
    return "Counter " + this.counterId;
  }
}
