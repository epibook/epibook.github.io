package com.epi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.NoSuchElementException;

public class OrderStatistic {
  // @include
  private static class Compare {
    private static class GreaterThan implements Comparator<Integer> {
      public int compare(Integer a, Integer b) {
        return (a > b) ? -1 : (a == b) ? 0 : 1;
      }
    }
    public static final GreaterThan GREATER_THAN = new GreaterThan();
    // @exclude

    private static class LessThan implements Comparator<Integer> {
      public int compare(Integer a, Integer b) {
        return (a < b) ? -1 : (a == b) ? 0 : 1;
      }
    }
    public static final LessThan LESS_THAN = new LessThan();
  }

  // @include
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then 
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  public static int findKthLargest(int[] A, int k ) {
    return findKth(A, k, Compare.GREATER_THAN);
  }

  // @exclude

  // The numbering starts from one, i.e., if A = [3,1,-1,2] then 
  // findKthSmallest(A, 1) returns -1, findKthSmallest(A, 2) returns 1,
  // findKthSmallest(A, 3) returns 2, and findKthSmallest(A, 4) returns 3.
  public static int findKthSmallest(int[] A, int k ) {
    return findKth(A, k, Compare.LESS_THAN);
  }

  // @include
  public static int findKth(int[] A, int k, Comparator<Integer> cmp) {
    int left = 0, right = A.length - 1;
    Random r = new Random();
    while (left <= right) {
      // Generates a random integer in [left, right].
      int pivotIdx = r.nextInt(right - left + 1) + left;
      int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, A, cmp);
      if (newPivotIdx == k - 1) {
        return A[newPivotIdx];
      } else if (newPivotIdx > k - 1) {
        right = newPivotIdx - 1;
      } else { // newPivotIdx < k - 1.
        left = newPivotIdx + 1;
      }
    }
    // @exclude
    throw new NoSuchElementException("no k-th node in array A");
    // @include
  }

  // Partition A[left : right] around pivotIdx, returns the new index of the
  // pivot, newPivotIdx, after partition. After partitioning,
  // A[left : newPivotIdx - 1] contains elements that are greater than the
  // pivot, and A[newPivotIdx + 1 : right] contains elements that are less
  // than the pivot. 
  //
  // Note that greater than is defined by the comparator object.
  private static int partitionAroundPivot(int left, int right, int pivotIdx,
                                          int[] A, Comparator<Integer> cmp) {
    int pivotValue = A[pivotIdx];
    int newPivotIdx = left;

    swap(A, pivotIdx, right);
    for (int i = left; i < right; ++i) {
      if (cmp.compare(A[i], pivotValue) < 0) {
        swap(A, i, newPivotIdx++);
      }
    }
    swap(A, right, newPivotIdx);
    return newPivotIdx;
  }

  private static void swap(int[] A, int a, int b) {
    int temp = A[a];
    A[a] = A[b];
    A[b] = temp;
  }
  // @exclude

  private static void SimpleTestKthSmallest() {
    int[] A = new int[] {3, 1, 2, 0, 4, 6, 5};
    assert(0 == findKthSmallest(A, 1));
    assert(1 == findKthSmallest(A, 2));
    assert(2 == findKthSmallest(A, 3));
    assert(3 == findKthSmallest(A, 4));
    assert(4 == findKthSmallest(A, 5));
    assert(5 == findKthSmallest(A, 6));
    assert(6 == findKthSmallest(A, 7));
    A[2] = 6;
    assert(6 == findKthSmallest(A, 6));
    assert(6 == findKthSmallest(A, 7));
    assert(5 == findKthSmallest(A,5));

    A = new int[]{0,-7,3,4,4,12,6,10,0};
    // -7 0 0 3 4 4 6 10 12
    System.out.println("first element " + findKthSmallest(A, 1));
    assert(-7 == findKthSmallest(A, 1));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(0 == findKthSmallest(A, 2));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(0 == findKthSmallest(A, 3));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(3 == findKthSmallest(A, 4));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(4 == findKthSmallest(A, 5));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(4 == findKthSmallest(A, 6));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(4 == findKthSmallest(A, 5));
    for(int i = 0; i < A.length; i++) {
        System.out.println("i,A[i] = " + i + " " + A[i]);
        if (i<4) {
            assert(A[i] < 4);
        } else if (i > 5) {
            assert(A[i] > 4);
        }
    }
  }


  private static void SimpleTestKthLargest() {
    int[] A = new int[] {3, 1, 2, 0, 4, 6, 5};
    assert(6 == findKthLargest(A, 1));
    assert(5 == findKthLargest(A, 2));
    assert(4 == findKthLargest(A, 3));
    assert(3 == findKthLargest(A, 4));
    assert(2 == findKthLargest(A, 5));
    assert(1 == findKthLargest(A, 6));
    assert(0 == findKthLargest(A, 7));
    A[2] = 6;
    assert(6 == findKthLargest(A, 1));
    assert(6 == findKthLargest(A, 2));
    assert(5 == findKthLargest(A, 3));

    A = new int[]{0,-7,3,4,4,12,6,10,0};
    // 12 10 6 4 4 3 0 0 -7
    System.out.println("first element " + findKthLargest(A, 1));
    assert(12 == findKthLargest(A, 1));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(10 == findKthLargest(A, 2));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(6 == findKthLargest(A, 3));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(4 == findKthLargest(A, 4));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(4 == findKthLargest(A, 5));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(3 == findKthLargest(A, 6));
    A = new int[]{0,-7,3,4,4,12,6,10,0};
    assert(4 == findKthLargest(A, 5));
    for(int i = 0; i < A.length; i++) {
        System.out.println("i,A[i] = " + i + " " + A[i]);
        if (i<3) {
            assert(A[i] > 4);
        } else if (i > 4) {
            assert(A[i] < 4);
        }
    }
  }

  private static void SimpleTest() {
    int[] A = new int[]{123};
    assert(123 == findKthLargest(A, 1));
    System.out.println("Length 1 array: " + findKthLargest(A, 1));
  }

  public static void main(String[] args) {
    SimpleTest();
    SimpleTestKthLargest();
    SimpleTestKthSmallest();
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
        k = r.nextInt(n - 1) + 1;
      }
      int[] A = new int[n];
      for (int i = 0; i < n; ++i) {
        A[i] = r.nextInt(10000000);
      }
      int result = findKthLargest(A, k);
      Arrays.sort(A);
      assert(result == A[A.length - k]);
    }
  }
}
