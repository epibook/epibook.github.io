package com.epi;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class StackQueueUsingHeap {
  // @include
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  private static class ValueWithRank {
    public Integer value;
    public Integer rank;

    public ValueWithRank(Integer value, Integer rank) {
      this.value = value;
      this.rank = rank;
    }
  }

  private static class Compare implements Comparator<ValueWithRank> {
    @Override
    public int compare(ValueWithRank o1, ValueWithRank o2) {
      return Integer.compare(o2.value, o1.value);
    }

    public static final Compare COMPARE_VALUEWITHRANK = new Compare();
  }

  public static class Stack {
    private int timestamp = 0;
    private PriorityQueue<ValueWithRank> maxHeap = new PriorityQueue<>(
        DEFAULT_INITIAL_CAPACITY, Compare.COMPARE_VALUEWITHRANK);

    public void push(Integer x) {
      maxHeap.add(new ValueWithRank(timestamp++, x));
    }

    public Integer pop() throws NoSuchElementException {
      return maxHeap.remove().rank;
    }

    public Integer peek() { return maxHeap.peek().rank; }
  }
  // @exclude

  public static class Queue {
    private int order = 0;
    private PriorityQueue<ValueWithRank> H
        = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, new Compare());

    public void enqueue(Integer x) { H.add(new ValueWithRank(order--, x)); }

    public Integer dequeue() throws NoSuchElementException {
      return H.remove().rank;
    }

    public Integer head() { return H.peek().rank; }
  }

  public static void main(String[] args) {
    Stack s = new Stack();
    s.push(1);
    s.push(2);
    s.push(3);
    assert(s.peek().equals(3));
    s.pop();
    assert(s.peek().equals(2));
    s.pop();
    s.push(4);
    assert(s.peek().equals(4));
    s.pop();
    s.pop();
    try {
      s.pop();
      assert(false);
    } catch (NoSuchElementException e) {
      System.out.println("empty stack");
      System.out.println(e.getMessage());
    }

    s.push(0);
    s.push(-1);
    s.push(Integer.MAX_VALUE);
    assert(s.peek().equals(Integer.MAX_VALUE));
    s.pop();
    assert(s.peek().equals(-1));
    s.pop();
    assert(s.peek().equals(0));
    s.pop();
    try {
      s.pop();
      assert(false);
    } catch (NoSuchElementException e) {
      System.out.println("empty stack");
      System.out.println(e.getMessage());
    }
    s.push(0);
    assert(s.peek().equals(0));

    Queue q = new Queue();
    q.enqueue(1);
    q.enqueue(2);
    assert(q.head().equals(1));
    q.dequeue();
    assert(q.head().equals(2));
    q.dequeue();
    try {
      q.dequeue();
      assert(false);
    } catch (NoSuchElementException e) {
      System.out.println("empty queue");
      System.out.println(e.getMessage());
    }
  }
}
