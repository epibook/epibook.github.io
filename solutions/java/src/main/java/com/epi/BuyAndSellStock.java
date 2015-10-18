// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuyAndSellStock {
  // @include
  public static double computeMaxProfit(List<Double> prices) {
    double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
    for (Double price : prices) {
      maxProfit = Math.max(maxProfit, price - minPrice);
      minPrice = Math.min(minPrice, price);
    }
    return maxProfit;
  }
  // @exclude

  // O(n^2) checking answer.
  private static double checkAns(List<Double> prices) {
    double cap = 0;
    for (int i = 1; i < prices.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        cap = Math.max(cap, prices.get(i) - prices.get(j));
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
        n = gen.nextInt(10000) + 1;
      }
      List<Double> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextDouble() * 10000);
      }
      System.out.println(computeMaxProfit(A));
      assert(Double.compare(checkAns(A), computeMaxProfit(A)) == 0);
    }
  }
}
