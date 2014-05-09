// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.epi.utils.Pair;

class SmallestSubarrayCoveringSetStream {
  // @include
  public static Pair<Integer, Integer> findSmallestSubarrayCoveringSubset(
      ArrayList<String> A, ArrayList<String> Q) {
    LinkedList<Integer> loc = new LinkedList<Integer>(); // tracks the last
                                                         // occurrence (index)
                                                         // of each string in
                                                         // Q.
    HashMap<String, LinkedList<Integer>.Node> dict = new HashMap<>();
    for (String s : Q) {
      dict.put(s, null);
    }

    Pair<Integer, Integer> res = new Pair<Integer, Integer>(-1, -1);
    int idx = 0;
    String s = new String();
    Iterator<String> sin = A.iterator();
    while (sin.hasNext()) {
      s = sin.next();
      if (dict.containsKey(s)) { // s is in Q.
        LinkedList<Integer>.Node it = dict.get(s);
        if (it != null) {
          loc.erase(it);
        }

        LinkedList<Integer>.Node back = loc.pushBack(idx);
        dict.put(s, back);
      }

      if (loc.size() == Q.size() && // found |Q| keywords.
          ((res.getFirst() == -1 && res.getSecond() == -1) || idx
              - loc.front().item < res.getSecond() - res.getFirst())) {
        res.setFirst(loc.front().item);
        res.setSecond(idx);
      }
      ++idx;
    }
    return res;
  }
  // @exclude
}
