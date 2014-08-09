package com.epi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CompareKthLargestInHeap {
  // @include
  private static class Data {
    public int larger;
    public int equal;
  }

  // -1 means smaller, 0 means equal, and 1 means larger.
  public static int compareKthLargestHeap(List<Integer> maxHeap, int k, int x) {
    Data data = new Data();
    data.larger = 0;
    data.equal = 0;
    compareKthLargestHeapHelper(maxHeap, k, x, 0, data);
    return data.larger >= k ? 1 : (data.larger + data.equal >= k ? 0 : -1);
  }

  private static void compareKthLargestHeapHelper(List<Integer> maxHeap, int k,
                                                  int x, int idx, Data data) {
    if (data.larger >= k || idx >= maxHeap.size() || maxHeap.get(idx) < x) {
      return;
    } else if (maxHeap.get(idx) == x) {
      if (++data.equal >= k) {
        return;
      }
    } else { // max_heap[idx] > x.
      ++data.larger;
    }
    compareKthLargestHeapHelper(maxHeap, k, x, (idx * 2) + 1, data);
    compareKthLargestHeapHelper(maxHeap, k, x, (idx * 2) + 2, data);
  }
  // @exclude

  public static void main(String[] args) {
    // 5
    // 4 5
    // 4 4 4 3
    // 4
    List<Integer> maxHeap = new ArrayList<>();
    maxHeap.add(5);
    maxHeap.add(4);
    maxHeap.add(5);
    maxHeap.add(4);
    maxHeap.add(4);
    maxHeap.add(4);
    maxHeap.add(3);
    maxHeap.add(4);
    int k, x;
    if (args.length == 2) {
      k = Integer.parseInt(args[0]);
      x = Integer.parseInt(args[1]);
      int res = compareKthLargestHeap(maxHeap, k, x);
      System.out.println((res == -1 ? "smaller" : (res == 0 ? "equal"
          : "larger")));
    } else {
      assert (-1 == compareKthLargestHeap(maxHeap, 1, 6)); // expect smaller
      assert (0 == compareKthLargestHeap(maxHeap, 1, 5)); // expect equal
      assert (0 == compareKthLargestHeap(maxHeap, 6, 4)); // expect equal
      assert (0 == compareKthLargestHeap(maxHeap, 3, 4)); // expect equal
      assert (-1 == compareKthLargestHeap(maxHeap, 8, 4)); // expect smaller
      assert (1 == compareKthLargestHeap(maxHeap, 2, 4)); // expect larger
      assert (1 == compareKthLargestHeap(maxHeap, 2, 3)); // expect larger
    }
  }
}
