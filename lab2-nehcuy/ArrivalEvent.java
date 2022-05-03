/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class ArrivalEvent extends Event {
  // Variable declarations
  private Customer customer;
  private ShopNew shop;

  // Constructor
  public ArrivalEvent(Customer customer, ShopNew shop, double time) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  // Methods
  @Override
  public Event[] simulate() {
    Counters counter = customer.proceedToCounter(this.shop);
    if (counter != null) { // If there is a counter available, begin service
      return new Event[] {new ServiceBegin(this.customer, counter, this.getTime(), this.shop)};
    } else { // Else customer should depart
      if (!shop.isQueueFull()) {
        return new Event[] {new JoinQueue(this.customer, this.shop, this.getTime())};
      } else {
        return new Event[] {new DepartureEvent(this.customer, this.getTime())};
      }
    }
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " arrived " + shop.getQueueStr();
  }
}
