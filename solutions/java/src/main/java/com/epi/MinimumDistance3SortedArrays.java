package com.epi;

import java.util.*;

public class MinimumDistance3SortedArrays {
  private static int distance(List<? extends List<Integer>> sortedArrays,
                              List<Integer> idx) {
    int maxVal = Integer.MIN_VALUE;
    int minVal = Integer.MAX_VALUE;
    for (int i = 0; i < idx.size(); ++i) {
      maxVal = Math.max(maxVal, sortedArrays.get(i).get(idx.get(i)));
      minVal = Math.min(minVal, sortedArrays.get(i).get(idx.get(i)));
    }
    return maxVal - minVal;
  }

  // @include
  public static class ArrayData implements Comparable<ArrayData> {
    public int idx;
    public int val;

    public ArrayData(int idx, int val) {
      this.idx = idx;
      this.val = val;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.valueOf(val).compareTo(o.val);
      if (result == 0) {
        result = Integer.valueOf(idx).compareTo(o.idx);
      }
      return result;
    }
  }

  public static int findMinDistanceSortedArrays(
      List<? extends List<Integer>> sortedArrays) {
    // Indices into each of the arrays.
    List<Integer> heads = new ArrayList<>(sortedArrays.size());
    for (List<Integer> arr : sortedArrays) {
      heads.add(0);
    }
    int result = Integer.MAX_VALUE;
    NavigableSet<ArrayData> currentHeads = new TreeSet<>();

    // Adds the minimum element of each array in to currentHeads.
    for (int i = 0; i < sortedArrays.size(); ++i) {
      currentHeads.add(new ArrayData(i, sortedArrays.get(i).get(heads.get(i))));
    }

    while (true) {
      result =
          Math.min(result, currentHeads.last().val - currentHeads.first().val);
      int idxNextMin = currentHeads.first().idx;
      // Return if there is no remaining element in one array.
      heads.set(idxNextMin, heads.get(idxNextMin) + 1);
      if (heads.get(idxNextMin) >= sortedArrays.get(idxNextMin).size()) {
        return result;
      }
      currentHeads.pollFirst();
      currentHeads.add(new ArrayData(
          idxNextMin, sortedArrays.get(idxNextMin).get(heads.get(idxNextMin))));
    }
  }
  // @exclude

  private static Integer ans;

  private static void recGenAnswer(List<? extends List<Integer>> sortedArrays,
                                   List<Integer> idx, int level) {
    if (level == sortedArrays.size()) {
      ans = Math.min(distance(sortedArrays, idx), ans);
      return;
    }
    for (int i = 0; i < sortedArrays.get(level).size(); ++i) {
      idx.set(level, i);
      recGenAnswer(sortedArrays, idx, level + 1);
    }
  }

  private static int bruteForceGenAnswer(
      List<? extends List<Integer>> sortedArrays) {
    List<Integer> idx = new ArrayList<>(sortedArrays.size());
    for (List<Integer> arr : sortedArrays) {
      idx.add(0);
    }
    ans = Integer.MAX_VALUE;
    recGenAnswer(sortedArrays, idx, 0);
    System.out.println(ans);
    return ans;
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      List<ArrayList<Integer>> sortedArrays = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(5) + 1;
      }
      for (int i = 0; i < n; ++i) {
        int size = r.nextInt(40) + 1;
        sortedArrays.add(new ArrayList<Integer>(size));
        for (int j = 0; j < size; ++j) {
          sortedArrays.get(i).add(r.nextInt(10000));
        }
        Collections.sort(sortedArrays.get(i));
      }
      int ans = findMinDistanceSortedArrays(sortedArrays);
      System.out.println(ans);
      assert(bruteForceGenAnswer(sortedArrays) == ans);
    }
  }
}
