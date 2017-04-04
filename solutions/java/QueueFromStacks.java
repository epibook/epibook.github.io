import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueFromStacks {
  // @include
  public static class Queue {
    private Deque<Integer> enqueue = new LinkedList<>();
    private Deque<Integer> dequeue = new LinkedList<>();

    public void enqueue(Integer x) { enqueue.addFirst(x); }

    public Integer dequeue() {
      if (dequeue.isEmpty()) {
        // Transfers the elements from enqueue to dequeue.
        while (!enqueue.isEmpty()) {
          dequeue.addFirst(enqueue.removeFirst());
        }
      }

      if (!dequeue.isEmpty()) {
        return dequeue.removeFirst();
      }
      throw new NoSuchElementException("Cannot pop empty queue");
    }
  }
  // @exclude

  private static void assertDequeue(Queue q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  public static void main(String[] args) {
    Queue Q = new Queue();
    Q.enqueue(1);
    Q.enqueue(2);
    assertDequeue(Q, 1);
    assertDequeue(Q, 2);
    Q.enqueue(3);
    assertDequeue(Q, 3);
    try {
      Q.dequeue();
      assert(false);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    Q.enqueue(-1);
    Q.enqueue(123);
    Q.enqueue(Integer.MAX_VALUE);
    Q.enqueue(Integer.MIN_VALUE);
    Q.enqueue(0);
    assertDequeue(Q, -1);
    Q.enqueue(0);
    assertDequeue(Q, 123);
    assertDequeue(Q, Integer.MAX_VALUE);
    assertDequeue(Q, Integer.MIN_VALUE);
    assertDequeue(Q, 0);
    assertDequeue(Q, 0);
    try {
      Q.dequeue();
      assert(false);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
