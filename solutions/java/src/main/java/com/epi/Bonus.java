package com.epi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

public class Bonus {
  private static class EmployeeData {
    public Integer productivity;
    public Integer index;

    public EmployeeData(Integer productivity, Integer index) {
      this.productivity = productivity;
      this.index = index;
    }
  }

  private static void checkAns(int[] ratings, int[] C) {
    for (int i = 0; i < ratings.length; ++i) {
      if (i > 0) {
        assert((ratings[i] > ratings[i - 1] && C[i] > C[i - 1]) ||
               (ratings[i] < ratings[i - 1] && C[i] < C[i - 1]) ||
               ratings[i] == ratings[i - 1]);
      }
      if (i + 1 < ratings.length) {
        assert((ratings[i] > ratings[i + 1] && C[i] > C[i + 1]) ||
               (ratings[i] < ratings[i + 1] && C[i] < C[i + 1]) ||
               ratings[i] == ratings[i + 1]);
      }
    }
  }

  // @include
  public static int[] calculateBonus(int[] productivity) {
    // Stores (index, productivity)-pair in minHeap where ordered by
    // productivity.
    TreeSet<EmployeeData> minHeap = new TreeSet<>(new Comparator<EmployeeData>() {
      @Override
      public int compare(EmployeeData o1, EmployeeData o2) {
        int result = o1.productivity.compareTo(o2.productivity);
        if (result == 0) {
          result = o1.index.compareTo(o2.index);
        }
        return result;
      }
    });
    for (int i = 0; i < productivity.length; ++i) {
      minHeap.add(new EmployeeData(productivity[i], i));
    }

    // Initially assigns one ticket to everyone.
    int[] tickets = new int[productivity.length];
    Arrays.fill(tickets, 1);
    // Fills tickets from lowest rating one to toppmost rating.
    while (!minHeap.isEmpty()) {
      int nextDev = minHeap.first().index;
      EmployeeData p = minHeap.first();
      // Handles the left neighbor.
      if (nextDev > 0) {
        if (productivity[nextDev] > productivity[nextDev - 1]) {
          tickets[nextDev] = tickets[nextDev - 1] + 1;
        }
      }
      // Handles the right neighbor.
      if (nextDev + 1 < tickets.length) {
        if (productivity[nextDev] > productivity[nextDev + 1]) {
          tickets[nextDev] = Math.max(tickets[nextDev], tickets[nextDev + 1] + 1);
        }
      }
      minHeap.remove(p);
    }
    return tickets;
  }
  // @exclude

  private static void smallTest() {
    int[] a = new int[] {1, 2, 2};
    int[] goldenA = new int[] {1, 2, 1};
    assert(Arrays.equals(calculateBonus(a), goldenA));
    a = new int[] {1, 2, 3, 2, 1};
    goldenA = new int[] {1, 2, 3, 2, 1};
    assert(Arrays.equals(calculateBonus(a), goldenA));
    a = new int[] {300, 400, 500, 200};
    goldenA = new int[] {1, 2, 3, 1};
    assert(Arrays.equals(calculateBonus(a), goldenA));
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
      int[] ratings = new int[n];
      for (int i = 0; i < n; ++i) {
        ratings[i] = r.nextInt(10000) + 1;
      }
      int[] T = calculateBonus(ratings);
      checkAns(ratings, T);
    }
  }
}
