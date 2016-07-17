package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BonusImproved {
  private static void checkAns(List<Integer> ratings, List<Integer> C) {
    for (int i = 0; i < ratings.size(); ++i) {
      if (i > 0) {
        assert(
            (ratings.get(i) > ratings.get(i - 1) && C.get(i) > C.get(i - 1))
            || (ratings.get(i) < ratings.get(i - 1) && C.get(i) < C.get(i - 1))
            || ratings.get(i).equals(ratings.get(i - 1)));
      }
      if (i + 1 < ratings.size()) {
        assert(
            (ratings.get(i) > ratings.get(i + 1) && C.get(i) > C.get(i + 1))
            || (ratings.get(i) < ratings.get(i + 1) && C.get(i) < C.get(i + 1))
            || ratings.get(i).equals(ratings.get(i + 1)));
      }
    }
  }

  // @include
  public static List<Integer> calculateBonus(List<Integer> productivity) {
    // Initially assigns one ticket to everyone.
    List<Integer> tickets
        = new ArrayList<>(Collections.nCopies(productivity.size(), 1));
    // From left to right.
    for (int i = 1; i < productivity.size(); ++i) {
      if (productivity.get(i) > productivity.get(i - 1)) {
        tickets.set(i, tickets.get(i - 1) + 1);
      }
    }
    // From right to left.
    for (int i = productivity.size() - 2; i >= 0; --i) {
      if (productivity.get(i) > productivity.get(i + 1)) {
        tickets.set(i, Math.max(tickets.get(i), tickets.get(i + 1) + 1));
      }
    }
    return tickets;
  }
  // @exclude

  private static void smallTest() {
    List<Integer> A = Arrays.asList(1, 2, 2);
    List<Integer> goldenA = Arrays.asList(1, 2, 1);
    assert(calculateBonus(A).equals(goldenA));
    A = Arrays.asList(1, 2, 3, 2, 1);
    goldenA = Arrays.asList(1, 2, 3, 2, 1);
    assert(calculateBonus(A).equals(goldenA));
    A = Arrays.asList(300, 400, 500, 200);
    goldenA = Arrays.asList(1, 2, 3, 1);
    assert(calculateBonus(A).equals(goldenA));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> ratings = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        ratings.add(r.nextInt(10000) + 1);
      }
      List<Integer> T = calculateBonus(ratings);
      checkAns(ratings, T);
    }
  }
}
