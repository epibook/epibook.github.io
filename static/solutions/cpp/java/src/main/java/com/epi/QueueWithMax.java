package com.epi;

import com.epi.StackWithMax.Stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

// @include
public class QueueWithMax {
  private StackWithMax.Stack enqueue = new StackWithMax.Stack();
  private StackWithMax.Stack dequeue = new StackWithMax.Stack();

  public void enqueue(Integer x) { enqueue.push(x); }

  public Integer dequeue() {
    if (dequeue.empty()) {
      while (!enqueue.empty()) {
        dequeue.push(enqueue.pop());
      }
    }
    if (!dequeue.empty()) {
      return dequeue.pop();
    }
    throw new NoSuchElementException("Cannot get dequeue() on empty queue.");
  }

  public Integer max() {
    if (!enqueue.empty()) {
      return dequeue.empty() ? enqueue.max()
                             : Math.max(enqueue.max(), dequeue.max());
    }
    if (!dequeue.empty()) {
      return dequeue.max();
    }
    throw new NoSuchElementException("Cannot get max() on empty queue.");
  }
  // @exclude

  private static void assertDequeue(QueueWithMax q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  private static void simpleTest() {
    QueueWithMax Q = new QueueWithMax();
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
    QueueWithMax Q = new QueueWithMax();
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
