// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuyAndSellStockTwice {
  // @include
  public static double buyAndSellStockTwice(List<Double> prices) {
    double maxTotalProfit = 0.0;
    List<Double> firstBuySellProfits = new ArrayList<>();
    double minPriceSoFar = Double.MAX_VALUE;

    // Forward phase. For each day, we record maximum profit if we
    // sell on that day.
    for (int i = 0; i < prices.size(); ++i) {
      minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
      maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
      firstBuySellProfits.add(maxTotalProfit);
    }

    // Backward phase. For each day, find the maximum profit if we make
    // the second buy on that day.
    double maxPriceSoFar = Double.MIN_VALUE;
    for (int i = prices.size() - 1; i > 0; --i) {
      maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
      maxTotalProfit
          = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i)
                                         + firstBuySellProfits.get(i - 1));
    }
    return maxTotalProfit;
  }
  // @exclude

  // O(n^4) checking answer.
  private static double checkAns(List<Double> h) {
    double cap = 0.0;
    for (int i = 1; i < h.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        cap = Math.max(cap, h.get(i) - h.get(j));
      }
    }
    for (int a = 0; a < h.size(); ++a) {
      for (int b = a + 1; b < h.size(); ++b) {
        double temp = h.get(b) - h.get(a);
        for (int c = b + 1; c < h.size(); ++c) {
          for (int d = c + 1; d < h.size(); ++d) {
            double profit = h.get(d) - h.get(c) + temp;
            cap = Math.max(cap, profit);
          }
        }
      }
    }
    return cap;
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(100) + 1;
      }
      List<Double> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextDouble() * 10000);
      }
      System.out.println("n = " + n);
      assert(Double.compare(checkAns(A), buyAndSellStockTwice(A)) == 0);
    }
  }
}
