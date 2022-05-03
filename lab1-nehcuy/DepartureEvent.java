public class DepartureEvent extends Event {
  // Variable declarations
  private Customer customer;

  // Constructor
  public DepartureEvent(Customer customer, double time) {
    super(time);
    this.customer = customer;
  }

  // Methods
  public Event[] simulate() { 
    // Do nothing
    return new Event[0];
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " departed";
  }
}
