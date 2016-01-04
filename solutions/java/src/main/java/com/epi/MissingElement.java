package com.epi;

import com.epi.utils.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class MissingElement {
  // @include
  private static final int NUM_BUCKET = 1 << 16;

  public static int findMissingElement(InputStream ifs) throws IOException {
    List<Integer> counter = new ArrayList<>(Collections.nCopies(NUM_BUCKET, 0));
    ifs.mark(Integer.MAX_VALUE);
    Scanner s = new Scanner(ifs);
    while (s.hasNextInt()) {
      int idx = s.nextInt() >>> 16;
      counter.set(idx, counter.get(idx) + 1);
    }

    for (int i = 0; i < counter.size(); ++i) {
      // Look for a bucket that contains less than NUM_BUCKET elements.
      if (counter.get(i) < NUM_BUCKET) {
        BitSet bitVec = new BitSet(NUM_BUCKET);
        ifs.reset();
        s = new Scanner(ifs);
        while (s.hasNext()) {
          int x = s.nextInt();
          if (i == (x >>> 16)) {
            bitVec.set(((NUM_BUCKET)-1) & x); // Gets the lower 16 bits of x.
          }
        }
        ifs.close();

        for (int j = 0; j < (1 << 16); ++j) {
          if (!bitVec.get(j)) {
            return (i << 16) | j;
          }
        }
      }
    }
    // @exclude
    throw new IllegalArgumentException("no missing element");
    // @include
  }
  // @exclude

  public static void main(String[] args) {
    int n = 990000;
    Random r = new Random();
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    }
    File missingFile = new File("missing.txt");
    Set<Integer> hash = new HashSet<>();
    FileOutputStream ofs = null;
    try {
      OutputStreamWriter osw = null;
      try {
        ofs = new FileOutputStream(missingFile);
        osw = new OutputStreamWriter(ofs);
        for (int i = 0; i < n; ++i) {
          int x;
          do {
            x = r.nextInt(1000000);
          } while (!hash.add(x));
          osw.write(x + "\n");
        }
      } finally {
        Utils.closeSilently(osw);
      }

      FileInputStream ifs = null;
      try {
        ifs = new FileInputStream(missingFile);
        BufferedInputStream bis = new BufferedInputStream(ifs);
        int missing = findMissingElement(bis);
        assert(!hash.contains(missing));
        System.out.println(missing);
      } finally {
        if (ifs != null) {
          ifs.close();
        }
      }
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    } finally {
      // Remove file after the execution.
      missingFile.delete();
    }
  }
}
