import cs2030s.fp.BooleanCondition;
import cs2030s.fp.InfiniteList;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test 4 for CS2030S Lab 7 (AY21/22 Sem 2).  Tests
 * for InfiniteList sentinel, isSentinel, limit, toList.
 *
 * @author Ooi Wei Tsang
 */
class Test4 {
  /**
   * Main method for Test4.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).isSentinel(), false);
    i.expectReturn(
        "InfiniteList.generate(() -> 2).isSentinel()",
        () -> InfiniteList.generate(() -> 2).isSentinel(), false);
    i.expectReturn(
        "InfiniteList.generate(() -> 2).filter(x -> x % 3 == 0).isSentinel()",
        () -> InfiniteList.generate(() -> 2).filter(x -> x % 3 == 0).isSentinel(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isSentinel(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isSentinel(), false);

    i.expectReturn("InfiniteList.sentinel().isSentinel()",
        () -> InfiniteList.sentinel().isSentinel(), true);
    i.expectReturn("InfiniteList.sentinel().map(x -> 2).isSentinel()",
        () -> InfiniteList.sentinel().map(x -> 2).isSentinel(), true);
    i.expectReturn("InfiniteList.sentinel().filter(x -> true).isSentinel()",
        () -> InfiniteList.sentinel().filter(x -> true).isSentinel(), true);
    i.expectReturn("InfiniteList.sentinel().filter(x -> false).isSentinel()",
        () -> InfiniteList.sentinel().filter(x -> false).isSentinel(), true);

    i.expectReturn(
        "InfiniteList.sentinel().limit(4).isSentinel()",
        () -> InfiniteList.sentinel().limit(4).isSentinel(), true);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(0).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(0).isSentinel(), true);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(0).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(0).isSentinel(), true);

    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(1).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(1).isSentinel(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(10).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(10).isSentinel(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(-1).isSentinel()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(-1).isSentinel(), true);

    List<Integer> incrHistory = new ArrayList<>();
    Transformer<Integer, Integer> incr = x -> {
      incrHistory.add(x);
      return x + 1;
    };
    InfiniteList.iterate(1, incr).limit(0).isSentinel();
    i.expect("InfiniteList.iterate(1, x -> x + 1).limit(0).isSentinel() " + 
        "causes zero evaluation of x -> x + 1", 
        incrHistory, List.of());
    InfiniteList.iterate(1, incr).limit(1).isSentinel();
    i.expect("InfiniteList.iterate(1, x -> x + 1).limit(1).isSentinel() " + 
        "causes zero evaluation of x -> x + 1", 
        incrHistory, List.of());
    InfiniteList.iterate(1, incr).limit(10).isSentinel();
    i.expect("InfiniteList.iterate(1, x -> x + 1).limit(10).isSentinel() " + 
        "causes zero evaluation of x -> x + 1", 
        incrHistory, List.of());

    i.expectReturn("InfiniteList.generate(() -> 1).limit(4).toString()",
        () -> InfiniteList.generate(() -> 1).limit(4).toString(), 
        "[? ?]");
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(4).toString()",
        () -> InfiniteList.iterate(1, incr).limit(4).toString(), 
        "[[1] ?]");
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(0).head()",
        () -> InfiniteList.iterate(1, incr).limit(0).head(), 
        new java.util.NoSuchElementException());
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(1).head()",
        () -> InfiniteList.iterate(1, incr).limit(1).head(), 1);
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(4).head()",
        () -> InfiniteList.iterate(1, incr).limit(4).head(), 1);
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(1).tail().head()",
        () -> InfiniteList.iterate(1, incr).limit(1).tail().head(), 
        new java.util.NoSuchElementException());
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(4).tail().tail().head()",
        () -> InfiniteList.iterate(1, incr).limit(4).tail().tail().head(), 3);
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(4).limit(1).tail().head()",
        () -> InfiniteList.iterate(1, incr).limit(4).limit(1).tail().head(), 
        new java.util.NoSuchElementException());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(1).limit(4).tail().head()", 
        () -> InfiniteList.iterate(1, incr).limit(1).limit(4).tail().head(), 
        new java.util.NoSuchElementException());

    BooleanCondition<Integer> isEven = x -> (x % 2 == 0);

    i.expectException(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(0).head()",
        () -> InfiniteList.iterate(1, incr).filter(isEven).limit(0).head(), 
        new java.util.NoSuchElementException());
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(1).head()",
        () -> InfiniteList.iterate(1, incr).filter(isEven).limit(1).head(), 2);
    i.expectException(
        "InfiniteList.iterate(1, x -> x + 1).limit(1).filter(x -> x % 2 == 0).head()",
        () -> InfiniteList.iterate(1, incr).limit(1).filter(isEven).head(), 
        new java.util.NoSuchElementException());
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(2).filter(x -> x % 2 == 0).head()",
        () -> InfiniteList.iterate(1, incr).limit(2).filter(isEven).head(), 2);

    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).head(), 1);
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).tail().head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length())
        .tail().head(), 2);
    i.expectException(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).tail().tail()" +
        ".head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).tail().tail()
        .head(),
        new java.util.NoSuchElementException());

    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).head(), 1);
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).tail().head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).tail().head(), 2);
    i.expectException(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).tail().tail()" +
        ".head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).limit(2).tail().tail()
        .head(), 
        new java.util.NoSuchElementException());

    i.expectReturn(
        "InfiniteList.<String>sentinel().toList()",
        () -> InfiniteList.<String>sentinel().toList(), List.of());
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).toList()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).limit(2).toList(),
        List.of(1, 2));
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).toList()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).toList(),
        List.of(1, 2));
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(2).filter(x -> x % 2 == 0).toList()",
        () -> InfiniteList.iterate(1, incr).limit(2).filter(isEven).toList(), List.of(2));
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(2).toList()",
        () -> InfiniteList.iterate(1, incr).filter(isEven).limit(2).toList(), List.of(2, 4));

    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(10).limit(3).toList()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(10).limit(3).toList(), List.of(1, 2, 3));
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(3).limit(10).toList()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(3).limit(10).toList(), List.of(1, 2, 3));

    i.expectReturn(
        "InfiniteList.generate(() -> 4).limit(0).toList()",
        () -> InfiniteList.generate(() -> 4).limit(0).toList(), List.of());
    i.expectReturn(
        "InfiniteList.generate(() -> 4).limit(2).toList()",
        () -> InfiniteList.generate(() -> 4).limit(2).toList(), List.of(4, 4));

    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30)" + 
        ".filter(x -> x < 20).limit(5).toList()",
        () -> InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30)
        .filter(x -> x < 20).limit(5).toList(),
        List.of(11, 12, 13, 14, 15));

    java.util.Random rng = new java.util.Random(1);
    i.expectReturn(
        "InfiniteList.generate(() -> rng.nextInt() % 100).filter(x -> x > 10).limit(4).toList()",
        () -> InfiniteList.generate(() -> rng.nextInt() % 100).filter(x -> x > 10).limit(4)
        .toList(),
        List.of(76, 95, 26, 69));

    i.expectReturn("InfiniteList.generate(() -> null).limit(4).limit(1).toList()",
        () -> InfiniteList.<Object>generate(() -> null).limit(4).limit(1).toList(), 
        Arrays.asList(new Object[] { null }));
    i.expectReturn("InfiniteList.generate(() -> null).limit(1).limit(4).toList()",
        () -> InfiniteList.<Object>generate(() -> null).limit(1).limit(4).toList(), 
        Arrays.asList(new Object[] { null }));
  }
}
