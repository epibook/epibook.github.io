// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ReservoirSampling {
  // @include
  // Assumption: there are at least k elements in the stream.
  public static List<Integer> onlineRandomSample(Iterator<Integer> sequence,
                                                 int k) {
    List<Integer> runningSample = new ArrayList<>(k);
    // Stores the first k elements.
    for (int i = 0; sequence.hasNext() && i < k; ++i) {
      runningSample.add(sequence.next());
    }

    // Have read the first k elements.
    int numSeenSoFar = k;
    Random randIdxGen = new Random();
    while (sequence.hasNext()) {
      Integer x = sequence.next();
      ++numSeenSoFar;
      // Generate a random number in [0, numSeenSoFar], and if this number is in
      // [0, k - 1], we replace that element from the sample with x.
      final int idxToReplace = randIdxGen.nextInt(numSeenSoFar);
      if (idxToReplace < k) {
        runningSample.set(idxToReplace, x);
      }
    }
    return runningSample;
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
      n = gen.nextInt(10000) + 1;
      k = gen.nextInt(n) + 1;
    }

    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }

    List<Integer> ans = onlineRandomSample(A.iterator(), k);
    assert(ans.size() == k);
  }
}
