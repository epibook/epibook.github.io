package com.epi;

public class CompareKthLargestInHeap {
  // @include
  private static class Status {
    public int larger;
    public int equal;
  }

  // -1 means smaller, 0 means equal, and 1 means larger.
  public static int compareKthLargestHeap(int[] maxHeap, int k, int x) {
    Status data = new Status();
    data.larger = 0;
    data.equal = 0;
    compareKthLargestHeapHelper(maxHeap, k, x, 0, data);
    return data.larger >= k ? 1 : (data.larger + data.equal >= k ? 0 : -1);
  }

  private static void compareKthLargestHeapHelper(int[] maxHeap, int k,
                                                  int x, int idx, Status data) {
    if (data.larger >= k || idx >= maxHeap.length || maxHeap[idx] < x) {
      return;
    } else if (maxHeap[idx] == x) {
      if (++data.equal >= k) {
        return;
      }
    } else { // maxHeap[idx] > x.
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
    int[] maxHeap = new int[]{5, 4, 5, 4, 4, 4, 3, 4};
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
