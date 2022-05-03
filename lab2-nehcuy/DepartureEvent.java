/**
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab2
 */
public class DepartureEvent extends Event {
  // Variable declarations
  private Customer customer;

  // Constructor
  public DepartureEvent(Customer customer, double time) {
    super(time);
    this.customer = customer;
  }

  // Methods
  @Override
  public Event[] simulate() { 
    // Do nothing
    return new Event[0];
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " departed";
  }
}
