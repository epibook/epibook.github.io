package com.epi;

import java.util.LinkedList;

public class QueueWithMaxUsingDeque {
  // @include
  public static class Queue<T extends Comparable<T>> {
    private LinkedList<T> entries = new LinkedList<>();
    private LinkedList<T> candidatesForMax = new LinkedList<>();

    public void enqueue(T x) {
      entries.addFirst(x);
      while (!candidatesForMax.isEmpty()) {
        // Eliminate dominated elements in candidatesForMax.
        if (candidatesForMax.getLast().compareTo(x) >= 0) {
          break;
        }
        candidatesForMax.removeLast();
      }
      candidatesForMax.addLast(x);
    }

    public T dequeue() {
      if (!entries.isEmpty()) {
        T result = entries.removeLast();
        if (result.equals(candidatesForMax.getFirst())) {
          candidatesForMax.removeFirst();
        }
        return result;
      }
      throw new RuntimeException("empty queue");
    }

    public T max() {
      if (!candidatesForMax.isEmpty()) {
        return candidatesForMax.getFirst();
      }
      throw new RuntimeException("empty queue");
    }

    // @exclude

    public T head() { return entries.getLast(); }
    // @include
  }
  // @exclude

  private static <T extends Comparable<T>> void assertDequeue(Queue<T> q, T t) {
    T dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  public static void main(String[] args) {
    Queue<Integer> Q = new Queue<>();
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
}
