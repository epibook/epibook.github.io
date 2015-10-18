package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class LargeArray {
  // @include
  private static final int N = 2000000000;
  private static final char[] A_LARGE = new char[N];

  // @exclude

  public static int comp(char[] A, int a, int b) {
    if (A[a] == A[b]) {
      return 0;
    } else if (A[a] < A[b]) {
      return -1;
    } else {
      return 1;
    }
  }

  private static long buildUnsignedInt(char[] A, int a) {
    return A[a] << 24 | A[a + 1] << 16 | A[a + 2] << 8 | A[a + 3];
  }

  public static int compUnsignedInt(char[] A, int a, int b) {
    long aLong = buildUnsignedInt(A, a);
    long bLong = buildUnsignedInt(A, b);
    if (aLong == bLong) {
      return 0;
    } else if (aLong < bLong) {
      return -1;
    } else {
      return 1;
    }
  }

  public static void init(final char[] A) {
    Random r = new Random();
    int i;
    Integer[] P = new Integer[127];
    for (i = 0; i < 127; ++i) {
      P[i] = r.nextInt() % N;
    }
    Collections.sort(Arrays.asList(P), new Comparator<Integer>() {
      // clang-format off
      @Override
      public int compare(Integer o1, Integer o2) { return comp(A, o1, o2); }
      // clang-format on
    });
    int pre = 0;
    char val;
    for (val = 0; val < 127; ++val) {
      while (pre < P[val]) {
        A[pre++] = val;
      }
    }
    while (pre < N) {
      A[pre++] = val;
    }
    // qsort(A, N, sizeof(char), comp); // too slow to sort
  }

  public static int binarySearch(char key, char[] A, int offset, int L, int U) {
    while (L <= U) {
      int M;
      // Change the following logic to M = (L + U) / 2 will fail this program.
      if ((L > 0 && U > 0 || L < 0 && U < 0)) {
        M = L + (U - L) / 2;
      } else {
        M = (L + U) / 2;
      }
      if (A[offset + M] < key) {
        L = M + 1;
      } else if (A[offset + M] > key) {
        U = M - 1;
      } else {
        return M;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    char key = 123;
    init(A_LARGE);
    // @include
    int offset = 1500000000;
    int L = -1499000000;
    int U = 1499000000;
    // On a 32-bit machine (U - L) = -1296967296 because the actual value,
    // 2998000000 is larger than 2^31 - 1. Consequently, the bsearch function
    // called below sets m to -2147483648 instead of 0, which leads to an
    // out-of-bounds access, since the most negative index that can be applied
    // to B is -1500000000.
    int result = binarySearch(key, A_LARGE, offset, L, U);
    // @exclude
    if (result != -1) {
      System.out.println("B[" + result + "] = " + A_LARGE[offset + result]);
      assert(A_LARGE[offset + result] == key);
    }
  }
}
