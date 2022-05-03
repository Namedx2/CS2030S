/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class ShopNew {
  // Variables
  private Counters[] counters;
  private Queue queue;

  // Constructor
  public ShopNew(int numOfCounters, int queueLength) {
    this.counters = new Counters[numOfCounters];
    this.queue = new Queue(queueLength);
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

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean joinQueue(Customer customer) {
    return this.queue.enq(customer);
  }

  public Customer leaveQueue() {
    return (Customer) this.queue.deq();
  }

  public String getQueueStr() {
    return queue.toString();
  }
}
