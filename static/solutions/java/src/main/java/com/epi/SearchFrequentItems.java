// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SearchFrequentItems {
  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char)(gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static List<String> searchFrequentTtems(Iterable<String> input,
                                                 int k) {
    // Finds the candidates which may occur > n / k times.
    String buf = "";
    Map<String, Integer> hash = new HashMap<>();
    int n = 0; // Counts the number of strings.

    Iterator<String> sequence = input.iterator();
    while (sequence.hasNext()) {
      buf = sequence.next();
      hash.put(buf, hash.containsKey(buf) ? hash.get(buf) + 1 : 1);
      ++n;
      // Detecting k items in hash, at least one of them must have exactly one
      // in it. We will discard those k items by one for each.
      if (hash.size() == k) {
        List<String> delKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
          if (entry.getValue() - 1 == 0) {
            delKeys.add(entry.getKey());
          }
        }
        for (String s : delKeys) {
          hash.remove(s);
        }

        for (Map.Entry<String, Integer> e : hash.entrySet()) {
          hash.put(e.getKey(), e.getValue() - 1);
        }
      }
    }

    // Resets hash for the following counting.
    for (String it : hash.keySet()) {
      hash.put(it, 0);
    }

    // Counts the occurrence of each candidate word.
    sequence = input.iterator();
    while (sequence.hasNext()) {
      buf = sequence.next();
      Integer it = hash.get(buf);
      if (it != null) {
        hash.put(buf, it + 1);
      }
    }

    // Selects the word which occurs > n / k times.
    List<String> ret = new ArrayList<>();
    for (Map.Entry<String, Integer> it : hash.entrySet()) {
      if (n * 1.0 / k < (double)it.getValue()) {
        ret.add(it.getKey());
      }
    }

    return ret;
  }
  // @exclude

  public static void checkAns(List<String> stream, int k, List<String> items) {
    Collections.sort(stream);
    Collections.sort(items);

    int count = 1, idx = 0;
    for (int i = 1; i < stream.size(); ++i) {
      if (stream.get(i).compareTo(stream.get(i - 1)) != 0) {
        if ((double)count > 1.0 * stream.size() / k) {
          assert(idx < items.size());
          assert(stream.get(i - 1).compareTo(items.get(idx++)) == 0);
        }
        count = 1;
      } else {
        ++count;
      }
    }
    if ((double)count > 1.0 * stream.size() / k) {
      assert(stream.get(stream.size() - 1).compareTo(items.get(idx++)) == 0);
    }
    assert(idx == items.size());
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println(times);
      List<String> stream = new ArrayList<>();
      int n, k;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
        k = gen.nextInt(n) + 1;
      } else if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        n = gen.nextInt(99999);
        k = gen.nextInt(n) + 1;
      }
      for (int i = 0; i < n; ++i) {
        stream.add(randString(gen.nextInt(5) + 1));
      }
      List<String> items = searchFrequentTtems(stream, k);
      checkAns(stream, k, items);
    }
  }
}
