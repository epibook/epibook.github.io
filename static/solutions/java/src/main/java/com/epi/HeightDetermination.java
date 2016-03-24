package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeightDetermination {
  // @include
  public static int getHeight(int cases, int drops) {
    List<List<Integer>> F = new ArrayList<>(cases + 1);
    for (int i = 0; i < cases + 1; ++i) {
      F.add(new ArrayList(Collections.nCopies(drops + 1, -1)));
    }
    return getHeightHelper(cases, drops, F);
  }

  private static int getHeightHelper(int cases, int drops,
                                     List<List<Integer>> F) {
    if (cases == 0 || drops == 0) {
      return 0;
    } else if (cases == 1) {
      return drops;
    } else {
      if (F.get(cases).get(drops) == -1) {
        F.get(cases).set(drops, getHeightHelper(cases, drops - 1, F)
                                    + getHeightHelper(cases - 1, drops - 1, F)
                                    + 1);
      }
      return F.get(cases).get(drops);
    }
  }
  // @exclude

  private static int checkAnswer(int cases, int drops) {
    if (cases <= 0 || drops <= 0) {
      return 0;
    }
    List<Integer> row = new ArrayList<>(Collections.nCopies(cases, 0));
    for (int d = 0; d < drops; ++d) {
      for (int c = cases - 1; c >= 0; --c) {
        row.set(c, row.get(c) + (c == 0 ? 0 : row.get(c - 1)) + 1);
      }
    }
    return row.get(cases - 1);
  }

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

    assert(checkAnswer(1, 10) == 10);
    assert(checkAnswer(2, 1) == 1);
    assert(checkAnswer(2, 2) == 3);
    assert(checkAnswer(2, 3) == 6);
    assert(checkAnswer(2, 4) == 10);
    assert(checkAnswer(2, 5) == 15);
    assert(checkAnswer(3, 2) == 3);
    assert(checkAnswer(100, 2) == 3);
    assert(checkAnswer(3, 5) == 25);
    assert(checkAnswer(8, 11) == 1980);
    assert(checkAnswer(3, 0) == 0);
    assert(checkAnswer(3, 1) == 1);
    assert(checkAnswer(3, 3) == 7);
    assert(checkAnswer(0, 10) == 0);
    assert(checkAnswer(0, 0) == 0);
  }
}
