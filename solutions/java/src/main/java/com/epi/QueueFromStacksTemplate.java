package com.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class QueueFromStacksTemplate {
  // @include
  public static class Queue<T> {
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
  }

  // @exclude

  private static <T> void assertDequeue(Queue<T> q, T t) {
    T dequeue = q.dequeue();
    assert (t.equals(dequeue));
  }

  public static void main(String[] args) {
    Queue<Integer> Q = new Queue<Integer>();
    Q.enqueue(1);
    Q.enqueue(2);
    assertDequeue(Q, 1);
    assertDequeue(Q, 2);
    Q.enqueue(3);
    assertDequeue(Q, 3);
    try {
      Q.dequeue();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
