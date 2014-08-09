package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackWithMax {
  // @include
  public static class Stack {
    private LinkedList<Pair<Integer, Integer>> s = new LinkedList<>();

    public boolean empty() {
      return s.isEmpty();
    }

    public Integer max() {
      if (!empty()) {
        return s.peek().getSecond();
      }
      throw new RuntimeException("empty stack");
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("empty stack");
      }
      return s.pop().getFirst();
    }

    public void push(Integer x) {
      s.push(new Pair<>(x, Collections.max(Arrays.asList(x, empty() ? x : s
          .peek().getSecond()))));
    }
  }
  // @exclude

  public static void main(String[] args) {
    Stack s = new Stack();
    s.push(1);
    s.push(2);
    assert (s.max() == 2);
    System.out.println(s.max()); // 2
    System.out.println(s.pop()); // 2
    assert (s.max() == 1);
    System.out.println(s.max()); // 1
    s.push(3);
    s.push(2);
    assert (s.max() == 3);
    System.out.println(s.max()); // 3
    s.pop();
    assert (s.max() == 3);
    System.out.println(s.max()); // 3
    s.pop();
    assert (s.max() == 1);
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
