package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Bonus {
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
  private static class EmployeeData {
    public Integer productivity;
    public Integer index;

    public EmployeeData(Integer productivity, Integer index) {
      this.productivity = productivity;
      this.index = index;
    }
  }

  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public static List<Integer> calculateBonus(List<Integer> productivity) {
    PriorityQueue<EmployeeData> minHeap = new PriorityQueue<>(
        DEFAULT_INITIAL_CAPACITY, new Comparator<EmployeeData>() {
          @Override
          public int compare(EmployeeData o1, EmployeeData o2) {
            int result = Integer.compare(o1.productivity, o2.productivity);
            if (result == 0) {
              result = Integer.compare(o1.index, o2.index);
            }
            return result;
          }
        });
    for (int i = 0; i < productivity.size(); ++i) {
      minHeap.add(new EmployeeData(productivity.get(i), i));
    }

    // Initially assigns one ticket to everyone.
    List<Integer> tickets
        = new ArrayList<>(Collections.nCopies(productivity.size(), 1));
    // Fills tickets from lowest rating to highest rating.
    while (!minHeap.isEmpty()) {
      EmployeeData p = minHeap.peek();
      int nextDev = minHeap.peek().index;
      // Handles the left neighbor.
      if (nextDev > 0) {
        if (productivity.get(nextDev) > productivity.get(nextDev - 1)) {
          tickets.set(nextDev, tickets.get(nextDev - 1) + 1);
        }
      }
      // Handles the right neighbor.
      if (nextDev + 1 < tickets.size()) {
        if (productivity.get(nextDev) > productivity.get(nextDev + 1)) {
          tickets.set(nextDev, Math.max(tickets.get(nextDev),
                                        tickets.get(nextDev + 1) + 1));
        }
      }
      minHeap.remove(p);
    }
    return tickets;
  }
  // @exclude

  private static void smallTest() {
    List<Integer> a = Arrays.asList(1, 2, 2);
    List<Integer> goldenA = Arrays.asList(1, 2, 1);
    assert(calculateBonus(a).equals(goldenA));
    a = Arrays.asList(1, 2, 3, 2, 1);
    goldenA = Arrays.asList(1, 2, 3, 2, 1);
    assert(calculateBonus(a).equals(goldenA));
    a = Arrays.asList(300, 400, 500, 200);
    goldenA = Arrays.asList(1, 2, 3, 1);
    assert(calculateBonus(a).equals(goldenA));
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
      List<Integer> ratings = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        ratings.add(r.nextInt(10000) + 1);
      }
      List<Integer> T = calculateBonus(ratings);
      checkAns(ratings, T);
    }
  }
}
