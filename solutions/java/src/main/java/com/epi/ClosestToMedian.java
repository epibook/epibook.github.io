package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ClosestToMedian {
  private static <T> void nthElement(List<T> A, int n, Comparator<T> c) {
    Collections.sort(A, c);
  }

  private static <T extends Comparable<T>> void nthElement(List<T> A, int n) {
    nthElement(A, n, new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {
        return o1.compareTo(o2);
      }
    });
  }

  // @include
  // Promote to double to prevent precision error.
  public static double findMedian(List<Integer> a) {
    int half = a.size() >> 1;
    nthElement(a, half);
    if ((a.size() & 1) != 0) { // a has odd number elements.
      return a.get(half);
    } else { // a has even number elements.
      int x = a.get(half);
      nthElement(a, half - 1);
      return 0.5 * (x + a.get(half - 1));
    }
  }

  public static List<Integer> findKClosestToMedian(List<Integer> a, int k) {
    // Find the element i where |a[i] - median| is k-th smallest.
    final double m = findMedian(a);
    nthElement(a, k - 1, new Comparator<Integer>() {
      @Override
      public int compare(Integer a, Integer b) {
        return Double.valueOf(Math.abs(a - m)).compareTo(Math.abs(b - m));
      }
    });
    return new ArrayList<Integer>(a.subList(0, k));
  }

  // @exclude

  private static void checkAns(List<Integer> answer, List<Integer> res, int k) {
    Collections.sort(answer);
    double median = ((answer.size() & 1) != 0) ? answer.get(answer.size() >> 1)
        : 0.5 * (answer.get((answer.size() >> 1) - 1) + answer.get(answer
            .size() >> 1));
    ArrayList<Double> temp = new ArrayList<Double>();
    for (int a : answer) {
      temp.add(Math.abs(median - a));
    }
    Collections.sort(temp);
    for (int r : res) {
      assert (Math.abs(r - median) <= temp.get(k - 1));
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
      ArrayList<Integer> a = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {
        a.add(r.nextInt(n << 1));
      }
      // System.out.println(a);
      List<Integer> res = findKClosestToMedian(a, k);
      assert (res.size() == k);
      System.out.println("n = " + n + ", k = " + k);
      checkAns(a, res, k);
    }
  }
}
