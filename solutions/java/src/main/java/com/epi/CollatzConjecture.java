package com.epi;

import java.util.HashSet;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CollatzConjecture {
  // @include
  public static boolean testCollatzConjecture(int n) {
    // Stores the odd number that converges to 1.
    HashSet<Long> table = new HashSet<Long>();

    // Start from 2 since we don't need to test 1.
    for (int i = 2; i <= n; ++i) {
      HashSet<Long> sequence = new HashSet<Long>();
      long testI = i;
      while (testI >= i) {
        // Emplace failed, it mean we met some number encountered before.
        if (!sequence.add(testI)) {
          return false;
        }

        if ((testI & 1) != 0) { // odd number
          if (!table.add(testI)) {
            break; // this number have already be proven to converge to 1.
          }
          long nextTestI = 3 * testI + 1; // 3n + 1.
          if (nextTestI <= testI) {
            throw new RuntimeException("test process overflow");
          }
          testI = nextTestI;
        } else { // even number.
          testI >>= 1; // n / 2.
        }
      }
      table.remove((long) i); // removes i from table.
    }
    return true;
  }

  // @exclude

  // Slow check without any pruning.
  public static boolean check(int n) {
    for (int i = 2; i <= n; ++i) {
      int testI = i;
      while (testI != 1 && testI >= i) {
        if ((testI & 1) != 0) {
          testI = testI * 3 + 1;
        } else {
          testI >>= 1;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(100000) + 1;
      }
      System.out.println("n = " + n);
      boolean res = testCollatzConjecture(n);
      System.out.println(res);
      assert (res == check(n));
    }
  }
}
