package com.epi;

/*
@slug
circular-queue

@summary
A queue can be implemented using an array
and two additional fields, the beginning and the end indices. This structure
is sometimes referred to as a circular queue.
Both enqueue and dequeue have $\mathcal{O}(1)$ time complexity.
If the array is fixed, there is a maximum number of entries that can be stored.
If the array is dynamically resized,
the total time for $m$ combined enqueue and dequeue operations is
$\mathcal{O}(m)$.

@problem
Implement a queue API using an array for storing elements.
Your API should include a constructor function, which takes as argument the
initial capacity
of the queue, enqueue and dequeue functions, and a function which returns
the number of elements stored. Implement dynamic resizing to support storing an
arbitrarily large
number of elements.

*/

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class CircularQueue {
  // @include
  public static class Queue {
    private int head = 0, tail = 0, numQueueElements = 0;
    private static final int SCALE_FACTOR = 2;
    private Integer[] entries;

    public Queue(int capacity) { entries = new Integer[capacity]; }

    public void enqueue(Integer x) {
      if (numQueueElements == entries.length) { // Need to resize.
        // Makes the queue elements appear consecutively.
        Collections.rotate(Arrays.asList(entries), -head);
        // Resets head and tail.
        head = 0;
        tail = numQueueElements;
        entries = Arrays.copyOf(entries, numQueueElements * SCALE_FACTOR);
      }

      entries[tail] = x;
      tail = (tail + 1) % entries.length;
      ++numQueueElements;
    }

    public Integer dequeue() {
      if (numQueueElements != 0) {
        --numQueueElements;
        Integer ret = entries[head];
        head = (head + 1) % entries.length;
        return ret;
      }
      throw new NoSuchElementException("Dequeue called on an empty queue.");
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
    // Now the vector (entries) is resized; but the head and tail.
    // (or elements) does not change accordingly.

    q.enqueue(15);
    q.enqueue(16);
    q.enqueue(17);
    q.enqueue(18);
    // The elements starting from head=3 are over-written!

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
    } catch (NoSuchElementException e) {
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
