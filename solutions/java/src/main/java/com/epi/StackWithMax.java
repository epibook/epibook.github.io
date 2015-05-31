package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class StackWithMax {
  // @include
  public static class Stack {
    // Stores (element, cached maximum) pair.
    private LinkedList<Pair<Integer, Integer>> elementWithCachedMax =
        new LinkedList<>();

    public boolean empty() { return elementWithCachedMax.isEmpty(); }

    public Integer max() {
      if (!empty()) {
        return elementWithCachedMax.peek().getSecond();
      }
      throw new RuntimeException("max(): empty stack");
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("pop(): empty stack");
      }
      return elementWithCachedMax.pop().getFirst();
    }

    public void push(Integer x) {
      elementWithCachedMax.push(
          new Pair<>(x, Collections.max(Arrays.asList(x, empty() ? x : max()))));
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
