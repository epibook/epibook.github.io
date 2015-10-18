package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// import static com.epi.utils.Utils.simplePrint;

public class GassingUp {
  // @include
  private static class CityAndRemainingGas {
    public Integer city;
    public Integer remainingGas;

    public CityAndRemainingGas(Integer city, Integer remainingGas) {
      this.city = city;
      this.remainingGas = remainingGas;
    }
  }

  static int findStartCity(List<Integer> G, List<Integer> D) {
    int carry = 0;
    CityAndRemainingGas min = new CityAndRemainingGas(0, 0);
    for (int i = 1; i < G.size(); ++i) {
      carry += G.get(i - 1) - D.get(i - 1);
      if (carry < min.remainingGas) {
        min = new CityAndRemainingGas(i, carry);
      }
    }
    return min.city;
  }
  // @exclude

  static void checkAns(List<Integer> G, List<Integer> D, int c) {
    int s = c;
    int gas = 0;
    do {
      gas += G.get(s) - D.get(s);
      assert(gas >= 0);
      s = (s + 1) % G.size();
    } while (s != c);
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10000) + 1;
      }
      List<Integer> G = new ArrayList<>(n);
      int sum = 0;
      for (int i = 0; i < n; ++i) {
        int x = gen.nextInt(200) + 1;
        sum += x;
        G.add(x);
      }
      List<Integer> D = new ArrayList<>(n);
      sum -= n;
      for (int i = 0; i < n; ++i) {
        int x = 0;
        if (sum > 0) {
          x = gen.nextInt(sum) + 1;
        }
        D.add(x + 1);
        sum -= x;
      }
      D.set(D.size() - 1, D.get(D.size() - 1) + sum);

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
