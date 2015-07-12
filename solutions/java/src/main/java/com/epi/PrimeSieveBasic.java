package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PrimeSieveBasic {
  // @include
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    List<Integer> primes = new ArrayList<>();
    // isPrime[p] represents wheather p is prime or not.
    // Initially, set each to true. Then use sieving to eliminate non primes.
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int p = 2; p <= n; ++p) {
      if (isPrime[p]) {
        primes.add(p);
        // Sieve p's multiples.
        for (int j = p; j <= n; j += p) {
          isPrime[j] = false;
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
