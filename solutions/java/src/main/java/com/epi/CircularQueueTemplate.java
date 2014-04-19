package com.epi;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CircularQueueTemplate {
  // @include
  public static class Queue<T> {
    private int head = 0, tail = 0, count = 0;
    private Object[] data;

    public Queue(int cap) {
      data = new Object[cap];
    }

    public void enqueue(T x) {
      // Dynamically resize due to data_.size() limit.
      if (count == data.length) {
        // Rearrange elements.
        Collections.rotate(Arrays.asList(data), -head);
        head = 0;
        tail = count;
        data = Arrays.copyOf(data, count << 1);
      }
      // Perform enqueue
      data[tail] = x;
      tail = (tail + 1) % data.length;
      ++count;
    }

    public T dequeue() {
      if (count != 0) {
        --count;
        T ret = (T) data[head];
        head = (head + 1) % data.length;
        return ret;
      }
      throw new RuntimeException("empty queue");
    }

    public int size() {
      return count;
    }
  }

  // @exclude

  private static void test() {
    Queue<Integer> q = new Queue<Integer>(8);
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

    q.enqueue(14); // now the vector (data) is resized; but the head and tail.
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

  private static <T> void assertDequeue(Queue<T> q, T t) {
    T dequeue = q.dequeue();
    assert (t.equals(dequeue));
  }

  public static void main(String[] args) {
    test();
    Queue<Integer> q = new Queue<Integer>(8);
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
    assert (q.size() == 9);
  }
}
