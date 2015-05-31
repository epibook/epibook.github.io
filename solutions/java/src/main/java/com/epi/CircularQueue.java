package com.epi;

import java.util.Arrays;
import java.util.Collections;

public class CircularQueue {
  // @include
  public static class Queue {
    private int head = 0, tail = 0, numQueueElements = 0;
    private final int SCALE_FACTOR = 2;
    private Integer[] data;

    public Queue(int cap) { data = new Integer[cap]; }

    public void enqueue(Integer x) {
      if (numQueueElements == data.length) { // Needs to resize.
        // Makes the queue elements appear consecutively.
        Collections.rotate(Arrays.asList(data), -head);
        // Resets head and tail.
        head = 0;
        tail = numQueueElements;
        data = Arrays.copyOf(data, numQueueElements * SCALE_FACTOR);
      }

      data[tail] = x;
      tail = (tail + 1) % data.length;
      ++numQueueElements;
    }

    public Integer dequeue() {
      if (numQueueElements != 0) {
        --numQueueElements;
        Integer ret = data[head];
        head = (head + 1) % data.length;
        return ret;
      }
      throw new RuntimeException("empty queue");
    }

    public int size() { return numQueueElements; }
  }
  // @exclude

  private static void test() {
    Queue q = new Queue(8);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    q.enqueue(6);
    q.enqueue(7);
    q.enqueue(8);
    // Now head = 0 and tail = 0

    assertDequeue(q, 1);
    assertDequeue(q, 2);
    assertDequeue(q, 3);
    // Now head = 3 and tail = 0

    q.enqueue(11);
    q.enqueue(12);
    q.enqueue(13);
    // Ok till here. Now head = 3 and tail = 3

    q.enqueue(14);
    // Now the vector (data) is resized; but the head and tail.
    // (or elements) does not change accordingly.

    q.enqueue(15);
    q.enqueue(16);
    q.enqueue(17);
    q.enqueue(18);
    // The elements starting from head=3 are overwriten!

    assertDequeue(q, 4);
    assertDequeue(q, 5);
    assertDequeue(q, 6);
    assertDequeue(q, 7);
    assertDequeue(q, 8);
    assertDequeue(q, 11);
    assertDequeue(q, 12);
  }

  private static void assertDequeue(Queue q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  public static void main(String[] args) {
    test();
    Queue q = new Queue(8);
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    assertDequeue(q, 1);
    q.enqueue(4);
    assertDequeue(q, 2);
    assertDequeue(q, 3);
    assertDequeue(q, 4);
    try {
      q.dequeue();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    // test resize()
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    q.enqueue(4);
    assert(q.size() == 9);
  }
}
