public class ShopNew {
  // Variables
  private Counters[] counters;

  // Constructor
  public ShopNew(int numOfCounters) {
    counters = new Counters[numOfCounters];
    for (int i = 0; i < numOfCounters; ++i) {
      counters[i] = new Counters();
    }
  }

  // Methods
  public Counters getAvailableCounter() {
    Counters counter = null;
    for (int i = 0; i < counters.length; ++i) {
      if (counters[i].getAvailability()) {
        counter = counters[i];
        return counter;
      }
    }
    return counter;
  }
}
