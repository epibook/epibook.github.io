package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MinimumDistance3SortedArrays {
  private static int distance(List<? extends List<Integer>> arrs,
                              List<Integer> idx) {
    int maxVal = Integer.MIN_VALUE;
    int minVal = Integer.MAX_VALUE;
    for (int i = 0; i < idx.size(); ++i) {
      maxVal = Math.max(maxVal, arrs.get(i).get(idx.get(i)));
      minVal = Math.min(minVal, arrs.get(i).get(idx.get(i)));
    }
    return maxVal - minVal;
  }

  // @include
  public static class ArrData implements Comparable<ArrData> {
    public int idx;
    public int val;

    public ArrData(int idx, int val) {
      this.idx = idx;
      this.val = val;
    }

    @Override
    public int compareTo(ArrData o) {
      int result = Integer.valueOf(val).compareTo(o.val);
      if (result == 0) {
        result = Integer.valueOf(idx).compareTo(o.idx);
      }
      return result;
    }
  }

  public static int findMinDistanceSortedArrays(
      List<? extends List<Integer>> arrs) {
    // Pointers for each of arrs.
    List<Integer> idx = new ArrayList<>(arrs.size());
    for (List<Integer> arr : arrs) {
      idx.add(0);
    }
    int minDis = Integer.MAX_VALUE;
    NavigableSet<ArrData> currentHeads = new TreeSet<>();

    // Each of arrs puts its minimum element into current_heads.
    for (int i = 0; i < arrs.size(); ++i) {
      if (idx.get(i) >= arrs.get(i).size()) {
        return minDis;
      }
      currentHeads.add(new ArrData(i, arrs.get(i).get(idx.get(i))));
    }

    while (true) {
      minDis = Math.min(minDis, currentHeads.last().val
          - currentHeads.first().val);
      int tar = currentHeads.first().idx;
      // Return if there is no remaining element in one array.
      idx.set(tar, idx.get(tar) + 1);
      if (idx.get(tar) >= arrs.get(tar).size()) {
        return minDis;
      }
      currentHeads.pollFirst();
      currentHeads.add(new ArrData(tar, arrs.get(tar).get(idx.get(tar))));
    }
  }
  // @exclude

  private static Integer ans;

  private static void recGenAnswer(List<? extends List<Integer>> arrs,
                                   List<Integer> idx, int level) {
    if (level == arrs.size()) {
      ans = Math.min(distance(arrs, idx), ans);
      return;
    }
    for (int i = 0; i < arrs.get(level).size(); ++i) {
      idx.set(level, i);
      recGenAnswer(arrs, idx, level + 1);
    }
  }

  private static int bruteForceGenAnswer(List<? extends List<Integer>> arrs) {
    List<Integer> idx = new ArrayList<>(arrs.size());
    for (List<Integer> arr : arrs) {
      idx.add(0);
    }
    ans = Integer.MAX_VALUE;
    recGenAnswer(arrs, idx, 0);
    System.out.println(ans);
    return ans;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      List<ArrayList<Integer>> arrs = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(5) + 1;
      }
      for (int i = 0; i < n; ++i) {
        int size = r.nextInt(40) + 1;
        arrs.add(new ArrayList<Integer>(size));
        for (int j = 0; j < size; ++j) {
          arrs.get(i).add(r.nextInt(10000));
        }
        Collections.sort(arrs.get(i));
      }
      int ans = findMinDistanceSortedArrays(arrs);
      System.out.println(ans);
      assert (bruteForceGenAnswer(arrs) == ans);
    }
  }
}
