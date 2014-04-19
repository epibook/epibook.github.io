package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RemoveDuplicatesFromSortedArray {
  // @include
  public static int removeDuplicates(List<Integer> a) {
    if (a.isEmpty()) {
      return 0;
    }

    int i = 0, j = 1;
    while (j < a.size()) {
      if (!a.get(i).equals(a.get(j))) {
        a.set(++i, a.get(j));
      }
      ++j;
    }
    return i + 1;
  }

  // @exclude

  private static void checkAns(List<Integer> A, int n) {
    for (int i = 1; i < n; ++i) {
      assert (!A.get(i - 1).equals(A.get(i)));
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    for (int times = 0; times < 1000; ++times) {
      List<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(2001) - 1000);
      }
      Collections.sort(A);
      Set<Integer> unique = new HashSet<Integer>(A);
      int size = removeDuplicates(A);
      assert (size == unique.size());
      checkAns(A, size);
      System.out.println(size);
    }
  }
}
