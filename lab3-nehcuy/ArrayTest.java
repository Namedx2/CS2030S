class ArrayTest {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    Array<Integer> a = new Array<Integer>(4);
    i.expect("initializing an empty array",
        a.toString(), "[ 0:null, 1:null, 2:null, 3:null ]");
    a.set(0, 3);
    i.expect("set element 0 to 3",
        a.toString(), "[ 0:3, 1:null, 2:null, 3:null ]");
    a.set(1, 4);
    i.expect("set element 1 to 4",
        a.toString(), "[ 0:3, 1:4, 2:null, 3:null ]");
    a.set(2, 1);
    i.expect("set element 2 to 1",
        a.toString(), "[ 0:3, 1:4, 2:1, 3:null ]");
    a.set(3, 6);
    i.expect("set element 3 to 6",
        a.toString(), "[ 0:3, 1:4, 2:1, 3:6 ]");
    i.expect("get element 0",
        a.get(0), 3);
    i.expect("get element 1",
        a.get(1), 4);
    i.expect("get element 2",
        a.get(2), 1);
    i.expect("get element 3",
        a.get(3), 6);

    i.expect("smallest element is 1",
        a.min(), 1);
    i.expect("a.min() does not change the array",
        a.toString(), "[ 0:3, 1:4, 2:1, 3:6 ]");
    a.set(2, 9);
    i.expect("update element 2 to 9",
        a.toString(), "[ 0:3, 1:4, 2:9, 3:6 ]");
    i.expect("smallest element is now 3",
        a.min(), 3);

    i.expectCompile("cannot put a non-integer into an array of integer",
        "new Array<Integer>(4).set(0, false)", false);

    i.expectCompile("cannot get a non-integer from an array of integer",
        "String s = new Array<Integer>(4).get(0)", false);

    i.expectCompile("cannot create an array of non-comparable element", 
        "class A {} Array<A> a;", false);

    i.expectCompile("cannot create an array of comparable element (but not to itself)", 
        "class A implements Comparable<Long> {" +
        "  public int compareTo(Long i) {" +
        "    return 0; " +
        "  }" +
        "}" +
        "Array<A> a;", false);

    i.expectCompile("can create an array of comparable element (to itself)", 
        "class A implements Comparable<A> {" +
        "  public int compareTo(A i) {" +
        "    return 0; " +
        "  }" +
        "}" +
        "Array<A> a;", true);
  }
}
