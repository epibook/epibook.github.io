package com.epi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PowerSetAlternative {
  // @include
  private static void generatePowerSetHelper(ArrayList<Integer> S, int idx,
      LinkedList<Integer> res) {
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

  public static void generatePowerSet(ArrayList<Integer> S) {
    LinkedList<Integer> res = new LinkedList<Integer>();
    generatePowerSetHelper(S, 0, res);
  }

  // @exclude

  public static void main(String[] args) {
    ArrayList<Integer> S = new ArrayList<Integer>();
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
