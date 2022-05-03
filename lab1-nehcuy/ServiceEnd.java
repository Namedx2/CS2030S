public class ServiceEnd extends Event {
  // Variable declarations
  private Customer customer;
  private Counters counter;

  // Constructor 
  public ServiceEnd(Customer customer, Counters counter, double time) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  // Methods
  public Event[] simulate() {
    // Make counter available
    this.counter.setAvailable();
    // Return new departure event at current time
    return new Event[] {
      new DepartureEvent(this.customer, this.getTime())};
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " service done (by " + counter.toString() + ")";
  }
}
