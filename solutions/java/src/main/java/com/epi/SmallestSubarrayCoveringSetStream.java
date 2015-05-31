// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SmallestSubarrayCoveringSetStream {
  // @include
  public static Pair<Integer, Integer> findSmallestSubarrayCoveringSubset(
      String[] A, String[] Q) {
    // Tracks the last occurrence (index) of each string in Q.
    LinkedList<Integer> loc = new LinkedList<>();

    Map<String, LinkedList<Integer>.Node> dict = new HashMap<>();
    for (String s : Q) {
      dict.put(s, null);
    }

    Pair<Integer, Integer> res = new Pair<>(-1, -1);
    int idx = 0;
    String s = new String();
    for (String aA : A) {
      s = aA;
      if (dict.containsKey(s)) { // s is in Q.
        LinkedList<Integer>.Node it = dict.get(s);
        if (it != null) {
          loc.erase(it);
        }

        LinkedList<Integer>.Node back = loc.pushBack(idx);
        dict.put(s, back);
      }

      if (loc.size() == Q.length && // Found |Q| keywords.
          ((res.getFirst() == -1 && res.getSecond() == -1) ||
           idx - loc.front().item < res.getSecond() - res.getFirst())) {
        res.setFirst(loc.front().item);
        res.setSecond(idx);
      }
      ++idx;
    }
    return res;
  }
  // @exclude
}
