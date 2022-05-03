/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class JoinQueue extends Event {
  // Variable declarations
  private Customer customer;
  private ShopNew shop;

  // Constructor
  public JoinQueue(Customer customer, ShopNew shop, double time) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  // Methods
  @Override
  public Event[] simulate() {
    shop.joinQueue(this.customer);
    // Return empty event after customer joins queue
    return new Event[0];
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " joined queue " + shop.getQueueStr(); 
  }
}
