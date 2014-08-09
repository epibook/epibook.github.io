package com.epi;

import com.epi.utils.BinaryOperators;
import com.epi.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static com.epi.utils.Utils.*;

public class NonUniformRandomNumberGeneration {
  // @include
  public static double nonuniformRandomNumberGeneration(List<Double> T,
                                                        List<Double> P) {
    List<Double> prefixP = new ArrayList<>();
    Utils.fill(prefixP, P.size() + 1, 0D);
    ListIterator<Double> iter = prefixP.listIterator();
    iter.next();
    partialSum(P.iterator(), iter, BinaryOperators.ADD);
    // gen object is used to generate random numbers from in [0.0, 1.0]
    Random gen = new Random();

    // upperBound returns an iterator pointing to the first element in
    // (prefixP.cbegin(),prefixP.cend()) which compares greater than
    // gen.nextDouble()
    // which is a uniform random number in [0.0,1.0].
    int it = upperBound(prefixP, gen.nextDouble());
    return T.get(it - 1);
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
    } else {
      n = gen.nextInt(50) + 1;
    }
    List<Double> T = new ArrayList<>(n);
    iota(T, n, 0);
    List<Double> P = new ArrayList<>();
    double fullProb = 1.0;
    for (int i = 0; i < n - 1; ++i) {
      double pi = gen.nextDouble() * fullProb;
      P.add(pi);
      fullProb -= pi;
    }
    P.add(fullProb);

    simplePrint(T);
    System.out.println();

    simplePrint(P);
    System.out.println();

    System.out.println(nonuniformRandomNumberGeneration(T, P));
    // Test. Perform the nonuniform random number generation for n * kTimes
    // times
    // and calculate the distribution of each bucket.
    int kTimes = 100000;
    int[] counts = new int[n];
    for (int i = 0; i < n * kTimes; ++i) {
      double t = nonuniformRandomNumberGeneration(T, P);
      ++counts[(int) t];
    }
    for (int i = 0; i < n; ++i) {
      System.out.println((double) (counts[i]) / (n * kTimes) + " " + P.get(i));
      assert Math.abs(((double) counts[i]) / (n * kTimes) - P.get(i)) < 0.01;
    }
  }

}
