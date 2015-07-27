package com.epi;

import java.util.LinkedList;
import java.util.NoSuchElementException;

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
      throw new NoSuchElementException("Called dequeue() on empty queue.");
    }

    public T max() {
      if (!candidatesForMax.isEmpty()) {
        return candidatesForMax.getFirst();
      }
      throw new NoSuchElementException("empty queue");
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

  public static void SimpleTest() {
    Queue<Integer> Q = new Queue<>();
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
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    SimpleTest();
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
      assert(false);
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    try {
      Q.dequeue();
      assert(false);
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }
}
