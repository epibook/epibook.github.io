package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OfflineSampling {
  // @include
  public static void randomSampling(int k, List<Integer> A) {
    Random gen = new Random();
    for (int i = 0; i < k; ++i) {
      // Generate a random int in [i, A.size() - 1].
      Collections.swap(A, i, i + gen.nextInt(A.size() - i));
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n, k;
    Random gen = new Random();
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      k = gen.nextInt(n) + 1;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = gen.nextInt(1000000) + 1;
      k = gen.nextInt(n) + 1;
    }

    List<Integer> A = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }
    System.out.println(n + " " + k);

    randomSampling(k, A);
  }
}
