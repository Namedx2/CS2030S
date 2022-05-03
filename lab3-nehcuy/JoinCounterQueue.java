/** 
 * @author Yuchen (Group 10B)
 * @version CS2030S AY21/22 Sem2 Lab3
 */
public class JoinCounterQueue extends Event {
  // Variable declarations
  private Customer customer;
  private Counter counter;

  // Constructor
  public JoinCounterQueue(Customer customer, Counter counter, double time) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }
  // Methods
  
  @Override
  public Event[] simulate() {
    counter.joinQueue(this.customer);
    // Return empty event after customer joins Counter Queue. 
    return new Event[0];
  }

  @Override 
  public String toString() {
    return super.toString() + ": " + customer.toString() + " joined counter queue (at " +
      counter.toString() + " " + counter.getQueue() + ")";
  }
}
