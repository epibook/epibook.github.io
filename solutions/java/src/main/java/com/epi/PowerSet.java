package com.epi;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PowerSet {
  // @include
  private static final double LOG_2 = Math.log(2);

  public static void generatePowerSet(ArrayList<Integer> S) {
    for (int i = 0; i < (1 << S.size()); ++i) {
      int x = i;
      while (x != 0) {
        int tar = (int) (Math.log(x & ~(x - 1)) / LOG_2);
        System.out.print(S.get(tar));
        if ((x &= x - 1) != 0) {
          System.out.print(",");
        }
      }
      System.out.println();
    }
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
