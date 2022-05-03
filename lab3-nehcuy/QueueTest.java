class QueueTest {

  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    Queue<Integer> q = new Queue<Integer>(2);
    i.expect("insert 4 into a queue of integer",
        q.enq(4), true);
    i.expect("insert 8 into a queue of integer",
        q.enq(8), true);
    i.expect("insert 0 into a full queue",
        q.enq(0), false);
    i.expect("remove 4 from queue",
        q.deq(), 4);
    i.expect("remove 8 from queue",
        q.deq(), 8);
    i.expect("cannot deque anymore",
        q.deq(), null);
    i.expectCompile("cannot deque a non-integer from a queue of integer",
        "String s = new Queue<Integer>(3).deq();", false);
    i.expectCompile("cannot insert a non-integer into a queue of integer",
        "new Queue<Integer>(3).enqueue(false);", false);
  }
}
