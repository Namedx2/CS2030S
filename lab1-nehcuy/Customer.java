public class Customer {
  // Variables
  private int customerId;
  private double arrivalTime;
  private double serviceTime;
  private static int numOfCustomers = 0;

  // Constructor
  public Customer(double arrivalTime, double serviceTime) {
    this.customerId = numOfCustomers++;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  // Methods
  public double getServiceTime() {
    return this.serviceTime;
  }

  public Counters proceedToCounter(ShopNew shop) {
    return shop.getAvailableCounter();
  }

  @Override
  public String toString() {
    return "Customer " + this.customerId;
  }
}
