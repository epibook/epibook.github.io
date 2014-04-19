package com.epi;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ComputingBinomialCoefficients {
  // @include
  public static int computeBinomialCoefficients(int n, int k) {
    int[][] table = new int[n + 1][k + 1];
    // Basic case: C(i, 0) = 1.
    for (int i = 0; i <= n; ++i) {
      table[i][0] = 1;
    }
    // Basic case: C(i, i) = 1.
    for (int i = 1; i <= k; ++i) {
      table[i][i] = 1;
    }

    // C(i, j) = C(i - 1, j) + C(i - 1, j - 1).
    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j < i && j <= k; ++j) {
        table[i][j] = table[i - 1][j] + table[i - 1][j - 1];
      }
    }
    return table[n][k];
  }

  // @exclude

  private static int checkAns(int n, int k) {
    ArrayList<Integer> number = new ArrayList<Integer>();
    for (int i = 0; i < k; ++i) {
      number.add(n - i);
    }

    ArrayList<Integer> temp = new ArrayList<Integer>();
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
      assert (res == checkAns(n, k));
      System.out.println(n + " out of " + k + " = " + res);
      if (args.length == 2) {
        break;
      }
    }
  }
}
