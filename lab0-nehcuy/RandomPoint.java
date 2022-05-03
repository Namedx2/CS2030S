import java.util.Random;

public class RandomPoint extends Point {
  static Random rng = new Random(1);

  public RandomPoint(double minX, double maxX, double minY, double maxY) {
    super((maxX - minX) * rng.nextDouble() + minX, (maxY - minY) * rng.nextDouble() + minY);
  }

  public static void setSeed(int s) {
    rng = new Random(s);
  }       
}