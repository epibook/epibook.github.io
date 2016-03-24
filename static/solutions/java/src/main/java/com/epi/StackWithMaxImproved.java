package com.epi;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithMaxImproved {
  // @include
  private static class MaxWithCount {
    public Integer max;
    public Integer count;

    public MaxWithCount(Integer max, Integer count) {
      this.max = max;
      this.count = count;
    }
  }

  public static class Stack {
    private Deque<Integer> element = new LinkedList<>();
    private Deque<MaxWithCount> cachedMaxWithCount = new LinkedList<>();

    public boolean empty() { return element.isEmpty(); }

    public Integer max() {
      if (empty()) {
        throw new IllegalStateException("max(): empty stack");
      }
      return cachedMaxWithCount.peekFirst().max;
    }

    public Integer pop() {
      if (empty()) {
        throw new IllegalStateException("pop(): empty stack");
      }
      Integer popElement = element.removeFirst();
      if (popElement.equals(cachedMaxWithCount.peekFirst().max)) {
        cachedMaxWithCount.peekFirst().count
            = cachedMaxWithCount.peekFirst().count - 1;
        if (cachedMaxWithCount.peekFirst().count.equals(0)) {
          cachedMaxWithCount.removeFirst();
        }
      }
      return popElement;
    }

    public void push(Integer x) {
      element.addFirst(x);
      if (!cachedMaxWithCount.isEmpty()) {
        if (Integer.compare(x, cachedMaxWithCount.peekFirst().max) == 0) {
          cachedMaxWithCount.peekFirst().count
              = cachedMaxWithCount.peekFirst().count + 1;
        } else if (Integer.compare(x, cachedMaxWithCount.peekFirst().max) > 0) {
          cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
        }
      } else {
        cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
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
