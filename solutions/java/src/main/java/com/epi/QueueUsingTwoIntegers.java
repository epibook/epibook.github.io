package com.epi;

public class QueueUsingTwoIntegers {
  // @include
  public static class Queue {
    private int val = 0;
    private int size = 0;
    private int maxSize = (int)Math.floor(Math.log10(Integer.MAX_VALUE));

    public void enqueue(int x) {
      if (size >= maxSize) {
        throw new IllegalStateException("queue overflow");
      }
      val = val * 10 + x;
      ++size;
    }

    public int dequeue() {
      if (size == 0) {
        throw new IllegalStateException("empty queue");
      }
      int ret = 0, d = (int)Math.floor(Math.log10(val));
      if (d + 1 == size) {
        int powVal = (int)Math.pow(10, d);
        ret = val / powVal;
        val -= powVal * ret;
      }
      --size;
      return ret;
    }
  }
  // @exclude

  private static void assertDequeue(Queue q, int t) {
    int dequeue = q.dequeue();
    assert(t == dequeue);
  }

  public static void main(String[] args) {
    Queue q = new Queue();
    q.enqueue(0);
    q.enqueue(5);
    q.enqueue(0);
    q.enqueue(2);
    assertDequeue(q, 0);
    assertDequeue(q, 5);
    q.enqueue(3);
    assertDequeue(q, 0);
    assertDequeue(q, 2);
    assertDequeue(q, 3);
    q.enqueue(0);
    q.enqueue(0);
    assertDequeue(q, 0);
    assertDequeue(q, 0);
    // Empty queue, it should throw
    try {
      q.dequeue();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    q.enqueue(0);
    q.enqueue(0);
    q.enqueue(0);
    q.enqueue(0);
    q.enqueue(5);
    q.enqueue(0);
    q.enqueue(2);
    q.enqueue(5);
    q.enqueue(0);
    // Queue overflow, it should throw.
    try {
      q.enqueue(2);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
