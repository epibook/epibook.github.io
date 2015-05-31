package com.epi;

import com.epi.utils.Pair;

import java.util.LinkedList;

public class StackWithMaxImproved {
  // @include
  public static class Stack {
    private LinkedList<Integer> element = new LinkedList<>();
    // Stores (maximum value, count) pair.
    private LinkedList<Pair<Integer, Integer>> cachedMaxWithCount =
        new LinkedList<>();

    public boolean empty() { return element.isEmpty(); }

    public Integer max() {
      if (!empty()) {
        return cachedMaxWithCount.peek().getFirst();
      }
      throw new RuntimeException("max(): empty_stack");
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("pop(): empty_stack");
      }
      Integer popElement = element.pop();
      if (popElement.equals(cachedMaxWithCount.peek().getFirst())) {
        cachedMaxWithCount.peek().setSecond(
            cachedMaxWithCount.peek().getSecond() - 1);
        if (cachedMaxWithCount.peek().getSecond().equals(0)) {
          cachedMaxWithCount.pop();
        }
      }
      return popElement;
    }

    public void push(Integer x) {
      element.push(x);
      if (!cachedMaxWithCount.isEmpty()) {
        if (x.compareTo(cachedMaxWithCount.peek().getFirst()) == 0) {
          cachedMaxWithCount.peek().setSecond(
              cachedMaxWithCount.peek().getSecond() + 1);
        } else if (x.compareTo(cachedMaxWithCount.peek().getFirst()) > 0) {
          cachedMaxWithCount.push(new Pair<>(x, 1));
        }
      } else {
        cachedMaxWithCount.push(new Pair<>(x, 1));
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    Stack s = new Stack();
    s.push(1);
    s.push(2);
    assert(s.max() == 2);
    System.out.println(s.max()); // 2
    System.out.println(s.pop()); // 2
    assert(s.max() == 1);
    System.out.println(s.max()); // 1
    s.push(3);
    s.push(2);
    assert(s.max() == 3);
    System.out.println(s.max()); // 3
    s.pop();
    assert(s.max() == 3);
    System.out.println(s.max()); // 3
    s.pop();
    assert(s.max() == 1);
    System.out.println(s.max()); // 1
    s.pop();
    try {
      s.max();
      s.pop();
      s.pop();
      s.pop();
      s.pop();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
