package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SurroundedRegions {
  // @include
  public static void fillSurroundedRegions(List<List<Character>> board) {
    if (board.isEmpty()) {
      return;
    }

    List<List<Boolean>> visited = new ArrayList<>(board.size());
    for (int i = 0; i < board.size(); ++i) {
      visited.add(
          new ArrayList(Collections.nCopies(board.get(i).size(), false)));
    }
    for (int i = 1; i < board.size() - 1; ++i) {
      for (int j = 1; j < board.get(i).size() - 1; ++j) {
        if (board.get(i).get(j) == 'W' && !visited.get(i).get(j)) {
          markRegionIfSurrounded(i, j, board, visited);
        }
      }
    }
  }

  private static class Coordinate {
    public Integer x;
    public Integer y;

    public Coordinate(Integer x, Integer y) {
      this.x = x;
      this.y = y;
    }
  }

  private static void markRegionIfSurrounded(int i, int j,
                                             List<List<Character>> board,
                                             List<List<Boolean>> visited) {
    // Uses q as a queue.
    List<Coordinate> q = new ArrayList<>();
    q.add(new Coordinate(i, j));
    visited.get(i).set(j, true);
    boolean isSurrounded = true;
    int idx = 0;
    // Uses BFS to traverse this region.
    while (idx < q.size()) {
      Coordinate curr = q.get(idx++);
      // A 'W' on the border means this region is not surrounded.
      if (curr.x == 0 || curr.x == board.size() - 1 || curr.y == 0
          || curr.y == board.get(curr.x).size() - 1) {
        isSurrounded = false;
      } else {
        final int DIRS[][] = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : DIRS) {
          Coordinate next = new Coordinate(curr.x + d[0], curr.y + d[1]);
          if (board.get(next.x).get(next.y) == 'W'
              && !visited.get(next.x).get(next.y)) {
            visited.get(next.x).set(next.y, true);
            q.add(next);
          }
        }
      }
    }

    if (isSurrounded) {
      // Marks surrounded regions in q.
      for (Coordinate p : q) {
        board.get(p.x).set(p.y, 'B');
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
