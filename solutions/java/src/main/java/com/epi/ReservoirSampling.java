// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.utils.Utils.close;

public class ReservoirSampling {
  // @include
  public static int[] onlineRandomSample(InputStream sin, int k)
      throws IOException, ClassNotFoundException {
    int[] runningSample = new int[k];

    ObjectInputStream osin = new ObjectInputStream(sin);
    // Stores the first k elements.
    Integer x = (Integer)readObjectSilently(osin);
    for (int i = 0; i < k && x != null; ++i) {
      runningSample[i] = x;
      x = (Integer)readObjectSilently(osin);
    }

    // After the first k elements.
    int numSeenSoFar = k + 1;
    x = (Integer)readObjectSilently(osin);
    while (x != null) {
      Random randIdxGen = new Random();
      // Generate a random number in [0, num_seen_so_far], and if this number is
      // in [0, k - 1], we replace that element from the sample with x.
      int idxToReplace = randIdxGen.nextInt(++numSeenSoFar);
      if (idxToReplace < k) {
        runningSample[idxToReplace] = x;
      }

      x = (Integer)readObjectSilently(osin);
    }

    // Close "osin" silently
    close(osin);
    return runningSample;
  }

  private static Object readObjectSilently(ObjectInputStream osin)
      throws ClassNotFoundException, IOException {
    Object object = null;
    try {
      object = osin.readObject();
    } catch (EOFException e) {
      // we don't want to force the calling code to catch an EOFException
      // only to realize there is nothing more to read.
    }
    return object;
  }
  // @exclude

  public static void main(String[] args)
      throws IOException, ClassNotFoundException {
    int n, k;
    Random gen = new Random();

    if (args.length == 1) {
      n = Integer.valueOf(args[0]);
      k = gen.nextInt(n) + 1;
    } else if (args.length == 2) {
      n = Integer.valueOf(args[0]);
      k = Integer.valueOf(args[1]);
    } else {
      n = gen.nextInt(100000);
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

    System.out.println(n + " " + k);

    ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
    int[] ans = onlineRandomSample(sin, k);

    assert ans.length == k;

    close(baos);
    close(oos);
    // simplePrint(ans);
  }
}
