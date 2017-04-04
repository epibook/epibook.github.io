package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {
  private static int distance(List<List<Integer>> sortedArrays,
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
    public int val;
    public int idx;

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof ArrayData)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      ArrayData that = (ArrayData)obj;
      return this.val == that.val && this.idx == that.idx;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(val, idx); }
    // clang-format on
  }

  public static int findMinDistanceSortedArrays(
      List<List<Integer>> sortedArrays) {
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
      result = Math.min(result,
                        currentHeads.last().val - currentHeads.first().val);
      int idxNextMin = currentHeads.first().idx;
      // Return if some array has no remaining elements.
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

  private static void recGenAnswer(List<List<Integer>> sortedArrays,
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

  private static int bruteForceGenAnswer(List<List<Integer>> sortedArrays) {
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
      List<List<Integer>> sortedArrays = new ArrayList<>();
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
