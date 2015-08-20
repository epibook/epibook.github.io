package com.epi;

import java.util.Random;

public class NumberSteps {
  // @include
  public static int numberSteps(int n, int k) {
    if (n <= 1) {
      return 1;
    }

    int steps[] = new int[k + 1];
    steps[0] = steps[1] = 1;
    for (int i = 2; i <= n; ++i) {
      steps[i % (k + 1)] = 0;
      for (int j = 1; j <= k && i - j >= 0; ++j) {
        steps[i % (k + 1)] += steps[(i - j) % (k + 1)];
      }
    }
    return steps[n % (k + 1)];
  }
  // @exclude

  public static void main(String[] args) {
    assert(5 == numberSteps(4, 2));
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
    System.out.println(numberSteps(n, k));
  }
}
