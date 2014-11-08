package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinations {
  // @include
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();
    combinationsHelper(n, k, 1, ans, result);
    return result;
  }

  private static void combinationsHelper(int n, int k, int start,
                                         List<Integer> ans,
                                         List<List<Integer>> result) {
    if (ans.size() == k) {
      result.add(new ArrayList<>(ans));
      return;
    }

    for (int i = start; i <= n && k - ans.size() <= n - i + 1; ++i) {
      ans.add(i);
      combinationsHelper(n, k, i + 1, ans, result);
      ans.remove(ans.size() - 1);
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, k;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(10) + 1;
      k = r.nextInt(n + 1);
    }
    List<List<Integer>> res = combinations(n, k);
    System.out.println("n = " + n + ", k = " + k);
    System.out.println(res);
  }
}
