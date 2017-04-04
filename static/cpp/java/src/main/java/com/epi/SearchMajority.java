package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SearchMajority {
  // @include
  public static String majoritySearch(Iterator<String> sequence) {
    String candidate = "";
    String iter;
    int candidateCount = 0;
    while (sequence.hasNext()) {
      iter = sequence.next();
      if (candidateCount == 0) {
        candidate = iter;
        candidateCount = 1;
      } else if (candidate.equals(iter)) {
        ++candidateCount;
      } else {
        --candidateCount;
      }
    }
    return candidate;
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  private static void checkAns(List<String> stream, String ans) {
    Collections.sort(stream);
    int candidateCount = 1;
    boolean find = false;
    for (int i = 1; i < stream.size(); ++i) {
      if (!stream.get(i).equals(stream.get(i - 1))) {
        if (candidateCount * 2 >= stream.size()) {
          assert(ans.equals(stream.get(i - 1)));
          find = true;
        }
        candidateCount = 1;
      } else {
        ++candidateCount;
      }
    }
    if (candidateCount * 2 >= stream.size()) {
      assert(ans.equals(stream.get(stream.size() - 1)));
      find = true;
    }
    assert(find);
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      List<String> stream = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      for (int i = 0; i < n; ++i) {
        stream.add(randString(r.nextInt(5) + 1));
      }
      // generate the majority
      for (int i = 0; i < n; ++i) {
        stream.add(stream.get(stream.size() - 1));
      }
      String ret = majoritySearch(stream.iterator());
      System.out.println(ret);
      checkAns(stream, ret);
    }
  }
}
