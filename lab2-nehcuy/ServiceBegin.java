/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class ServiceBegin extends Event {
  // Variable declarations
  private Customer customer;
  private Counters counter;
  private ShopNew shop;

  // Constructor
  public ServiceBegin(Customer customer, Counters counter, double time, ShopNew shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  // Methods
  @Override
  public Event[] simulate() {
    // Make counter unavailable
    this.counter.setUnavailable();
    // Return service-end event at current time + service time
    double totalTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {new ServiceEnd(this.customer, this.counter, totalTime, this.shop)};
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + 
      " service begin (by " + counter.toString() + ")";
  }
}
