package com.epi;

import java.io.*;
import java.util.*;

public class MissingElement {
  // @include
  public static int findMissingElement(InputStream ifs) throws IOException {
    int[] counter = new int[1 << 16];
    ifs.mark(Integer.MAX_VALUE);
    Scanner s = new Scanner(ifs);
    while (s.hasNextInt()) {
      ++counter[s.nextInt() >> 16];
    }

    for (int i = 0; i < counter.length; ++i) {
      // Finds one bucket contains less than (1 << 16) elements.
      if (counter[i] < (1 << 16)) {
        BitSet bitVec = new BitSet(1 << 16);
        ifs.reset();
        s = new Scanner(ifs);
        while (s.hasNext()) {
          int x = s.nextInt();
          if (i == (x >> 16)) {
            bitVec.set(((1 << 16) - 1) & x); // Gets the lower 16 bits of x.
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
    throw new RuntimeException("no missing element");
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
      try {
        ofs = new FileOutputStream(missingFile);
        OutputStreamWriter osw = new OutputStreamWriter(ofs);
        for (int i = 0; i < n; ++i) {
          int x;
          do {
            x = r.nextInt(1000000);
          } while (!hash.add(x));
          osw.write(x + "\n");
        }
      } finally {
        if (ofs != null) {
          ofs.close();
        }
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
