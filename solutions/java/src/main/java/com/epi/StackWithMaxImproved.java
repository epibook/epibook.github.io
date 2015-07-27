package com.epi;

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
    private LinkedList<Integer> element = new LinkedList<>();
    private LinkedList<MaxWithCount> cachedMaxWithCount = new LinkedList<>();

    public boolean empty() { return element.isEmpty(); }

    public Integer max() {
      if (!empty()) {
        return cachedMaxWithCount.peek().max;
      }
      throw new RuntimeException("max(): empty stack");
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("pop(): empty stack");
      }
      Integer popElement = element.pop();
      if (popElement.equals(cachedMaxWithCount.peek().max)) {
        cachedMaxWithCount.peek().count = cachedMaxWithCount.peek().count - 1;
        if (cachedMaxWithCount.peek().count.equals(0)) {
          cachedMaxWithCount.pop();
        }
      }
      return popElement;
    }

    public void push(Integer x) {
      element.push(x);
      if (!cachedMaxWithCount.isEmpty()) {
        if (x.compareTo(cachedMaxWithCount.peek().max) == 0) {
          cachedMaxWithCount.peek().count = cachedMaxWithCount.peek().count + 1;
        } else if (x.compareTo(cachedMaxWithCount.peek().max) > 0) {
          cachedMaxWithCount.push(new MaxWithCount(x, 1));
        }
      } else {
        cachedMaxWithCount.push(new MaxWithCount(x, 1));
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
