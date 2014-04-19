package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class QueueWithMax {
  // @include
  public static class Queue<T extends Comparable<T>> {
    private LinkedList<T> a = new LinkedList<T>();
    private LinkedList<T> b = new LinkedList<T>();

    public void enqueue(T x) {
      a.push(x);
    }

    public T dequeue() {
      if (b.isEmpty()) {
        while (!a.isEmpty()) {
          b.push(a.pop());
        }
      }
      if (!b.isEmpty()) {
        return b.pop();
      }
      throw new RuntimeException("empty queue");
    }

    public T max() {
      if (!a.isEmpty()) {
        return b.isEmpty() ? Collections.max(a) : Collections.max(Arrays
            .asList(Collections.max(a), Collections.max(b)));
      } else { // A_.empty() == true.
        if (!b.isEmpty()) {
          return Collections.max(b);
        }
        throw new RuntimeException("empty queue");
      }
    }
  }

  // @exclude

  private static <T extends Comparable<T>> void assertDequeue(Queue<T> q, T t) {
    T dequeue = q.dequeue();
    assert (t.equals(dequeue));
  }

  public static void main(String[] args) {
    Queue<Integer> Q = new Queue<Integer>();
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
