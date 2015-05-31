package com.epi;

import com.epi.utils.Pair;

import java.util.Random;

// import static com.epi.utils.Utils.simplePrint;

public class GassingUp {
  // @include
  static int findStartCity(int[] G, int[] D) {
    int carry = 0;
    Pair<Integer, Integer> min = new Pair<>(0, 0);
    for (int i = 1; i < G.length; ++i) {
      carry += G[i - 1] - D[i - 1];
      if (carry < min.getSecond()) {
        min = new Pair<>(i, carry);
      }
    }
    return min.getFirst();
  }
  // @exclude

  static void checkAns(int[] G, int[] D, int c) {
    int s = c;
    int gas = 0;
    do {
      gas += G[s] - D[s];
      assert(gas >= 0);
      s = (s + 1) % G.length;
    } while (s != c);
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      int[] G = new int[n], D = new int[n];
      int sum = 0;
      for (int i = 0; i < n; ++i) {
        int x = gen.nextInt(200) + 1;
        sum += x;
        G[i] = x;
      }
      sum -= n;
      for (int i = 0; i < n; ++i) {
        int x = 0;
        if (sum > 0) {
          x = gen.nextInt(sum) + 1;
        }
        D[i] = x + 1;
        sum -= x;
      }
      D[D.length - 1] += sum;

      /*
       * simplePrint(G); System.out.println();
       *
       * simplePrint(D); System.out.println();
       */
      int c = findStartCity(G, D);
      System.out.println("start city = " + c);
      checkAns(G, D, c);
    }
  }
}
