package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MinimumWaitingTime {
  // @include
  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
    // Sort the service times in increasing order.
    Collections.sort(serviceTimes);

    int totalWaitingTime = 0;
    for (int i = 0; i < serviceTimes.size(); ++i) {
      int numRemainingQueries = serviceTimes.size() - (i + 1);
      totalWaitingTime += serviceTimes.get(i) * numRemainingQueries;
    }
    return totalWaitingTime;
  }
  // @exclude

  private static void smallTest() {
    assert(10 == minimumTotalWaitingTime(Arrays.asList(5, 1, 2, 3)));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(100) + 1;
    }
    List<Integer> waitingTime = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      waitingTime.add(r.nextInt(1000));
    }
    System.out.println(waitingTime);
    System.out.println(minimumTotalWaitingTime(waitingTime));
  }
}
