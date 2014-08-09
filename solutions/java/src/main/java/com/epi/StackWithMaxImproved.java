package com.epi;

import com.epi.utils.Pair;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackWithMaxImproved {
  // @include
  public static class Stack {
    private LinkedList<Integer> s = new LinkedList<>();
    private LinkedList<Pair<Integer, Integer>> aux = new LinkedList<>();

    public boolean empty() {
      return s.isEmpty();
    }

    public Integer max() {
      if (!empty()) {
        return aux.peek().getFirst();
      }
      throw new RuntimeException("empty_stack");
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("empty_stack");
      }
      Integer ret = s.pop();
      if (ret.equals(aux.peek().getFirst())) {
        aux.peek().setSecond(aux.peek().getSecond() - 1);
        if (aux.peek().getSecond().equals(0)) {
          aux.pop();
        }
      }
      return ret;
    }

    public void push(Integer x) {
      s.push(x);
      if (!aux.isEmpty()) {
        if (x.compareTo(aux.peek().getFirst()) == 0) {
          aux.peek().setSecond(aux.peek().getSecond() + 1);
        } else if (x.compareTo(aux.peek().getFirst()) > 0) {
          aux.push(new Pair<>(x, 1));
        }
      } else {
        aux.push(new Pair<>(x, 1));
      }
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
