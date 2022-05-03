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
  public Event[] simulate() {
    Counters counter = customer.proceedToCounter(this.shop);
    if (counter != null) { // If there is a counter available, begin service
      return new Event[] {new ServiceBegin(this.customer, counter, this.getTime())};
    } else { // Else customer should depart
      return new Event[] {new DepartureEvent(this.customer, this.getTime())};
    }
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " arrives";
  }
}
