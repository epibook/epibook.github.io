package com.epi;

import java.util.Random;

public class JumpGame {
  // @include
  public static boolean canReach(int[] A) {
    int furthestReach = 0;
    for (int i = 0; i <= furthestReach && furthestReach < A.length - 1; ++i) {
      furthestReach = Math.max(furthestReach, i + A[i]);
    }
    return furthestReach >= A.length - 1;
  }
  // @exclude

  private static void smallTest() {
    int[] A = {2, 3, 1, 1, 4};
    assert (canReach(A));
    int[] B = {3, 2, 1, 0, 4};
    assert (!canReach(B));
    int[] C = {3, 2, 1, -10, 4};
    assert (!canReach(C));
    int[] D = {2, 3, -1, -1, 4};
    assert (canReach(D));
    int[] E = {2, 2, -1, -1, 100};
    assert (!canReach(E));
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(1000) + 1;
    }
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = r.nextInt(10) + 1;
    }
    System.out.println(canReach(A));
  }
}
