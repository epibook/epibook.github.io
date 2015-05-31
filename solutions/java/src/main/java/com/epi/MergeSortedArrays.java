package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

public class MergeSortedArrays {
  // @include
  public static List<Integer> mergeSortedArrays(
      List<List<Integer>> sortedArrays) {
    PriorityQueue<Pair<Integer, Integer>> minHeap =
        new PriorityQueue<>(11, new Comparator<Pair<Integer, Integer>>() {
          @Override
          public int compare(Pair<Integer, Integer> o1,
                             Pair<Integer, Integer> o2) {
            return o1.getFirst().compareTo(o2.getFirst());
          }
        });
    List<Integer> heads = new ArrayList<>(sortedArrays.size());

    // Puts each sortedArrays' first element in minHeap.
    for (int i = 0; i < sortedArrays.size(); ++i) {
      if (sortedArrays.get(i).size() > 0) {
        minHeap.add(new Pair<>(sortedArrays.get(i).get(0), i));
        heads.add(1);
      } else {
        heads.add(0);
      }
    }

    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      int smallestEntry = minHeap.peek().getFirst();
      List<Integer> smallestArray = sortedArrays.get(minHeap.peek().getSecond());
      int smallestArrayHead = heads.get(minHeap.peek().getSecond());
      result.add(smallestEntry);
      if (smallestArrayHead < smallestArray.size()) {
        // Add the next entry of smallestArray into minHeap.
        minHeap.add(new Pair<>(smallestArray.get(smallestArrayHead),
                               minHeap.peek().getSecond()));
        heads.set(minHeap.peek().getSecond(),
                  heads.get(minHeap.peek().getSecond()) + 1);
      }
      minHeap.remove();
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 100; ++times) {
      int n;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(100);
      }

      List<List<Integer>> S = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int m = rnd.nextInt(500);
        List<Integer> last = new ArrayList<>(m);
        S.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(rnd.nextInt(500));
        }
        Collections.sort(last);
      }

      List<Integer> ans = mergeSortedArrays(S);
      for (int i = 1; i < ans.size(); ++i) {
        assert(ans.get(i - 1) <= ans.get(i));
      }
    }
  }
}
