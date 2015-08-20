package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
  public static LinkedList<Coordinate> searchMaze(List<List<Integer>> maze,
                                                  Coordinate s, Coordinate e) {
    LinkedList<Coordinate> path = new LinkedList<>();
    maze.get(s.x).set(s.y, 1);
    path.addFirst(s);
    if (!searchMazeHelper(maze, s, e, path)) {
      path.removeLast();
    }
    return path; // Empty path means no path between s and e.
  }

  // Performs DFS to find a feasible path.
  private static boolean searchMazeHelper(List<List<Integer>> maze,
                                          Coordinate cur, Coordinate e,
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
        maze.get(next.x).set(next.y, 1);
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
  private static boolean isFeasible(Coordinate cur, List<List<Integer>> maze) {
    return cur.x >= 0 && cur.x < maze.size() && cur.y >= 0 &&
        cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0;
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
      List<List<Integer>> maze = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        maze.add(new ArrayList(m));
        for (int j = 0; j < m; ++j) {
          maze.get(i).add(r.nextInt(2));
        }
      }
      List<Coordinate> white = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
          if (maze.get(i).get(j) == 0) {
            white.add(new Coordinate(i, j));
          }
          System.out.print(maze.get(i).get(j) + " ");
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
