import java.util.Scanner; 
/**
 * This class implements a shop simulation.
 *
 * @author Yuchen
 * @version CS2030S AY21/22 Semester 2
 */ 

class ShopSimulation extends Simulation {

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  public Event[] initEvents;

  private ShopNew shop;
  
  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */

  // Re-implemented ShopSimulation  
  public ShopSimulation(Scanner sc) {
    this.initEvents = new Event[sc.nextInt()];
    this.shop = new ShopNew(sc.nextInt(), sc.nextInt());
    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(arrivalTime, serviceTime);
      initEvents[id] = new ArrivalEvent(customer, this.shop, arrivalTime);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
