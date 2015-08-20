package com.epi;

import java.util.*;

public class ClosestToMedian {

  // @include
  public static int[] findKClosestToMedian(int[] A, int k) {
    final double median = findMedian(A);

    // We will use this custom comparator to reorder the array
    // so that elements close to the median come to the front
    // of the array.
    Comparator<Integer> cmp = new Comparator<Integer>() {
        // Compare a and b based on how close they are to median.
        public int compare(Integer a, Integer b) {
            // Note the use of closure to access the variable median, which
            // is in the outer scope --- closure greatly simplifies the code.
            return Double.compare(Math.abs(a - median), Math.abs(b - median));
        }
    };

    OrderStatistic.findKth(A, k - 1, cmp);

    // Since findKth reordered A so that elements closest in absolute value
    // to median have been moved to the front of A, the first k entries 
    // are the result.
    return Arrays.copyOfRange(A, 0, k);
  }

  // Promote the return value to double to prevent precision error.
  public static double findMedian(int[] A) {
    int half = A.length / 2;
    OrderStatistic.findKthSmallest(A, half);

    if ((A.length % 2) != 0) { // A has an odd number elements.
      return A[half];
    } else { // A has an even number elements.
      int x = A[half];
      OrderStatistic.findKthSmallest(A, half - 1);
      return 0.5 * (x + A[half - 1]);
    }
  }
  // @exclude

  private static void checkAns(int[] answer, int[] res, int k) {
    Arrays.sort(answer);
    double median = ((answer.length & 1) != 0)
                        ? answer[(answer.length / 2)]
                        : 0.5 * (answer[((answer.length / 2) - 1)] +
                                 answer[(answer.length / 2)]);
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
    int[] d = new int[]{3, 2, 3, 5, 7, 3, 1};
    int[] dExpres = new int[]{2, 3, 3};
    int[] dRes = findKClosestToMedian(d, 3);
    checkAns(d, dRes, 3);
    for (int dResElement : dRes) {
      System.out.print(dResElement + " ");
    }
    System.out.println();
  }

  static int[] toIntArray(List<Integer> list){
    int[] result = new int[list.size()];
    for(int i = 0; i < result.length; i++) {
      result[i] = list.get(i);
    }
    return result;
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
      int[] res = findKClosestToMedian(toIntArray(a), k);
      assert(res.length == k);
      System.out.println("n = " + n + ", k = " + k);
      checkAns(toIntArray(a), res, k);
    }
  }
}
