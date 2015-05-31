package com.epi;

import java.util.*;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchMaze {
  public static class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate)o;

      if (x != that.x) {
        return false;
      }
      if (y != that.y) {
        return false;
      }

      return true;
    }

    @Override
    public String toString() {
      return x + " " + y;
    }
  }

  // @include
  public static LinkedList<Coordinate> searchMaze(int[][] maze, Coordinate s,
                                                  Coordinate e) {
    LinkedList<Coordinate> path = new LinkedList<>();
    maze[s.x][s.y] = 1;
    path.addFirst(s);
    if (!searchMazeHelper(maze, s, e, path)) {
      path.removeLast();
    }
    return path; // empty path means no path between s and e.
  }

  // Performs DFS to find a feasible path.
  private static boolean searchMazeHelper(int[][] maze, Coordinate cur,
                                          Coordinate e,
                                          LinkedList<Coordinate> path) {
    if (cur.equals(e)) {
      return true;
    }

    List<? extends List<Integer>> shift =
        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(0, -1),
                      Arrays.asList(1, 0), Arrays.asList(-1, 0));

    for (List<Integer> s : shift) {
      Coordinate next = new Coordinate(cur.x + s.get(0), cur.y + s.get(1));
      if (isFeasible(next, maze)) {
        maze[next.x][next.y] = 1;
        path.addLast(next);
        if (searchMazeHelper(maze, next, e, path)) {
          return true;
        }
        path.removeLast();
      }
    }
    return false;
  }

  // Checks cur is within maze and is a white pixel.
  private static boolean isFeasible(Coordinate cur, int[][] maze) {
    return cur.x >= 0 && cur.x < maze.length && cur.y >= 0 &&
        cur.y < maze[cur.x].length && maze[cur.x][cur.y] == 0;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n, m;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
      } else {
        n = r.nextInt(30) + 1;
        m = r.nextInt(30) + 1;
      }
      int[][] maze = new int[n][m];
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          maze[i][j] = r.nextInt(2);
        }
      }
      List<Coordinate> white = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          if (maze[i][j] == 0) {
            white.add(new Coordinate(i, j));
          }
          System.out.print(maze[i][j] + " ");
        }
        System.out.println();
      }
      System.out.println();
      if (white.size() != 0) {
        int start = r.nextInt(white.size());
        int end = r.nextInt(white.size());
        System.out.println(white.get(start));
        System.out.println(white.get(end));
        LinkedList<Coordinate> path =
            searchMaze(maze, white.get(start), white.get(end));
        if (!path.isEmpty()) {
          assert(white.get(start).equals(path.peekFirst()) &&
                 white.get(end).equals(path.peekLast()));
        }
        Coordinate prev = null;
        for (Coordinate curr : path) {
          if (prev != null) {
            assert(Math.abs(prev.x - curr.x) + Math.abs(prev.y - curr.y) == 1);
          }
          prev = curr;
          System.out.println("(" + curr.x + "," + curr.y + ")");
        }
      }
    }
  }
}
