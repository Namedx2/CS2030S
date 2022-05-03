/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class Counter implements Comparable<Counter> {
  // Variable declarations
  private boolean available;
  private int counterId;
  private static int numOfCounters = 0;
  private Queue<Customer> queue;

  // Constructor
  public Counter(int l) {
    this.available = true;
    this.counterId = numOfCounters++;
    this.queue = new Queue<Customer>(l);
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

  @Override
  public int compareTo(Counter nextCounter) {
    int currLen = this.queue.length();
    int nextCounterLen = nextCounter.queue.length();
    if (this.available == true && nextCounter.available == true) {
      return -1;
    } else if (this.available == true && nextCounter.available == false) {
      return -1;
    } else if (this.available == false && nextCounter.available == false) {
      if (currLen == nextCounterLen) {
        if (this.counterId > nextCounter.counterId) {
          return 1;
        } else {
          return -1;
        }
      } else if (currLen > nextCounterLen) {
        return 1;
      } else {
        return -1;
      }
    } else {
      return 1;
    }
  }

  @Override
  public String toString() {
    return "S" + this.counterId;
  }
}
