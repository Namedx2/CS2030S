import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2021/22
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX 
 */

class Lab0 {
  private static final Circle testCircle = new Circle(new Point(0.5, 0.5), 0.5);

  public static double estimatePi(int k, int seed) {
    double count = 0;
    RandomPoint.setSeed(seed);
    for (int n = 0; n < k; ++n) {
      Point p = new RandomPoint(0, 1, 0, 1);
        if (testCircle.contains(p)) {
          count++;
        }
    }
    double pi = 4 * count / k;
    return pi;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
