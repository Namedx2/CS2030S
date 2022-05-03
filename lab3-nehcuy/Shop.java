/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab3
 */
public class Shop {
  // Variables
  private Array<Counter> counters;
  private Queue<Customer> queue;

  // Constructor
  public Shop(int numOfCounters, int l, int m) {
    this.counters = new Array<Counter>(numOfCounters);
    this.queue = new Queue<Customer>(m);
    for (int i = 0; i < numOfCounters; ++i) {
      counters.set(i, new Counter(l));
    }
  }

  // Methods
  public Counter getAvailableCounter() {
    Counter counter = counters.min();
    if (counter.getAvailability()) {
      return counter;
    } else if (!counter.isQueueFull()) {
      return counter;
    } else {
      return null;
    }
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean joinQueue(Customer customer) {
    return this.queue.enq(customer);
  }

  public Customer leaveQueue() {
    return this.queue.deq();
  }

  public String getQueue() {
    return queue.toString();
  }
}
