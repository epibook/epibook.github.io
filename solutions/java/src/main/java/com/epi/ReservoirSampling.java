// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReservoirSampling {
  // @include
  public static List<Integer> onlineRandomSample(InputStream sin, int k)
      throws IOException, ClassNotFoundException {
    List<Integer> runningSample = new ArrayList<>(k);

    // Assumption: there are at least k elements in the stream.
    ObjectInputStream osin = new ObjectInputStream(sin);
    // Stores the first k elements.
    for (int i = 0; i < k; ++i) {
      Integer x = (Integer)osin.readObject();
      runningSample.add(x);
    }

    // Have read the first k elements.
    int numSeenSoFar = k;
    Random randIdxGen = new Random();
    while (true) {
      try {
        Integer x = (Integer)osin.readObject();
        ++numSeenSoFar;
        // Generate a random number in [0, numSeenSoFar], and if this number is
        // in [0, k - 1], we replace that element from the sample with x.
        int idxToReplace = randIdxGen.nextInt(numSeenSoFar);
        if (idxToReplace < k) {
          runningSample.set(idxToReplace, x);
        }
      } catch (EOFException e) {
        // Do nothing since the end of the stream has been reached.
        break;
      }
    }
    osin.close();

    return runningSample;
  }
  // @exclude

  public static void main(String[] args)
      throws IOException, ClassNotFoundException {
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

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    for (Integer i : A) {
      oos.writeObject(i);
    }

    ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
    List<Integer> ans = onlineRandomSample(sin, k);

    assert ans.size() == k;

    baos.close();
    oos.close();
    // simplePrint(ans);
  }
}
