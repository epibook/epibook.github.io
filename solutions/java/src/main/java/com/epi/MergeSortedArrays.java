package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

public class MergeSortedArrays {
  // @include
  public static List<Integer> mergeSortedArrays(int[][] sortedArrays) {
    PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(
        11, new Comparator<Pair<Integer, Integer>>() {
      @Override
      public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
        return o1.getFirst().compareTo(o2.getFirst());
      }
    }
    );
    int[] heads = new int[sortedArrays.length];

    // Puts each sortedArrays' first element in minHeap.
    for (int i = 0; i < sortedArrays.length; ++i) {
      if (sortedArrays[i].length > 0) {
        minHeap.add(new Pair<>(sortedArrays[i][0], i));
        heads[i] = 1;
      } else {
        heads[i] = 0;
      }
    }

    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      int smallestEntry = minHeap.peek().getFirst();
      int[] smallestArray = sortedArrays[minHeap.peek().getSecond()];
      int smallestArrayHead = heads[minHeap.peek().getSecond()];
      result.add(smallestEntry);
      if (smallestArrayHead < smallestArray.length) {
        // Add the next entry of smallestArray into minHeap.
        minHeap.add(new Pair<>(smallestArray[smallestArrayHead],
                               minHeap.peek().getSecond()));
        ++heads[minHeap.peek().getSecond()];
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

      int[][] S = new int[n][];
      for (int i = 0; i < n; ++i) {
        S[i] = new int[rnd.nextInt(500)];
        for (int j = 0; j < S[i].length; ++j) {
          S[i][j] = rnd.nextInt(10000);
        }
        Arrays.sort(S[i]);
      }

      List<Integer> ans = mergeSortedArrays(S);
      for (int i = 1; i < ans.size(); ++i) {
        assert (ans.get(i - 1) <= ans.get(i));
      }
    }
  }
}
