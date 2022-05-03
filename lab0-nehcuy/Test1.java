class Test1 {

  public static void main(String[] args) {

    CS2030STest we = new CS2030STest();

    we.expect("Point: new at (0, 0)",
        new Point(0, 0).toString(),
        "(0.0, 0.0)");
    we.expect("Point: new at (-3.14, 1.59)",
        new Point(-3.14, 1.59).toString(),
        "(-3.14, 1.59)");
  }
}
