package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.upperBound2;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LongestSubarrayK {
  // @include
  public static Pair<Integer, Integer> findLongestSubarrayLessEqualK(
      List<Integer> A, int k) {
    // Build the prefix sum according to A.
    List<Integer> prefixSum = new ArrayList<>();
    int sum = 0;
    for (int a : A) {
      sum += a;
      prefixSum.add(sum);
    }

    List<Integer> minPrefixSum = new ArrayList<>(prefixSum);

    for (int i = minPrefixSum.size() - 2; i >= 0; --i) {
      minPrefixSum.set(i, Math.min(minPrefixSum.get(i), minPrefixSum.get(i + 1)));
    }

    Pair<Integer, Integer> arrIdx =
        new Pair<>(0, upperBound2(minPrefixSum, k) - 1);
    for (int i = 0; i < prefixSum.size(); ++i) {
      int idx = upperBound2(minPrefixSum, k + prefixSum.get(i)) - 1;
      if (idx - i - 1 > arrIdx.getSecond() - arrIdx.getFirst()) {
        arrIdx = new Pair<>(i + 1, idx);
      }
    }
    return arrIdx;
  }
  // @exclude

  // O(n^2) checking answer
  private static void checkAnswer(List<Integer> A, Pair<Integer, Integer> ans,
                                  int k) {
    List<Integer> sum = new ArrayList<>(A.size() + 1);
    sum.add(0);
    for (int i = 0; i < A.size(); ++i) {
      sum.add(sum.get(i) + A.get(i));
    }
    if (ans.getFirst() != -1 && ans.getSecond() != -1) {
      int s = 0;
      for (int i = ans.getFirst(); i <= ans.getSecond(); ++i) {
        s += A.get(i);
      }
      assert(s <= k);
      for (int i = 0; i < sum.size(); ++i) {
        for (int j = i + 1; j < sum.size(); ++j) {
          if (sum.get(j) - sum.get(i) <= k) {
            assert((j - i) <= (ans.getSecond() - ans.getFirst() + 1));
          }
        }
      }
    } else {
      for (int i = 0; i < sum.size(); ++i) {
        for (int j = i + 1; j < sum.size(); ++j) {
          assert(sum.get(j) - sum.get(i) > k);
        }
      }
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, k;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        k = r.nextInt(10000);
      } else {
        n = r.nextInt(10000) + 1;
        k = r.nextInt(10000);
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(2001) - 1000);
      }
      // System.out.println(A);
      Pair<Integer, Integer> ans = findLongestSubarrayLessEqualK(A, k);
      System.out.println(k + " " + ans);
      checkAnswer(A, ans, k);
    }
  }
}
