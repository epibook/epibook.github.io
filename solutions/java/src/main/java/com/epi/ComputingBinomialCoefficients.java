package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ComputingBinomialCoefficients {
  // @include
  public static int computeBinomialCoefficients(int n, int k) {
    int[] table = new int[k + 1];
    for (int j = 0; j < k + 1; ++j) {
      table[j] = 0;
    }
    table[0] = 1; // C(0, 0).
    // C(i, j) = C(i - 1, j) + C(i - 1, j - 1).
    for (int i = 1; i <= n; ++i) {
      for (int j = Math.min(i, k); j >= 1; --j) {
        table[j] = table[j] + table[j - 1];
      }
      table[0] = 1; // One way to select zero element.
    }
    return table[k];
  }
  // @exclude

  private static int checkAns(int n, int k) {
    List<Integer> number = new ArrayList<>();
    for (int i = 0; i < k; ++i) {
      number.add(n - i);
    }

    List<Integer> temp = new ArrayList<>();
    for (int i = 2; i <= k; ++i) {
      boolean find = false;
      for (int j = 0; j < number.size(); j++) {
        if ((number.get(j) % i) == 0) {
          number.set(j, number.get(j) / i);
          find = true;
          break;
        }
      }
      if (!find) {
        temp.add(i);
      }
    }

    int res = 1;
    for (int a : number) {
      res *= a;
    }

    for (int a : temp) {
      res /= a;
    }

    return res;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, k;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(21) + 1;
        k = r.nextInt(n) + 1;
      }

      int res = computeBinomialCoefficients(n, k);
      assert(res == checkAns(n, k));
      System.out.println(n + " out of " + k + " = " + res);
      if (args.length == 2) {
        break;
      }
    }
  }
}
