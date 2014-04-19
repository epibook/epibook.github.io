// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

class SearchFrequentItems {

  public static String randString(int len) {
    StringBuilder sb = new StringBuilder();
    Random gen = new Random();
    while (len-- != 0) {
      sb.append((char) (gen.nextInt('z' + 1 - 'a') + 'a'));
    }
    return sb.toString();
  }

  // @include
  public static ArrayList<String> searchFrequentTtems(ArrayList<String> in,
      int k) {
    // Find the candidates which may occur >= n / k times.
    String buf = new String();
    HashMap<String, Integer> hash = new HashMap<String, Integer>();
    int n = 0; // count the number of strings.

    Iterator<String> sin = in.iterator();
    while (sin.hasNext()) {
      buf = sin.next();
      hash.put(buf, hash.containsKey(buf) ? hash.get(buf) + 1 : 1);
      ++n;
      // Detecting k + 1 items in hash, at least one of them must have exactly 1
      // in it. We will discard those k + 1 items by 1 for each.
      if (hash.size() == k + 1) {
        ArrayList<String> delKeys = new ArrayList<String>();
        Iterator<Map.Entry<String, Integer>> it = hash.entrySet().iterator();
        while (it.hasNext()) {
          Map.Entry<String, Integer> entry = it.next();
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

    // Reset hash for the following counting.
    for (String it : hash.keySet()) {
      hash.put(it, 0);
    }

    // Count the occurrence of each candidate word.
    sin = in.iterator();
    while (sin.hasNext()) {
      buf = sin.next();
      Integer it = hash.get(buf);
      if (it != null) {
        hash.put(buf, it + 1);
      }
    }

    ArrayList<String> ret = new ArrayList<String>();
    for (Map.Entry<String, Integer> it : hash.entrySet()) {
      // Select the word which occurs >= n / k times.
      if (new Double(it.getValue()) >= n * 1.0 / k) {
        ret.add(it.getKey());
      }
    }

    return ret;
  }

  // @exclude

  public static void checkAns(ArrayList<String> stream, int k,
      ArrayList<String> items) {
    Collections.sort(stream);
    Collections.sort(items);

    int count = 1, idx = 0;
    for (int i = 1; i < stream.size(); ++i) {
      if (stream.get(i).compareTo(stream.get(i - 1)) != 0) {
        if (new Double(count) >= 1.0 * stream.size() / k) {
          assert (idx < items.size());
          assert (stream.get(i - 1).compareTo(items.get(idx++)) == 0);
        }
        count = 1;
      } else {
        ++count;
      }
    }
    if (new Double(count) >= 1.0 * stream.size() / k) {
      assert (stream.get(stream.size() - 1).compareTo(items.get(idx++)) == 0);
    }
    assert (idx == items.size());
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      System.out.println(times);
      ArrayList<String> stream = new ArrayList<String>();
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
      ArrayList<String> items = searchFrequentTtems(stream, k);
      checkAns(stream, k, items);
    }
  }
}
