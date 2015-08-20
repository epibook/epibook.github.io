package com.epi;

import java.util.Arrays;

public class HeightDetermination {
  // @include
  static int getHeight(int c, int d) {
    int[][] F = new int[c + 1][d + 1];
    for (int[] element : F) {
      Arrays.fill(element, -1);
    }

    return getHeightHelper(F, c, d);
  }

  static int getHeightHelper(int[][] F, int c, int d) {
    if (c == 0 || d == 0) {
      return 0;
    } else if (c == 1) {
      return d;
    } else {
      if (F[c][d] == -1) {
        F[c][d] =
            getHeightHelper(F, c, d - 1) + getHeightHelper(F, c - 1, d - 1) + 1;
      }
      return F[c][d];
    }
  }
  // @exclude

  public static void main(String[] args) {
    assert(getHeight(1, 10) == 10);
    assert(getHeight(2, 1) == 1);
    assert(getHeight(2, 2) == 3);
    assert(getHeight(2, 3) == 6);
    assert(getHeight(2, 4) == 10);
    assert(getHeight(2, 5) == 15);
    assert(getHeight(3, 2) == 3);
    assert(getHeight(100, 2) == 3);
    assert(getHeight(3, 5) == 25);
    assert(getHeight(8, 11) == 1980);
    assert(getHeight(3, 0) == 0);
    assert(getHeight(3, 1) == 1);
    assert(getHeight(3, 3) == 7);
    assert(getHeight(0, 10) == 0);
    assert(getHeight(0, 0) == 0);
  }
}
