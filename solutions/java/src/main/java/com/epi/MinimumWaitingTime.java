package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MinimumWaitingTime {
  // @include
  public static int minimumWaitingTime(List<Integer> serviceTime) {
    // Sort the query time in increasing order.
    Collections.sort(serviceTime);

    int waiting = 0;
    for (int i = 0; i < serviceTime.size(); ++i) {
      waiting += serviceTime.get(i) * (serviceTime.size() - (i + 1));
    }
    return waiting;
  }
  // @exclude

  public static void main(String[] args) {
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
    System.out.println(minimumWaitingTime(waitingTime));
  }
}
