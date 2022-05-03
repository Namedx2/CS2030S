class Test3 {

  public static void main(String[] args) {

    CS2030STest we = new CS2030STest();

    we.expectCompile("RandomPoint: is a subtype of Point", 
        "Point p = new RandomPoint(0,1,0,1);", true);

    we.expect("RandomPoint: generate a new point with default seed",
        new RandomPoint(0, 1, 0, 1).toString(),
        "(0.7308781907032909, 0.41008081149220166)");

    RandomPoint.setSeed(10);
    we.expect("RandomPoint: generate a new point with seed 10",
        new RandomPoint(0, 1, 0, 1).toString(),
        "(0.7304302967434272, 0.2578027905957804)");

    we.expect("RandomPoint: generate a new point with the same seed",
        new RandomPoint(0, 1, 0, 1).toString(),
        "(0.059201965811244595, 0.24411725056425315)");

    RandomPoint.setSeed(10);
    we.expect("RandomPoint: reset seed to 10 and generate a new point",
        new RandomPoint(0, 1, 0, 1).toString(),
        "(0.7304302967434272, 0.2578027905957804)");
  }
}

