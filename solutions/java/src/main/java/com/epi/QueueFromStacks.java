package com.epi;

import java.util.LinkedList;

public class QueueFromStacks {
  // @include
  public static class Queue {
    private LinkedList<Integer> a = new LinkedList<>();
    private LinkedList<Integer> b = new LinkedList<>();

    public void enqueue(Integer x) { a.push(x); }

    public Integer dequeue() {
      if (b.isEmpty()) {
        // Transfers the elements from a to b.
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

  private static void assertDequeue(Queue q, Integer t) {
    Integer dequeue = q.dequeue();
    assert(t.equals(dequeue));
  }

  public static void main(String[] args) {
    Queue Q = new Queue();
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
