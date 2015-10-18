package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeightDetermination {
  // @include
  public static int getHeight(int c, int d) {
    List<List<Integer>> F = new ArrayList<>(c + 1);
    for (int i = 0; i < c + 1; ++i) {
      F.add(new ArrayList(Collections.nCopies(d + 1, -1)));
    }
    return getHeightHelper(c, d, F);
  }

  private static int getHeightHelper(int c, int d, List<List<Integer>> F) {
    if (c == 0 || d == 0) {
      return 0;
    } else if (c == 1) {
      return d;
    } else {
      if (F.get(c).get(d) == -1) {
        F.get(c).set(d, getHeightHelper(c, d - 1, F)
                            + getHeightHelper(c - 1, d - 1, F) + 1);
      }
      return F.get(c).get(d);
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
