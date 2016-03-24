package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class TopK {
  // @include
  public static List<String> topK(int k, Iterator<String> iter) {
    PriorityQueue<String> minHeap
        = new PriorityQueue<>(k, new Comparator<String>() {
            public int compare(String s1, String s2) {
              return Integer.compare(s1.length(), s2.length());
            }
          });
    while (iter.hasNext()) {
      minHeap.add(iter.next());
      if (minHeap.size() > k) {
        // Remove the shortest string. Note that the comparison function above
        // will order the strings by length.
        minHeap.poll();
      }
    }
    return new ArrayList<>(minHeap);
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int num;
      if (args.length == 1) {
        num = Integer.parseInt(args[0]);
      } else {
        num = r.nextInt(10000) + 1;
      }
      List<String> A = new ArrayList<>();
      for (int i = 0; i < num; ++i) {
        A.add("" + (r.nextInt(10000) + 1));
      }
      int k = r.nextInt(num) + 1;
      List<String> result = topK(k, A.iterator());
      Comparator<String> lambda = new Comparator<String>() {
        public int compare(String s1, String s2) {
          return Integer.compare(s1.length(), s2.length());
        }
      };
      Collections.sort(A, lambda);
      Collections.sort(result, lambda);
      for (int i = 0; i < result.size(); ++i) {
        assert(result.get(i).length() == A.get(A.size() - k + i).length());
      }
    }
  }
}
