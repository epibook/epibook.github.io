package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LongestSubarrayKImproved {
  // @include
  public static int findLongestSubarrayLessEqualK(List<Integer> A, int k) {
    // Build the prefix sum according to A.
    List<Integer> prefixSum = new ArrayList<>();
    int sum = 0;
    for (int a : A) {
      sum += a;
      prefixSum.add(sum);
    }

    // Early returns if the sum of A is smaller than or equal to k.
    if (prefixSum.get(prefixSum.size() - 1) <= k) {
      return A.size();
    }

    // Builds minPrefixSum.
    List<Integer> minPrefixSum = new ArrayList<>(prefixSum);
    for (int i = minPrefixSum.size() - 2; i >= 0; --i) {
      minPrefixSum.set(i,
                       Math.min(minPrefixSum.get(i), minPrefixSum.get(i + 1)));
    }

    int a = 0, b = 0, maxLength = 0;
    while (a < A.size() && b < A.size()) {
      int minCurrSum = a > 0 ? minPrefixSum.get(b) - prefixSum.get(a - 1)
                             : minPrefixSum.get(b);
      if (minCurrSum <= k) {
        int currLength = b - a + 1;
        if (currLength > maxLength) {
          maxLength = currLength;
        }
        ++b;
      } else { // minCurrSum > k.
        ++a;
      }
    }
    return maxLength;
  }
  // @exclude

  // O(n^2) checking answer
  private static void checkAnswer(List<Integer> A, int ans, int k) {
    List<Integer> sum = new ArrayList<>(A.size() + 1);
    sum.add(0);
    for (int i = 0; i < A.size(); ++i) {
      sum.add(sum.get(i) + A.get(i));
    }
    if (ans != 0) {
      for (int i = 0; i < sum.size(); ++i) {
        for (int j = i + 1; j < sum.size(); ++j) {
          if (sum.get(j) - sum.get(i) <= k) {
            assert((j - i) <= ans);
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

  private static void smallTest() {
    List<Integer> A = Arrays.asList(1, 1);
    int k = 0;
    int res = findLongestSubarrayLessEqualK(A, k);
    assert(res == 0);
    k = -100;
    res = findLongestSubarrayLessEqualK(A, k);
    assert(res == 0);
  }

  public static void main(String[] args) {
    smallTest();
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
      int ans = findLongestSubarrayLessEqualK(A, k);
      System.out.println(k + " " + ans);
      checkAnswer(A, ans, k);
    }
  }
}
