package com.epi;

import com.epi.utils.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StackQueueUsingHeap {
  // @include
  private static class Compare implements Comparator<Pair<Integer, Integer>> {
    @Override
    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
      return o2.getFirst().compareTo(o1.getFirst());
    }
  }

  public static class Stack {
    private int order = 0;
    private PriorityQueue<Pair<Integer, Integer>> maxHeap =
        new PriorityQueue<>(11, new Compare());

    public void push(Integer x) { maxHeap.add(new Pair<>(order++, x)); }

    public Integer pop() { return maxHeap.remove().getSecond(); }

    public Integer peek() { return maxHeap.peek().getSecond(); }
  }
  // @exclude

  public static class Queue {
    private int order = 0;
    private PriorityQueue<Pair<Integer, Integer>> H =
        new PriorityQueue<>(11, new Compare());

    public void enqueue(Integer x) { H.add(new Pair<>(order--, x)); }

    public Integer dequeue() { return H.remove().getSecond(); }

    public Integer head() { return H.peek().getSecond(); }
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
    } catch (Exception e) {
      System.out.println("empty stack");
      System.out.println(e.getMessage());
    }

    Queue q = new Queue();
    q.enqueue(1);
    q.enqueue(2);
    assert(q.head().equals(1));
    q.dequeue();
    assert(q.head().equals(2));
    q.dequeue();
    try {
      q.dequeue();
    } catch (Exception e) {
      System.out.println("empty queue");
      System.out.println(e.getMessage());
    }
  }
}
