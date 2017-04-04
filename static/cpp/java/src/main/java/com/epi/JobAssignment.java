package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class JobAssignment {
  private static void nthElement(List<Task> A, int n, CompTask c) {
    Collections.sort(A, c);
  }

  // @include
  private static class Task {
    public Integer taskId;
    public Integer load;

    public Task(Integer taskId, Integer load) {
      this.taskId = taskId;
      this.load = load;
    }
  }

  private static class Server {
    public Integer serverId;
    public Integer capacity;

    public Server(Integer serverId, Integer capacity) {
      this.serverId = serverId;
      this.capacity = capacity;
    }
  }

  public static boolean[][] findFeasibleJobAssignment(List<Integer> T,
                                                      List<Integer> S) {
    int tTotal = 0;
    for (Integer t : T) {
      tTotal += t;
    }
    int sTotal = 0;
    for (Integer s : S) {
      // Tighter bound of server capacity.
      sTotal += Math.min(s, T.size());
    }

    if (tTotal > sTotal || Collections.max(T) > S.size()) {
      // Too many jobs or one task needs too many servers.
      return new boolean[0][0];
    }

    List<Task> tIdxData = new ArrayList<>();
    List<Server> sIdxData = new ArrayList<>();
    for (int i = 0; i < T.size(); ++i) {
      tIdxData.add(new Task(i, T.get(i)));
    }
    for (int j = 0; j < S.size(); ++j) {
      sIdxData.add(new Server(j, S.get(j)));
    }

    Collections.sort(sIdxData, new CompServer());
    boolean[][] X = new boolean[T.size()][S.size()];
    for (Server aSIdxData : sIdxData) {
      if (aSIdxData.capacity < tIdxData.size()) {
        nthElement(tIdxData, aSIdxData.capacity, new CompTask());
      }

      // Greedily assign jobs.
      int size = Math.min(tIdxData.size(), aSIdxData.capacity);
      for (int i = 0; i < size; ++i) {
        if (tIdxData.get(i).load != 0) {
          X[tIdxData.get(i).taskId][aSIdxData.serverId] = true;
          tIdxData.get(i).load = (tIdxData.get(i).load - 1);
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

  private static class CompTask implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
      return Integer.compare(o1.load, o2.load);
    }
  }

  private static class CompServer implements Comparator<Server> {
    @Override
    public int compare(Server o1, Server o2) {
      return Integer.compare(o1.capacity, o2.capacity);
    }
  }
  // @exclude

  private static void checkAnswer(List<Integer> T, List<Integer> S,
                                  boolean[][] result) {
    // Check row constraints.
    for (int i = 0; i < T.size(); ++i) {
      int sum = 0;
      for (int j = 0; j < S.size(); ++j) {
        sum += result[i][j] ? 1 : 0;
      }
      assert(sum == T.get(i));
    }

    // Check column constraints.
    for (int j = 0; j < S.size(); ++j) {
      int sum = 0;
      for (int i = 0; i < T.size(); ++i) {
        sum += result[i][j] ? 1 : 0;
      }
      assert(sum <= S.get(j));
    }
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      List<Integer> T = new ArrayList<>();
      List<Integer> S = new ArrayList<>();
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
      boolean[][] result = findFeasibleJobAssignment(T, S);
      if (result.length != 0) { // there is a feasible answer.
        System.out.println("found feasible assignment!");
        for (boolean[] re : result) {
          System.out.println(Arrays.toString(re));
        }
        checkAnswer(T, S, result);
      } else {
        // TODO(THL): find a way to verify there is no assignment.
        System.out.println("no feasible assignment");
      }
    }
  }
}
