package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrimeSieveBasic {
  // @include
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    List<Integer> primes = new ArrayList<>();
    // isPrime.get(p) represents if p is prime or not. Initially, set each
    // to true, excepting 0 and 1. Then use sieving to eliminate nonprimes.
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
    isPrime.set(0, false);
    isPrime.set(1, false);
    for (int p = 2; p <= n; ++p) {
      if (isPrime.get(p)) {
        primes.add(p);
        // Sieve p's multiples.
        for (int j = p; j <= n; j += p) {
          isPrime.set(j, false);
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
