// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import static com.epi.utils.Utils.close;

class IndirectSort {
  // @include
  public static void indirectSort(String fileName) throws Exception {
    // Stores file records into A.
    Scanner ifs = null;
    List<Integer> A = new ArrayList<>();
    try {
      ifs = new Scanner(new File(fileName));
      while (ifs.hasNextInt()) {
        A.add(ifs.nextInt());
      }
    } finally {
      close(ifs);
    }

    // Indirectly sorts file.
    Collections.sort(A);

    // Outputs file.
    PrintWriter ofs = null;
    try {
      ofs = new PrintWriter(new FileWriter(fileName));
      for (Integer a : A) {
        ofs.println(a);
      }
    } finally {
      close(ofs);
    }
  }
  // @exclude

  public static <T extends Comparable<T>> boolean isSorted(Iterable<T> iterable) {
    Iterator<T> iter = iterable.iterator();
    if (!iter.hasNext()) {
      return true;
    }
    T t = iter.next();
    while (iter.hasNext()) {
      T t2 = iter.next();
      if (t.compareTo(t2) > 0) {
        return false;
      }
      t = t2;
    }
    return true;
  }

  public static void main(String[] args) throws Exception {
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println("times = " + times);
      int n = 0;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(rnd.nextInt(999999 + 1));
      }

      PrintWriter ofs = null;
      try {
        ofs = new PrintWriter(new FileWriter("input.txt"));
        for (Integer a : A) {
          ofs.println(a);
        }
      } finally {
        close(ofs);
      }
      indirectSort("input.txt");

      Scanner ifs = null;
      File input = new File("input.txt");
      try {
        ifs = new Scanner(input);
        A.clear();
        while (ifs.hasNextInt()) {
          A.add(ifs.nextInt());
        }
      } finally {
        close(ifs);
      }

      assert(isSorted(A));
      input.delete();
    }
  }
}
