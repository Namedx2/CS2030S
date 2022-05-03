/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab3
 */
public class ArrivalEvent extends Event {
  // Variable declarations
  private Customer customer;
  private Shop shop;

  // Constructor
  public ArrivalEvent(Customer customer, Shop shop, double time) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  // Methods
  @Override
  public Event[] simulate() {
    Counter counter = customer.proceedToCounter(this.shop);
    if (counter == null) {
      if (!shop.isQueueFull()) {
        return new Event[] {new JoinShopQueue(this.customer, this.shop, this.getTime())};
      } else {
        return new Event[] {new DepartureEvent(this.customer, this.getTime())};
      }
    }

    if (counter.getAvailability()) {
      return new Event[] {new ServiceBegin(this.customer, counter, this.getTime(), this.shop)};
    } else {
      return new Event[] {new JoinCounterQueue(this.customer, counter, this.getTime())};
    }
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " arrived " + shop.getQueue();
  }
}
