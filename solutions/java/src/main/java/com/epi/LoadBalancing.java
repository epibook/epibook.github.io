package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadBalancing {
  // @include
  public static List<Integer> decideLoadBalancing(List<Integer> userFileSize,
                                                  int serverNum) {
    // Uses binary search to find the assignment with minimized maximum load.
    int l = 0;
    int r = 0;
    for (int i : userFileSize) {
      r += i;
    }
    List<Integer> feasibleAssignment = new ArrayList<>();
    while (l <= r) {
      int m = l + ((r - l) / 2);
      List<Integer> assignRes = new ArrayList<>(serverNum);
      for (int i = 0; i < serverNum; i++) {
        assignRes.add(0);
      }
      boolean isFeasible
          = greedyAssignment(userFileSize, serverNum, m, assignRes);
      if (isFeasible) {
        feasibleAssignment = assignRes;
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return feasibleAssignment;
  }

  private static boolean greedyAssignment(List<Integer> userFileSize,
                                          int serverNum, int limit,
                                          List<Integer> assignRes) {
    int serverIdx = 0;
    for (int file : userFileSize) {
      while (serverIdx < serverNum && file + assignRes.get(serverIdx) > limit) {
        ++serverIdx;
      }

      if (serverIdx >= serverNum) {
        return false;
      } else {
        assignRes.set(serverIdx, assignRes.get(serverIdx) + file);
      }
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, m;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(50000) + 1;
      m = r.nextInt(n) + 1;
    }
    System.out.println(n + " " + m);

    // stores user i's data size.
    List<Integer> users = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      users.add(r.nextInt(1000) + 1);
    }
    for (int u : users) {
      System.out.print(u + " ");
    }
    System.out.println();
    List<Integer> res = decideLoadBalancing(users, m);
    for (int file : res) {
      System.out.print(file + " ");
    }
    System.out.println();
  }
}
