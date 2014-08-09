package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinations {
  // @include
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();
    combinationsHelper(n, k, 0, ans, res);
    return res;
  }

  private static void combinationsHelper(int n, int k, int start,
                                         List<Integer> ans,
                                         List<List<Integer>> res) {
    if (ans.size() == k) {
      res.add(new ArrayList<>(ans));
      return;
    }

    if (k - ans.size() <= n - (start + 1)) {
      combinationsHelper(n, k, start + 1, ans, res);
    }
    ans.add(start + 1);
    combinationsHelper(n, k, start + 1, ans, res);
    ans.remove(ans.size() - 1);
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
