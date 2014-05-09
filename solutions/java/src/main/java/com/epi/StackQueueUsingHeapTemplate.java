package com.epi;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackQueueUsingHeapTemplate {
  // @include
  private static class Compare<T> implements Comparator<Pair<Integer, T>> {
    @Override
    public int compare(Pair<Integer, T> o1, Pair<Integer, T> o2) {
      return o2.getFirst().compareTo(o1.getFirst());
    }
  }

  public static class Stack<T> {
    private int order = 0;
    private PriorityQueue<Pair<Integer, T>> H = new PriorityQueue<>(
        11, new Compare<T>());

    public void push(T x) {
      H.add(new Pair<Integer, T>(order++, x));
    }

    public T pop() {
      return H.remove().getSecond();
    }

    public T peek() {
      return H.peek().getSecond();
    }
  }

  public static class Queue<T> {
    private int order = 0;
    private PriorityQueue<Pair<Integer, T>> H = new PriorityQueue<>(
        11, new Compare<T>());

    public void enqueue(T x) {
      H.add(new Pair<Integer, T>(order--, x));
    }

    public T dequeue() {
      return H.remove().getSecond();
    }

    public T head() {
      return H.peek().getSecond();
    }
  }

  // @exclude

  public static void main(String[] args) {
    Stack<Integer> s = new Stack<Integer>();
    s.push(1);
    s.push(2);
    s.push(3);
    assert (s.peek().equals(3));
    s.pop();
    assert (s.peek().equals(2));
    s.pop();
    s.push(4);
    assert (s.peek().equals(4));
    s.pop();
    s.pop();
    try {
      s.pop();
    } catch (Exception e) {
      System.out.println("empty stack");
      System.out.println(e.getMessage());
    }

    Queue<Integer> q = new Queue<Integer>();
    q.enqueue(1);
    q.enqueue(2);
    assert (q.head().equals(1));
    q.dequeue();
    assert (q.head().equals(2));
    q.dequeue();
    try {
      q.dequeue();
    } catch (Exception e) {
      System.out.println("empty queue");
      System.out.println(e.getMessage());
    }

  }
}
