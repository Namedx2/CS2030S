import cs2030s.fp.BooleanCondition;
import cs2030s.fp.Maybe;
import cs2030s.fp.Transformer;
import java.util.Map;

class Test2 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    BooleanCondition<Integer> isEven = new BooleanCondition<>() {
      public boolean test(Integer x) {
        return x % 2 == 0;
      }
    };
    i.expect("Maybe.<Integer>none().filter(isEven) should be Maybe.none()", 
        Maybe.<Integer>none().filter(isEven), Maybe.none()); 
    i.expect("Maybe.<Integer>some(null).filter(isEven) should be Maybe.some(null)",
        Maybe.<Integer>some(null).filter(isEven), Maybe.some(null));
    i.expect("Maybe.<Integer>some(1).filter(isEven) should be Maybe.none()", 
        Maybe.<Integer>some(1).filter(isEven), Maybe.none());
    i.expect("Maybe.<Integer>some(2).filter(isEven) should be Maybe.some(2)", 
        Maybe.<Integer>some(2).filter(isEven), Maybe.some(2));

    Transformer<Integer, Integer> incr = new Transformer<>() {
      public Integer transform(Integer x) {
        return x + 1;
      }
    };
    i.expect("Maybe.<Integer>none().map(incr) should be Maybe.none()",
        Maybe.<Integer>none().map(incr), Maybe.none());

    i.expectException("Maybe.<Integer>some(null).map(incr) should throw NullPointerException", 
        () -> Maybe.<Integer>some(null).map(incr), new NullPointerException());
    i.expect("Maybe.<Integer>some(1).map(incr) should be Maybe.some(2)", 
        Maybe.<Integer>some(1).map(incr), Maybe.some(2));

    Map<String, Integer> map = Map.of("one", 1, "two", 2);
    Transformer<String, Integer> wordToInt = new Transformer<>() {
      public Integer transform(String x) {
        return map.get(x);
      }
    };
    i.expect("Maybe.<String>none().map(wordToInt) should be Maybe.none()",
        Maybe.<String>none().map(wordToInt), Maybe.none());
    i.expect("Maybe.<String>some(\"\").map(wordToInt) should be Maybe.some(null)",
        Maybe.<String>some("").map(wordToInt), Maybe.some(null));
    i.expect("Maybe.<String>some(\"one\").map(wordToInt) should be Maybe.some(1)",
        Maybe.<String>some("one").map(wordToInt), Maybe.some(1));

    Transformer<String, Maybe<Integer>> wordToMaybeInt = new Transformer<>() {
      public Maybe<Integer> transform(String x) {
        return Maybe.of(map.get(x));
      }
    };

    i.expect("Maybe.<String>none().map(wordToMaybeInt) should be Maybe.non()", 
        Maybe.<String>none().map(wordToMaybeInt), Maybe.none());
    i.expect("Maybe.<String>some(\"\").map(wordToMaybeInt) should be Maybe.some(Maybe.none())",
        Maybe.<String>some("").map(wordToMaybeInt), Maybe.some(Maybe.none()));
    i.expect("Maybe.<String>some(\"one\").map(wordToMaybeInt) should be Maybe.some(Maybe.some(1))", 
        Maybe.<String>some("one").map(wordToMaybeInt), Maybe.some(Maybe.some(1)));
  }
}
