package com.epi;

import java.util.*;

public class RemoveDuplicatesFromSortedArray {
  // @include
  public static int removeDuplicates(List<Integer> a) {
    if (a.isEmpty()) {
      return 0;
    }

    int index = 0;
    for (int i = 1; i < a.size(); ++i) {
      if (!a.get(index).equals(a.get(i))) {
        a.set(++index, a.get(i));
      }
    }
    return index + 1;
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
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(2001) - 1000);
      }
      Collections.sort(A);
      Set<Integer> unique = new HashSet<>(A);
      int size = removeDuplicates(A);
      assert (size == unique.size());
      checkAns(A, size);
      System.out.println(size);
    }
  }
}
