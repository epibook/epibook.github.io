package com.epi;

import java.io.*;
import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KthLargestElementLargeN {
  private static <T> void nthElement(List<T> a, int n, Comparator<T> c) {
    Collections.sort(a, c);
  }

  private static <T extends Comparable<T>> void nthElement(List<T> a, int n) {
    nthElement(a, n, new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {
        return o1.compareTo(o2);
      }
    });
  }

  private static class Greater<T extends Comparable<T>> implements
      Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
      return o2.compareTo(o1);
    }
  }

  // @include
  public static int findKthLargestUnknownLength(InputStream sin, int k) {
    List<Integer> M = new ArrayList<>();
    Scanner s = new Scanner(sin);
    while (s.hasNextInt()) {
      int x = s.nextInt();
      M.add(x);
      if (M.size() == (k * 2) - 1) {
        // Keeps the k largest elements and discard the small ones.
        nthElement(M, k - 1, new Greater<Integer>());
        M = new ArrayList<>(M.subList(0, k));
      }
    }
    nthElement(M, k - 1, new Greater<Integer>());
    return M.get(k - 1); // return the k-th largest one.
  }
  // @exclude

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
      nthElement(a, a.size() - k);
      System.out.println(result);
      System.out.println(a.get(a.size() - k));
      assert a.get(a.size() - k).equals(result);
    }
  }
}
