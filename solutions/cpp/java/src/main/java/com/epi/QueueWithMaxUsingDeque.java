package com.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class QueueWithMaxUsingDeque {
  // @include
  public static class Queue<T extends Comparable<T>> {
    private LinkedList<T> q = new LinkedList<>();
    private LinkedList<T> d = new LinkedList<>();

    public void enqueue(T x) {
      q.addFirst(x);
      while (!d.isEmpty() && d.getLast().compareTo(x) < 0) {
        d.removeLast();
      }
      d.addLast(x);
    }

    public T dequeue() {
      if (!q.isEmpty()) {
        T ret = q.removeLast();
        if (ret.equals(d.getFirst())) {
          d.removeFirst();
        }
        return ret;
      }
      throw new RuntimeException("empty queue");
    }

    public T max() {
      if (!d.isEmpty()) {
        return d.getFirst();
      }
      throw new RuntimeException("empty queue");
    }

    // @exclude

    public T head() {
      return q.getLast();
    }
    // @include
  }
  // @exclude

  private static <T extends Comparable<T>> void assertDequeue(Queue<T> q, T t) {
    T dequeue = q.dequeue();
    assert (t.equals(dequeue));
  }

  public static void main(String[] args) {
    Queue<Integer> Q = new Queue<>();
    Q.enqueue(1);
    Q.enqueue(2);
    assert (2 == Q.max());
    assertDequeue(Q, 1);
    assert (2 == Q.max());
    assertDequeue(Q, 2);
    Q.enqueue(3);
    assert (3 == Q.max());
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
}
