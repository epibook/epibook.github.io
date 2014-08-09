// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.equal;
import static com.epi.utils.Utils.swap;

public class NextPermutation {
  // @include
  public static List<Integer> nextPermutation(List<Integer> p) {
    int k = p.size() - 2;
    while (k >= 0 && p.get(k) >= p.get(k + 1)) {
      --k;
    }
    if (k == -1) {
      return Collections.emptyList(); // p is the last permutation.
    }

    // Find the smallest entry after index k that is bigger than p[k].
    int l = 0;
    for (int i = k + 1; i < p.size(); ++i) {
      if (p.get(i) > p.get(k)) {
        l = i;
      } else {
        // Since p[k+1], p[k+2], ... is decreasing, p[l] must be the result.
        break;
      }
    }
    swap(p, k, l);

    // Reversing this subarray sorts it in increasing order.
    Collections.reverse(p.subList(k + 1, p.size()));
    return p;
  }
  // @exclude

  // derived from http://codeforces.com/blog/entry/3980
  private static List<Integer> goldenNextPermutation(final List<Integer> c) {
    // 1. finds the largest k, that c[k] < c[k+1]
    List<Integer> result = new ArrayList<>(c);
    int first = getFirst(result);
    if (first == -1) { // no greater permutation
      return Collections.emptyList();
    }

    // 2. find last index toSwap, that c[k] < c[toSwap]
    int toSwap = c.size() - 1;
    while (c.get(first).compareTo(c.get(toSwap)) >= 0) {
      --toSwap;
    }

    // 3. swap elements with indexes first and last
    swap(result, first++, toSwap);

    // 4. reverse sequence from k+1 to n (inclusive)
    toSwap = c.size() - 1;
    while (first < toSwap) {
      swap(result, first++, toSwap--);
    }

    return result;
  }

  // finds the largest k, that c[k] < c[k+1]
  // if no such k exists (there is not greater permutation), return -1
  private static int getFirst(final List<Integer> c) {
    for (int i = c.size() - 2; i >= 0; --i) {
      if (c.get(i).compareTo(c.get(i + 1)) < 0) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    for (int times = 0; times < 1000; ++times) {
      List<Integer> p = new ArrayList<>();
      if (args.length > 1) {
        for (int i = 1; i < args.length; ++i) {
          p.add(Integer.valueOf(args[i]));
        }
      } else {
        Random gen = new Random();
        int n = (args.length == 1 ? Integer.valueOf(args[0]) : (gen
            .nextInt(100) + 1));
        for (int i = 0; i < n; ++i) {
          p.add(gen.nextInt(n));
        }
      }
      // System.out.print("p = ");
      // simplePrint(p);
      // System.out.println();

      // goldenNextPermutation does not change does not change p
      List<Integer> gold = goldenNextPermutation(p);
      // System.out.print("gold = ");
      // simplePrint(gold);
      // System.out.println();

      List<Integer> ans = nextPermutation(p);
      // System.out.print("ans = ");
      // simplePrint(ans);
      // System.out.println();

      assert equal(gold, ans);
    }
  }
}
