package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    // Identifies the regions that are reachable via white path starting from
    // the first or last columns.
    for (int i = 0; i < board.size(); ++i) {
      if (board.get(i).get(0) == 'W' && !visited.get(i).get(0)) {
        markBoundaryRegion(i, 0, board, visited);
      }
      if (board.get(i).get(board.get(i).size() - 1) == 'W'
          && !visited.get(i).get(board.get(i).size() - 1)) {
        markBoundaryRegion(i, board.get(i).size() - 1, board, visited);
      }
    }
    // Identifies the regions that are reachable via white path starting from
    // the first or last rows.
    for (int j = 0; j < board.get(0).size(); ++j) {
      if (board.get(0).get(j) == 'W' && !visited.get(0).get(j)) {
        markBoundaryRegion(0, j, board, visited);
      }
      if (board.get(board.size() - 1).get(j) == 'W'
          && !visited.get(board.size() - 1).get(j)) {
        markBoundaryRegion(board.size() - 1, j, board, visited);
      }
    }

    // Marks the surrounded white regions as black.
    for (int i = 1; i < board.size() - 1; ++i) {
      for (int j = 1; j < board.get(i).size() - 1; ++j) {
        if (board.get(i).get(j) == 'W' && !visited.get(i).get(j)) {
          board.get(i).set(j, 'B');
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

  private static void markBoundaryRegion(int i, int j,
                                         List<List<Character>> board,
                                         List<List<Boolean>> visited) {
    Queue<Coordinate> q = new LinkedList<>();
    q.add(new Coordinate(i, j));
    visited.get(i).set(j, true);
    // Uses BFS to traverse this region.
    while (!q.isEmpty()) {
      Coordinate curr = q.poll();
      final int DIRS[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      for (int[] dir : DIRS) {
        Coordinate next = new Coordinate(curr.x + dir[0], curr.y + dir[1]);
        if (next.x >= 0 && next.x < board.size() && next.y >= 0
            && next.y < board.get(next.x).size()
            && board.get(next.x).get(next.y) == 'W'
            && !visited.get(next.x).get(next.y)) {
          visited.get(next.x).set(next.y, true);
          q.add(next);
        }
      }
    }
  }
  // @exclude

  private static void simpleTest() {
    List<List<Character>> A = Arrays.asList(
        Arrays.asList('B', 'B', 'B', 'B'), Arrays.asList('W', 'B', 'W', 'B'),
        Arrays.asList('B', 'W', 'W', 'B'), Arrays.asList('B', 'B', 'B', 'B'));
    fillSurroundedRegions(A);
    List<List<Character>> golden = Arrays.asList(
        Arrays.asList('B', 'B', 'B', 'B'), Arrays.asList('W', 'B', 'B', 'B'),
        Arrays.asList('B', 'B', 'B', 'B'), Arrays.asList('B', 'B', 'B', 'B'));
    assert(A.equals(golden));
  }

  public static void main(String[] args) {
    simpleTest();
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
