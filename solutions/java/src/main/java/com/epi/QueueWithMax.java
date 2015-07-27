package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import java.util.NoSuchElementException;

public class QueueWithMax {
  // @include
  public static class Queue {
    private LinkedList<Integer> q1 = new LinkedList<>();
    private LinkedList<Integer> q2 = new LinkedList<>();

    public void enqueue(Integer x) { q1.push(x); }

    public Integer dequeue() {
      if (q2.isEmpty()) {
        while (!q1.isEmpty()) {
          q2.push(q1.pop());
        }
      }
      if (!q2.isEmpty()) {
        return q2.pop();
      }
      throw new NoSuchElementException("Cannot get max() on empty queue.");
    }

    public Integer max() {
      if (!q1.isEmpty()) {
        return q2.isEmpty() ? Collections.max(q1)
                            : Collections.max(Arrays.asList(Collections.max(q1),
                                                            Collections.max(q2)));
      } else { // q1.empty() == true.
        if (!q2.isEmpty()) {
          return Collections.max(q2);
        }
        throw new NoSuchElementException("Cannot get max() on empty queue.");
      }
    }
  }
  // @exclude

  private static void assertDequeue(Queue q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  private static void simpleTest() {
    Queue Q = new Queue();
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
    simpleTest();
    Queue Q = new Queue();
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
