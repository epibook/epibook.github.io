package com.epi;

import java.util.Random;

public class JumpGame {
  // @include
  public static boolean canReachEnd(int[] maxAdvanceSteps) {
    int furthestReachSoFar = 0;
    for (int i = 0; i <= furthestReachSoFar &&
                    furthestReachSoFar < maxAdvanceSteps.length - 1;
         ++i) {
      furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps[i]);
    }
    return furthestReachSoFar >= maxAdvanceSteps.length - 1;
  }
  // @exclude

  private static void smallTest() {
    int[] A = {2, 3, 1, 1, 4};
    assert(canReachEnd(A));
    int[] B = {3, 2, 1, 0, 4};
    assert(!canReachEnd(B));
    int[] C = {3, 2, 1, -10, 4};
    assert(!canReachEnd(C));
    int[] D = {2, 3, -1, -1, 4};
    assert(canReachEnd(D));
    int[] E = {2, 2, -1, -1, 100};
    assert(!canReachEnd(E));
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
    int[] maxAdvanceSteps = new int[n];
    for (int i = 0; i < n; i++) {
      maxAdvanceSteps[i] = r.nextInt(10) + 1;
    }
    System.out.println(canReachEnd(maxAdvanceSteps));
  }
}
