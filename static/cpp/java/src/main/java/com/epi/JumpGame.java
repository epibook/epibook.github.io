package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JumpGame {
  // @include
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int furthestReachSoFar = 0, lastIndex = maxAdvanceSteps.size() - 1;
    for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex;
         ++i) {
      furthestReachSoFar
          = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
    }
    return furthestReachSoFar >= lastIndex;
  }
  // @exclude

  private static void smallTest() {
    assert(canReachEnd(Arrays.asList(2, 3, 1, 1, 4)));
    assert(!canReachEnd(Arrays.asList(3, 2, 1, 0, 4)));
    assert(!canReachEnd(Arrays.asList(3, 2, 1, -10, 4)));
    assert(canReachEnd(Arrays.asList(2, 3, -1, -1, 4)));
    assert(!canReachEnd(Arrays.asList(2, 2, -1, -1, 100)));
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
    List<Integer> maxAdvanceSteps = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      maxAdvanceSteps.add(r.nextInt(10) + 1);
    }
    System.out.println(canReachEnd(maxAdvanceSteps));
  }
}
