class Test2 {

  public static void main(String[] args) {

    CS2030STest we = new CS2030STest();

    we.expect("Circle: new at (0, 0) with radius 4",
        new Circle(new Point(0, 0), 4).toString(),
        "{ center: (0.0, 0.0), radius: 4.0 }");

    we.expect("Circle centered at (0, 0) with radius 4 contains (0, 0)",
        new Circle(new Point(0, 0), 4)
        .contains(new Point(0, 0)),
        true);

    we.expect("Circle centered at (0, 0) with radius 4 does not contain (4, 3)",
        new Circle(new Point(0, 0), 4)
        .contains(new Point(4, 3)),
        false);

    we.expect("Circle centered at (0, 0) with radius 4 does not contain (3, 4)",
        new Circle(new Point(0, 0), 4)
        .contains(new Point(3, 4)),
        false);

    we.expect("Circle centered at (2, -3) with radius 0.5 contains (1.8, -3.1)",
        new Circle(new Point(2, -3), 0.5)
        .contains(new Point(1.8, -3.1)),
        true);

    we.expect("Circle centered at (2, -3) with radius 0.5 does not contain (1.8, -4)",
        new Circle(new Point(2, -3), 0.5)
        .contains(new Point(1.8, -4)),
        false);
  }
}
