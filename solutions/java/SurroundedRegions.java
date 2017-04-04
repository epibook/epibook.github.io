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
    // Identifies the regions that are reachable via white path starting from
    // the first or last columns.
    for (int i = 0; i < board.size(); ++i) {
      if (board.get(i).get(0) == 'W') {
        markBoundaryRegion(i, 0, board);
      }
      if (board.get(i).get(board.get(i).size() - 1) == 'W') {
        markBoundaryRegion(i, board.get(i).size() - 1, board);
      }
    }
    // Identifies the regions that are reachable via white path starting from
    // the first or last rows.
    for (int j = 0; j < board.get(0).size(); ++j) {
      if (board.get(0).get(j) == 'W') {
        markBoundaryRegion(0, j, board);
      }
      if (board.get(board.size() - 1).get(j) == 'W') {
        markBoundaryRegion(board.size() - 1, j, board);
      }
    }

    // Marks the surrounded white regions as black.
    for (int i = 0; i < board.size(); ++i) {
      for (int j = 0; j < board.get(i).size(); ++j) {
        board.get(i).set(j, board.get(i).get(j) != 'T' ? 'B' : 'W');
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
                                         List<List<Character>> board) {
    Queue<Coordinate> q = new LinkedList<>();
    q.add(new Coordinate(i, j));
    // Uses BFS to traverse this region.
    while (!q.isEmpty()) {
      Coordinate curr = q.poll();
      if (curr.x >= 0 && curr.x < board.size() && curr.y >= 0
          && curr.y < board.get(curr.x).size()
          && board.get(curr.x).get(curr.y) == 'W') {
        board.get(curr.x).set(curr.y, 'T');
        q.add(new Coordinate(curr.x - 1, curr.y));
        q.add(new Coordinate(curr.x + 1, curr.y));
        q.add(new Coordinate(curr.x, curr.y - 1));
        q.add(new Coordinate(curr.x, curr.y + 1));
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
