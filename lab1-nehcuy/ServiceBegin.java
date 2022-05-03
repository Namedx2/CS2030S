public class ServiceBegin extends Event {
  // Variable declarations
  private Customer customer;
  private Counters counter;

  // Constructor
  public ServiceBegin(Customer customer, Counters counter, double time) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  // Methods
  public Event[] simulate() {
    // Make counter unavailable
    this.counter.setUnavailable();
    // Return service-end event at current time + service time
    double totalTime = this.getTime() + customer.getServiceTime();
    return new Event[] {new ServiceEnd(this.customer, this.counter, totalTime)};
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " service begin (by " + counter.toString() + ")";
  }
}
