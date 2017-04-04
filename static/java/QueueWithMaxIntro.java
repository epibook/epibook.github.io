import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

// @include
public class QueueWithMaxIntro {
  private Deque<Integer> data = new LinkedList<>();

  public void enqueue(Integer x) { data.add(x); }

  public Integer dequeue() { return data.removeFirst(); }

  public Integer max() {
    if (!data.isEmpty()) {
      return Collections.max(data);
    }
    throw new IllegalStateException("cannot perform max() on empty queue");
  }
  // @exclude

  private static void assertDequeue(QueueWithMaxIntro q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  private static void simpleTest() {
    QueueWithMaxIntro Q = new QueueWithMaxIntro();
    Q.enqueue(11);
    Q.enqueue(2);
    assert(11 == Q.max());
    assertDequeue(Q, 11);
    assert(2 == Q.max());
    assertDequeue(Q, 2);
    Q.enqueue(3);
    assert(3 == Q.max());
    assertDequeue(Q, 3);
    Q.enqueue(Integer.MAX_VALUE - 1);
    Q.enqueue(Integer.MAX_VALUE);
    Q.enqueue(-2);
    Q.enqueue(-1);
    Q.enqueue(-1);
    Q.enqueue(Integer.MIN_VALUE);
    assert(Integer.MAX_VALUE == Q.max());
    assertDequeue(Q, Integer.MAX_VALUE - 1);
    assert(Integer.MAX_VALUE == Q.max());
    assertDequeue(Q, Integer.MAX_VALUE);
    assert(-1 == Q.max());
    assertDequeue(Q, -2);
    assert(-1 == Q.max());
    assertDequeue(Q, -1);
    assertDequeue(Q, -1);
    assert(Integer.MIN_VALUE == Q.max());
    assertDequeue(Q, Integer.MIN_VALUE);
    try {
      System.out.println("Q is empty, max() call should except = " + Q.max());
      assert(false);
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    simpleTest();
    QueueWithMaxIntro Q = new QueueWithMaxIntro();
    Q.enqueue(1);
    Q.enqueue(2);
    assert(2 == Q.max());
    assertDequeue(Q, 1);
    assert(2 == Q.max());
    assertDequeue(Q, 2);
    Q.enqueue(3);
    assert(3 == Q.max());
    assertDequeue(Q, 3);
    try {
      Q.max();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    try {
      Q.dequeue();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
  // @include
}
