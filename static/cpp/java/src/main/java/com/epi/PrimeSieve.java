package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrimeSieve {
  // @include
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    final int size = (int)Math.floor(0.5 * (n - 3)) + 1;
    List<Integer> primes = new ArrayList<>();
    primes.add(2);
    // isPrime.get(i) represents whether (2i + 3) is prime or not.
    // Initially, set each to true. Then use sieving to eliminate nonprimes.
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size, true));
    for (int i = 0; i < size; ++i) {
      if (isPrime.get(i)) {
        int p = ((i * 2) + 3);
        primes.add(p);
        // Sieving from p^2, whose value is (4i^2 + 12i + 9). The index of this
        // value in isPrime is (2i^2 + 6i + 3) because isPrime.get(i) represents
        // 2i + 3.
        //
        // Note that we need to use long type for j because p^2 might overflow.
        for (long j = ((i * i) * 2) + 6 * i + 3; j < size; j += p) {
          isPrime.set((int)j, false);
        }
      }
    }
    return primes;
  }
  // @exclude

  public static void main(String[] args) {
    if (args.length == 1) {
      int n = Integer.parseInt(args[0]);
      System.out.println("n = " + n);
      List<Integer> primes = generatePrimes(n);
      for (Integer prime : primes) {
        for (int j = 2; j < prime; ++j) {
          assert(prime % j != 0);
        }
      }
    } else {
      Random r = new Random();
      for (int times = 0; times < 100; ++times) {
        int n = r.nextInt(999999) + 2;
        System.out.println("n = " + n);
        List<Integer> primes = generatePrimes(n);
        for (Integer prime : primes) {
          for (int j = 2; j < prime; ++j) {
            assert(prime % j != 0);
          }
        }
      }
    }
  }
}
