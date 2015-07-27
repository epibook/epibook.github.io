package com.epi;

import java.io.*;
import java.util.*;

public class KthLargestElementLargeN {
  // @include
  public static int findKthLargestUnknownLength(InputStream sin, int k) {
    int[] candidates = new int[(k * 2)+1];
    Scanner s = new Scanner(sin);
    int idx = 0;
    while (s.hasNextInt()) {
      int x = s.nextInt();
      candidates[idx++] = x;
      if (idx == (k * 2 + 1) ) {
        // Reorders elements about median with larger elements appearing before
        // the median.
        OrderStatistic.findKthLargest(candidates, k);
        // Reset idx to keep just the k largest elements seen so far.
        idx = k;
      }
    }
    // Finds the k-th largest element in candidates.
    OrderStatistic.findKthLargest(candidates, k);
    return candidates[k - 1];
  }
  // @exclude

  private static void SimpleTest() {
      int a[] = new int[]{5,6,2,1,3,0,4};
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(baos);
      try {
        for (Integer anA : a) {
          osw.write(anA + " ");
        }
        osw.close();
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

      for (int i = 0; i < a.length; i++) {
        System.out.println("i = " + i);
        int result = findKthLargestUnknownLength(bais, i+1);
        System.out.println(result);
      }
  }

  public static void main(String[] args) {
    SimpleTest();
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
        a.add(r.nextInt(10000000));
      }
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(baos);
      try {
        for (Integer anA : a) {
          osw.write(anA + " ");
        }
        osw.close();
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

      int result = findKthLargestUnknownLength(bais, k);

      int[] array = new int[a.size()];
      int i = 0;
      for (int x : a) {
        array[i++] = x;
      }

      OrderStatistic.findKthLargest(array, k);
      System.out.println(result);
      System.out.println(array[k-1]);
      assert (array[k-1] == result);
    }
  }
}
