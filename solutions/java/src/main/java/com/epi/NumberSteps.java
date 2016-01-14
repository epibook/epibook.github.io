package com.epi;

import java.util.Random;

public class NumberSteps {
  // @include
  public static int numberOfWaysToTop(int top, int maximumStep) {
    return computeNumberOfWaysToH(top, maximumStep, new int[top + 1]);
  }

  private static int computeNumberOfWaysToH(int n, int maximumStep,
                                            int[] numberOfWaysToH) {
    if (n <= 1) {
      return 1;
    }
    if (numberOfWaysToH[n] == 0) {
      for (int i = 1; i <= maximumStep && n - i >= 0; ++i) {
        numberOfWaysToH[n]
            += computeNumberOfWaysToH(n - i, maximumStep, numberOfWaysToH);
      }
    }
    return numberOfWaysToH[n];
  }
  // @exclude

  public static void main(String[] args) {
    assert(5 == numberOfWaysToTop(4, 2));
    assert(1 == numberOfWaysToTop(1, 2));
    assert(1 == numberOfWaysToTop(0, 3));
    Random r = new Random();
    int n, k;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(20) + 1;
      k = r.nextInt(n) + 1;
    }
    System.out.println("n = " + n + ", k = " + k);
    System.out.println(numberOfWaysToTop(n, k));
  }
}
