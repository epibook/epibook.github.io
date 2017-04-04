// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fibonacci {
  // @include
  private static Map<Integer, Integer> cache = new HashMap<>();

  public static int fibonacci(int n) {
    if (n <= 1) {
      return n;
    } else if (!cache.containsKey(n)) {
      cache.put(n, fibonacci(n - 2) + fibonacci(n - 1));
    }
    return cache.get(n);
  }
  // @exclude

  private static void smallTest() {
    assert(fibonacci(10) == 55);
    assert(fibonacci(0) == 0);
    assert(fibonacci(1) == 1);
    assert(fibonacci(16) == 987);
    assert(fibonacci(40) == 102334155);
  }

  public static void main(String[] args) {
    smallTest();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      Random gen = new Random();
      n = gen.nextInt(41);
    }
    System.out.println("F(" + n + ") = " + fibonacci(n));
  }
}
