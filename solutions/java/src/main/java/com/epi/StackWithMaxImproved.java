package com.epi;

import java.util.LinkedList;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackWithMaxImproved {
  // @include
  public static class Stack<T extends Comparable<T>> {
    private LinkedList<T> s = new LinkedList<T>();
    private LinkedList<Pair<T, Integer>> aux = new LinkedList<Pair<T, Integer>>();

    public boolean empty() {
      return s.isEmpty();
    }

    public T max() {
      if (!empty()) {
        return aux.peek().getFirst();
      }
      throw new RuntimeException("empty_stack");
    }

    public T pop() {
      if (empty()) {
        throw new RuntimeException("empty_stack");
      }
      T ret = s.pop();
      if (ret.equals(aux.peek().getFirst())) {
        aux.peek().setSecond(aux.peek().getSecond() - 1);
        if (aux.peek().getSecond().equals(0)) {
          aux.pop();
        }
      }
      return ret;
    }

    public void push(T x) {
      s.push(x);
      if (!aux.isEmpty()) {
        if (x.compareTo(aux.peek().getFirst()) == 0) {
          aux.peek().setSecond(aux.peek().getSecond() + 1);
        } else if (x.compareTo(aux.peek().getFirst()) > 0) {
          aux.push(new Pair<T, Integer>(x, 1));
        }
      } else {
        aux.push(new Pair<T, Integer>(x, 1));
      }
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
