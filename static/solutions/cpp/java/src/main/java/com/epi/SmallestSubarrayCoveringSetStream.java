// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import static com.epi.utils.Utils.objectInputStreamFromList;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SmallestSubarrayCoveringSetStream {
  // @include
  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  private static Integer getValueForFirstEntry(
      LinkedHashMap<String, Integer> m) {
    // LinkedHashMap guarantees iteration over key-value pairs takes place in
    // insertion order, most recent first.
    Integer result = null;
    for (Map.Entry<String, Integer> entry : m.entrySet()) {
      result = entry.getValue();
      break;
    }
    return result;
  }

  public static Subarray findSmallestSubarrayCoveringSubset(
      Iterator<String> iter, List<String> queryStrings) {
    LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();
    for (String s : queryStrings) {
      dict.put(s, null);
    }
    int numStringsFromQueryStringsSeenSoFar = 0;

    Subarray res = new Subarray(-1, -1);
    int idx = 0;
    while (iter.hasNext()) {
      String s = iter.next();
      if (dict.containsKey(s)) { // s is in queryStrings.
        Integer it = dict.get(s);
        if (it == null) {
          // First time seeing this string from queryStrings.
          numStringsFromQueryStringsSeenSoFar++;
        }
        // dict.put(s,idx) won't work because it does not move the entry to
        // the front of the queue if an entry with key s is already present.
        // So we explicitly remove the existing entry with key s, then put
        // (s,idx).
        dict.remove(s);
        dict.put(s, idx);
      }

      if (numStringsFromQueryStringsSeenSoFar == queryStrings.size()) {
        // We have seen all strings in queryStrings, let's get to work.
        if ((res.start == -1 && res.end == -1)
            || idx - getValueForFirstEntry(dict) < res.end - res.start) {
          res.start = getValueForFirstEntry(dict);
          res.end = idx;
        }
      }
      ++idx;
    }
    return res;
  }
  // @exclude

  public static Integer[] findSmallestSubarrayCoveringSubsetAsArray(
      List<String> A, List<String> queryStrings) {
    Subarray result
        = findSmallestSubarrayCoveringSubset(A.iterator(), queryStrings);
    Integer[] resultArray = new Integer[2];
    resultArray[0] = result.start;
    resultArray[1] = result.end;
    return resultArray;
  }
}
