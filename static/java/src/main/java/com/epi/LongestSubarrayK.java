package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongestSubarrayK {
  // @include
  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
    // @exclude
    // clang-format off
    @Override
    public String toString() { return "[" + start + "," + end + "]"; }
    // clang-format on
    // @include
  }

  public static Subarray findLongestSubarrayLessEqualK(List<Integer> A, int k) {
    // Build the prefix sum according to A.
    List<Integer> prefixSum = new ArrayList<>();
    int sum = 0;
    for (int a : A) {
      sum += a;
      prefixSum.add(sum);
    }

    List<Integer> minPrefixSum = new ArrayList<>(prefixSum);

    for (int i = minPrefixSum.size() - 2; i >= 0; --i) {
      minPrefixSum.set(i,
                       Math.min(minPrefixSum.get(i), minPrefixSum.get(i + 1)));
    }

    Subarray arrIdx = new Subarray(0, firstGreaterThanKey(minPrefixSum, k) - 1);
    for (int i = 0; i < prefixSum.size(); ++i) {
      int idx = firstGreaterThanKey(minPrefixSum, k + prefixSum.get(i)) - 1;
      if (idx - i - 1 > arrIdx.end - arrIdx.start) {
        arrIdx = new Subarray(i + 1, idx);
      }
    }
    return arrIdx;
  }

  // Computes the index of first entry in input array that is greater than the
  // input key.
  // Returns the size of the input array if the key is greater than or equal to
  // all entries in the array.
  public static int firstGreaterThanKey(List<Integer> arr, Integer key) {
    int len = arr.size();
    int lo = 0;
    int hi = len - 1;
    int mid = (lo + hi) / 2;
    while (true) {
      int cmp = Integer.compare(arr.get(mid), key);
      if (cmp <= 0) {
        lo = mid + 1;
        if (hi < lo) {
          return mid < len - 1 ? mid + 1 : len;
        }
      } else {
        hi = mid - 1;
        if (hi < lo) {
          return mid;
        }
      }
      mid = (lo + hi) / 2;
    }
  }
  // @exclude

  // O(n^2) checking answer
  private static void checkAnswer(List<Integer> A, Subarray ans, int k) {
    List<Integer> sum = new ArrayList<>(A.size() + 1);
    sum.add(0);
    for (int i = 0; i < A.size(); ++i) {
      sum.add(sum.get(i) + A.get(i));
    }
    if (ans.start != -1 && ans.end != -1) {
      int s = 0;
      for (int i = ans.start; i <= ans.end; ++i) {
        s += A.get(i);
      }
      assert(s <= k);
      for (int i = 0; i < sum.size(); ++i) {
        for (int j = i + 1; j < sum.size(); ++j) {
          if (sum.get(j) - sum.get(i) <= k) {
            assert((j - i) <= (ans.end - ans.start + 1));
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
      Subarray ans = findLongestSubarrayLessEqualK(A, k);
      System.out.println(k + " " + ans);
      checkAnswer(A, ans, k);
    }
  }
}
