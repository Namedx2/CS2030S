/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class ServiceEnd extends Event {
  // Variable declarations
  private Customer customer;
  private Counters counter;
  private ShopNew shop;

  // Constructor 
  public ServiceEnd(Customer customer, Counters counter, double time, ShopNew shop) {
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
    Customer nextInQueue = shop.leaveQueue();
    if (nextInQueue == null) { // If no customer next in queue, return departure event
      return new Event[] {
        new DepartureEvent(this.customer, this.getTime())
      };
    } else { // Else depart customer followed by beginning service for customer next in queue
      return new Event[] {
        new DepartureEvent(this.customer, this.getTime()),
          new ServiceBegin(nextInQueue, this.counter, getTime(), this.shop)
      };
    }
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + 
      " service done (by " + counter.toString() + ")";
  }
}
