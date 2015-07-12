package com.epi;

import java.util.*;

public class ClosestToMedian {
  private static void nthElement(List<Integer> A, int n, Comparator<Integer> c) {
    Collections.sort(A, c);
  }

  private static void nthElement(List<Integer> A, int n) {
    nthElement(A, n, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
      }
    });
  }

  // @include
  public static List<Integer> findKClosestToMedian(List<Integer> A, int k) {
    // Find the element i where |A[i] - median| is k-th smallest.
    final double m = findMedian(A);
    nthElement(A, k - 1, new Comparator<Integer>() {
      @Override
      public int compare(Integer A, Integer b) {
        return Double.valueOf(Math.abs(A - m)).compareTo(Math.abs(b - m));
      }
    });
    return new ArrayList<>(A.subList(0, k));
  }

  // Promote the return value to double to prevent precision error.
  public static double findMedian(List<Integer> A) {
    int half = A.size() / 2;
    nthElement(A, half);
    if ((A.size() % 2) != 0) { // A has odd number elements.
      return A.get(half);
    } else { // A has even number elements.
      int x = A.get(half);
      nthElement(A, half - 1);
      return 0.5 * (x + A.get(half - 1));
    }
  }
  // @exclude

  private static void checkAns(List<Integer> answer, List<Integer> res, int k) {
    Collections.sort(answer);
    double median = ((answer.size() & 1) != 0)
                        ? answer.get(answer.size() / 2)
                        : 0.5 * (answer.get((answer.size() / 2) - 1) +
                                 answer.get(answer.size() / 2));
    List<Double> temp = new ArrayList<>();
    for (int a : answer) {
      temp.add(Math.abs(median - a));
    }
    Collections.sort(temp);
    for (int r : res) {
      assert(Math.abs(r - median) <= temp.get(k - 1));
    }
  }

  private static void simpleTest() {
    List<Integer> d = Arrays.asList(3, 2, 3, 5, 7, 3, 1);
    List<Integer> dExpres = Arrays.asList(2, 3, 3);
    List<Integer> dRes = findKClosestToMedian(d, 3);
    checkAns(d, dRes, 3);
    for (int dResElement : dRes) {
      System.out.print(dResElement + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, k;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        k = r.nextInt(n) + 1;
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(100000) + 1;
        k = r.nextInt(n) + 1;
      }
      List<Integer> a = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        a.add(r.nextInt(n * 2));
      }
      // System.out.println(a);
      List<Integer> res = findKClosestToMedian(a, k);
      assert(res.size() == k);
      System.out.println("n = " + n + ", k = " + k);
      checkAns(a, res, k);
    }
  }
}
