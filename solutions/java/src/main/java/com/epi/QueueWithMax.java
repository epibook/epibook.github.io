package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class QueueWithMax {
  // @include
  public static class Queue {
    private LinkedList<Integer> a = new LinkedList<>();
    private LinkedList<Integer> b = new LinkedList<>();

    public void enqueue(Integer x) { a.push(x); }

    public Integer dequeue() {
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

    public Integer max() {
      if (!a.isEmpty()) {
        return b.isEmpty() ? Collections.max(a)
                           : Collections.max(Arrays.asList(Collections.max(a),
                                                           Collections.max(b)));
      } else { // A_.empty() == true.
        if (!b.isEmpty()) {
          return Collections.max(b);
        }
        throw new RuntimeException("empty queue");
      }
    }
  }
  // @exclude

  private static void assertDequeue(Queue q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  public static void main(String[] args) {
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
