/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab3
 */
public class ServiceEnd extends Event {
  // Variable declarations
  private Customer customer;
  private Counter counter;
  private Shop shop;

  // Constructor 
  public ServiceEnd(Customer customer, Counter counter, double time, Shop shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  // Methods
  @Override
  public Event[] simulate() {
    // Make counter available
    this.counter.setAvailable();
    Customer nextInShopQueue = shop.leaveQueue();
    Customer nextInCounterQueue = counter.leaveQueue();
    if (nextInShopQueue != null) {
      if (nextInCounterQueue != null) {
        return new Event[] {
          new DepartureEvent(this.customer, this.getTime()),
          new ServiceBegin(nextInCounterQueue, this.counter, this.getTime(), this.shop),
          new JoinCounterQueue(nextInShopQueue, this.counter, this.getTime())
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.customer, this.getTime()),
          new ServiceBegin(nextInShopQueue, this.counter, this.getTime(), this.shop),
        };
      }
    } else {
      if (nextInCounterQueue != null) {
        return new Event[] {
          new DepartureEvent(this.customer, this.getTime()),
          new ServiceBegin(nextInCounterQueue, this.counter, this.getTime(), this.shop),
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.customer, this.getTime())
        };
      }
    }
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + 
      " service done (by " + counter.toString() + " " + counter.getQueue() + ")";
  }
}
