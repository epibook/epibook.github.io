package com.epi;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CollatzConjecture {
  // @include
  public static boolean testCollatzConjecture(int n) {
    // Stores odd numbers already tested to converge to 1.
    Set<Long> verifiedNumbers = new HashSet<>();

    // Starts from 3, since hypothesis holds trivially for 1 and 2.
    for (int i = 3; i <= n; i += 2) {
      Set<Long> sequence = new HashSet<>();
      long testI = i;
      while (testI >= i) {
        if (!sequence.add(testI)) {
          // We previously encountered testI, so the Collatz sequence
          // has fallen into a loop. This disproves the hypothesis, so
          // we short-circuit, returning false.
          return false;
        }

        if ((testI % 2) != 0) { // Odd number
          if (!verifiedNumbers.add(testI)) {
            break; // testI has already been verified to converge to 1.
          }
          long nextTestI = 3 * testI + 1; // Multiply by 3 and add 1.
          if (nextTestI <= testI) {
            throw new ArithmeticException("Collatz sequence overflow for " + i);
          }
          testI = nextTestI;
        } else {
          testI /= 2; // Even number, halve it.
        }
      }
    }
    return true;
  }
  // @exclude

  // Slow check without any pruning.
  public static boolean check(int n) {
    for (int i = 2; i <= n; ++i) {
      int testI = i;
      while (testI != 1 && testI >= i) {
        if ((testI % 2) != 0) {
          testI = testI * 3 + 1;
        } else {
          // clang-format off
          testI >>>= 1;
          // clang-format on
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
      assert(res == check(n));
    }
  }
}
