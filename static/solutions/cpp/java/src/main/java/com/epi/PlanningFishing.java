package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlanningFishing {
  // @include
  public static int maximizeFishing(List<List<Integer>> A) {
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.get(i).size(); ++j) {
        A.get(i).set(j, A.get(i).get(j)
                            + Math.max(i < 1 ? 0 : A.get(i - 1).get(j),
                                       j < 1 ? 0 : A.get(i).get(j - 1)));
      }
    }
    return A.get(A.size() - 1).get(A.get(0).size() - 1);
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, m;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(100) + 1;
      m = r.nextInt(100) + 1;
    }
    List<List<Integer>> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(new ArrayList(m));
      for (int j = 0; j < m; ++j) {
        A.get(i).add(r.nextInt(1000));
      }
    }
    System.out.println(A);
    System.out.println(maximizeFishing(A));
  }
}
