// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class IndirectSort {
  // @include
  public static void indirectSort(String fileName) throws IOException {
    // Stores file records into A.
    Scanner ifs = null;
    List<Integer> A = new ArrayList<>();
    try {
      ifs = new Scanner(new File(fileName));
      while (ifs.hasNextInt()) {
        A.add(ifs.nextInt());
      }
    } finally {
      if (ifs != null) {
        ifs.close();
      }
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
      if (ofs != null) {
        ofs.close();
      }
    }
  }
  // @exclude

  public static void main(String[] args) throws IOException {
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
        if (ofs != null) {
          ofs.close();
        }
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
        if (ifs != null) {
          ifs.close();
        }
      }

      List<Integer> Adup = new ArrayList<>(A);
      Collections.sort(Adup);
      assert(Adup.equals(A));
      input.delete();
    }
  }
}
