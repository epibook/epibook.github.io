// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import static com.epi.utils.Utils.close;

// @include
class IndirectSort {
  public static void indirectSort(String fileName) throws Exception {
    // Store file records into A.
    Scanner ifs = null;
    ArrayList<Integer> A = new ArrayList<Integer>();
    try {
      ifs = new Scanner(new File(fileName));
      while (ifs.hasNextInt()) {
        A.add(ifs.nextInt());
      }
    } finally {
      close(ifs);
    }

    // Indirectly sort file.
    Collections.sort(A);

    // Output file.
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

  // @exclude

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
      ArrayList<Integer> A = new ArrayList<Integer>();
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

      assert (isSorted(A));
      input.delete();
    }
  }
  // @include
}
