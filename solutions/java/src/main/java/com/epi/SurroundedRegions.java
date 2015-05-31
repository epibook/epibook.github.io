package com.epi;

import com.epi.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SurroundedRegions {
  // @include
  public static void fillSurroundedRegions(List<List<Character>> board) {
    if (board.isEmpty()) {
      return;
    }

    boolean[][] visited = new boolean[board.size()][board.get(0).size()];
    for (int i = 1; i < board.size() - 1; ++i) {
      for (int j = 1; j < board.get(i).size() - 1; ++j) {
        if (board.get(i).get(j) == 'W' && !visited[i][j]) {
          markRegionIfSurrounded(i, j, board, visited);
        }
      }
    }
  }

  private static void markRegionIfSurrounded(int i, int j,
                                             List<List<Character>> board,
                                             boolean[][] visited) {
    int dir[][] = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // Uses q as a queue.
    List<Pair<Integer, Integer>> q = new ArrayList<>();
    q.add(new Pair<>(i, j));
    visited[i][j] = true;
    boolean isSurrounded = true;
    int idx = 0;
    // Uses BFS to traverse this region.
    while (idx < q.size()) {
      Pair<Integer, Integer> curr = q.get(idx++);
      // A 'W' on the border means this region is not surrounded.
      if (curr.getFirst() == 0 || curr.getFirst() == board.size() - 1 ||
          curr.getSecond() == 0 ||
          curr.getSecond() == board.get(curr.getFirst()).size() - 1) {
        isSurrounded = false;
      } else {
        for (int[] d : dir) {
          Pair<Integer, Integer> next =
              new Pair<>(curr.getFirst() + d[0], curr.getSecond() + d[1]);
          if (board.get(next.getFirst()).get(next.getSecond()) == 'W' &&
              !visited[next.getFirst()][next.getSecond()]) {
            visited[next.getFirst()][next.getSecond()] = true;
            q.add(next);
          }
        }
      }
    }

    if (isSurrounded) {
      // Marks surrounded regions in q.
      for (Pair<Integer, Integer> p : q) {
        board.get(p.getFirst()).set(p.getSecond(), 'B');
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n, m;
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
    } else {
      n = r.nextInt(1000) + 1;
      m = r.nextInt(1000) + 1;
    }
    List<List<Character>> board = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      List<Character> row = new ArrayList<>();
      for (int j = 0; j < m; j++) {
        row.add(r.nextBoolean() ? 'B' : 'W');
      }
      board.add(row);
    }
    for (List<Character> aBoard1 : board) {
      System.out.println(aBoard1);
    }
    fillSurroundedRegions(board);
    System.out.println();
    for (List<Character> aBoard : board) {
      System.out.println(aBoard);
    }
  }
}
