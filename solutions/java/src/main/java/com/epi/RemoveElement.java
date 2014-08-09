package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveElement {
  // @include
  public static int removeElement(int k, List<Integer> A) {
    int writeIdx = 0;
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) != k) {
        A.set(writeIdx++, A.get(i));
      }
    }
    return writeIdx;
  }
  // @exclude

  private static void checkAns(List<Integer> A, int n, int k) {
    for (int i = 0; i < n; ++i) {
      assert (A.get(i) != k);
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
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(2001) - 1000);
      }
      List<Integer> copyA = new ArrayList<>(A);
      int target = r.nextInt(2001) - 1000;
      int size = removeElement(target, A);
      System.out.println("size = " + size + " k = " + target);
      checkAns(A, size, target);
      while (copyA.remove((Integer) target)) {

      }
      System.out.println(copyA.size());
      assert (size == copyA.size());
    }
  }
}
