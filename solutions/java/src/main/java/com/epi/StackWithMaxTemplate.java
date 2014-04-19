package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackWithMaxTemplate {
  // @include
  public static class Stack<T extends Comparable<T>> {
    private LinkedList<Pair<T, T>> s = new LinkedList<Pair<T, T>>();

    public boolean empty() {
      return s.isEmpty();
    }

    public T max() {
      if (!empty()) {
        return s.peek().getSecond();
      }
      throw new RuntimeException("empty stack");
    }

    public T pop() {
      if (empty()) {
        throw new RuntimeException("empty stack");
      }
      return s.pop().getFirst();
    }

    public void push(T x) {
      s.push(new Pair<T, T>(x, Collections.max(Arrays.asList(x, empty() ? x : s
          .peek().getSecond()))));
    }
  }

  // @exclude

  public static void main(String[] args) {
    Stack<Integer> s = new Stack<Integer>();
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
