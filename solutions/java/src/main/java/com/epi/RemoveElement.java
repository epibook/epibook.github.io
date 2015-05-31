package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveElement {
  // @include
  public static int removeElement(int k, int[] A) {
    int writeIdx = 0;
    for (int i = 0; i < A.length; ++i) {
      if (A[i] != k) {
        A[writeIdx++] = A[i];
      }
    }
    return writeIdx;
  }
  // @exclude

  private static void checkAns(int[] A, int n, int k) {
    for (int i = 0; i < n; ++i) {
      assert(A[i] != k);
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10001);
    }
    for (int times = 0; times < 1000; ++times) {
      int[] A = new int[n];
      for (int i = 0; i < n; i++) {
        A[i] = r.nextInt(2001) - 1000;
      }
      List<Integer> copyA = new ArrayList<>();
      for (int a : A) {
        copyA.add(a);
      }
      int target = r.nextInt(2001) - 1000;
      int size = removeElement(target, A);
      System.out.println("size = " + size + " k = " + target);
      checkAns(A, size, target);
      while (copyA.remove((Integer)target))
        ;
      System.out.println(copyA.size());
      assert(size == copyA.size());
    }
  }
}
