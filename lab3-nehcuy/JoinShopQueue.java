/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab3
 */
public class JoinShopQueue extends Event {
  // Variable declarations
  private Customer customer;
  private Shop shop;

  // Constructor
  public JoinShopQueue(Customer customer, Shop shop, double time) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  // Methods
  @Override
  public Event[] simulate() {
    shop.joinQueue(this.customer);
    // Return empty event after customer joins Shop Queue.
    return new Event[0];
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " joined shop queue " + shop.getQueue(); 
  }
}
