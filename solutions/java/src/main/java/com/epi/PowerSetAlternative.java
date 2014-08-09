package com.epi;

import java.util.*;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PowerSetAlternative {
  // @include
  public static void generatePowerSet(List<Integer> S) {
    Deque<Integer> res = new LinkedList<>();
    generatePowerSetHelper(S, 0, res);
  }

  private static void generatePowerSetHelper(List<Integer> S, int idx,
                                             Deque<Integer> res) {
    if (!res.isEmpty()) {
      // Print the subset.
      Iterator<Integer> iterator = res.iterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
        if (iterator.hasNext()) {
          System.out.print(",");
        }
      }
    }
    System.out.println();

    for (int i = idx; i < S.size(); ++i) {
      res.offerLast(S.get(i));
      generatePowerSetHelper(S, i + 1, res);
      res.pollLast();
    }
  }
  // @exclude

  public static void main(String[] args) {
    List<Integer> S = new ArrayList<>();
    if (args.length >= 1) {
      for (String arg : args) {
        S.add(Integer.parseInt(arg));
      }
    } else {
      Random r = new Random();
      int count = r.nextInt(10) + 1;
      for (int i = 0; i < count; ++i) {
        S.add(i);
      }
    }
    generatePowerSet(S);
  }
}
