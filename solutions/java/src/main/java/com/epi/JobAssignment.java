package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class JobAssignment {
  private static <T> void nthElement(List<T> A, int n, Comparator<T> c) {
    Collections.sort(A, c);
  }

  // @include
  public static boolean[][] findFeasibleJobAssignment(List<Integer> T,
      List<Integer> S) {
    int tTotal = 0;
    for (Integer t : T) {
      tTotal += t;
    }
    int sTotal = 0;
    for (Integer s : S) {
      sTotal += Math.min(s, T.size());
    } // tighter bound of server capacity.

    if (tTotal > sTotal || Collections.max(T) > S.size()) {
      return new boolean[0][0]; // too many jobs or one task needs too many
                                // servers.
    }

    ArrayList<Pair<Integer, Integer>> tIdxData = new ArrayList<Pair<Integer, Integer>>();
    ArrayList<Pair<Integer, Integer>> sIdxData = new ArrayList<Pair<Integer, Integer>>();
    for (int i = 0; i < T.size(); ++i) {
      tIdxData.add(new Pair<Integer, Integer>(i, T.get(i)));
    }
    for (int j = 0; j < S.size(); ++j) {
      sIdxData.add(new Pair<Integer, Integer>(j, S.get(j)));
    }

    Collections.sort(sIdxData, new Comp());
    boolean[][] X = new boolean[T.size()][S.size()];
    for (int j = 0; j < sIdxData.size(); ++j) {
      if (sIdxData.get(j).getSecond() < tIdxData.size()) {
        nthElement(tIdxData, sIdxData.get(j).getSecond(), new Comp());
      }

      // Greedily assign jobs.
      int size = Math.min(tIdxData.size(), sIdxData.get(j).getSecond());
      for (int i = 0; i < size; ++i) {
        if (tIdxData.get(i).getSecond() != 0) {
          X[tIdxData.get(i).getFirst()][sIdxData.get(j).getFirst()] = true;
          tIdxData.get(i).setSecond(tIdxData.get(i).getSecond() - 1);
          --tTotal;
        }
      }
    }
    if (tTotal != 0) {
      return new boolean[0][0]; // still some jobs remain, no feasible
                                // assignment.
    }
    return X;
  }

  private static class Comp implements Comparator<Pair<Integer, Integer>> {
    @Override
    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
      return o1.getSecond().compareTo(o2.getSecond());
    }
  }

  // @exclude

  private static void checkAnswer(List<Integer> T, List<Integer> S,
      boolean[][] res) {
    // Check row constraints.
    for (int i = 0; i < T.size(); ++i) {
      int sum = 0;
      for (int j = 0; j < S.size(); ++j) {
        sum += res[i][j] ? 1 : 0;
      }
      assert (sum == T.get(i));
    }

    // Check column constraints.
    for (int j = 0; j < S.size(); ++j) {
      int sum = 0;
      for (int i = 0; i < T.size(); ++i) {
        sum += res[i][j] ? 1 : 0;
      }
      assert (sum <= S.get(j));
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      ArrayList<Integer> T = new ArrayList<Integer>();
      ArrayList<Integer> S = new ArrayList<Integer>();
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(100) + 1;
        m = r.nextInt(100) + 1;
      }
      for (int i = 0; i < n; ++i) {
        T.add(r.nextInt(5) + 1);
      }
      for (int i = 0; i < m; ++i) {
        S.add(r.nextInt(10) + 1);
      }
      System.out.println("T = " + T);
      System.out.println("S = " + S);
      boolean[][] res = findFeasibleJobAssignment(T, S);
      if (res.length != 0) { // there is a feasible answer.
        System.out.println("found feasible assignment!");
        for (boolean[] re : res) {
          System.out.println(Arrays.toString(re));
        }
        checkAnswer(T, S, res);
      } else {
        // TODO(THL): find a way to verify there is no assignment.
        System.out.println("no feasible assignment");
      }
    }
  }
}
