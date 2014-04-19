package com.epi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ThreeJugs {
  // @include
  public static class Jug {
    public int low, high;

    public Jug() {
    }

    public Jug(int low, int high) {
      this.low = low;
      this.high = high;
    }
  }

  private static boolean checkFeasibleHelper(List<Jug> jugs, int L, int H,
      Set<Pair<Integer, Integer>> c) {
    if (L > H || c.contains(new Pair<Integer, Integer>(L, H))
        || (L < 0 && H < 0)) {
      return false;
    }

    // Checks the volume for each jug to see if it is possible.
    for (Jug j : jugs) {
      if ((L <= j.low && j.high <= H) || // base case: j is contained in [L, H]
          checkFeasibleHelper(jugs, L - j.low, H - j.high, c)) {
        return true;
      }
    }
    c.add(new Pair<Integer, Integer>(L, H)); // marks this as impossible
    return false;
  }

  public static boolean checkFeasible(List<Jug> jugs, int L, int H) {
    HashSet<Pair<Integer, Integer>> cache = new HashSet<Pair<Integer, Integer>>();
    return checkFeasibleHelper(jugs, L, H, cache);
  }

  // @exclude

  public static void main(String[] args) {
    int n;
    ArrayList<Jug> jugs = new ArrayList<Jug>();
    jugs.add(new Jug(230, 240));
    jugs.add(new Jug(290, 310));
    jugs.add(new Jug(500, 515));
    assert (checkFeasible(jugs, 2100, 2300) == true);
    jugs.clear();
    Random r = new Random();
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(100) + 1;
    }
    for (int i = 0; i < n; ++i) {
      Jug t = new Jug();
      t.low = r.nextInt(1000);
      t.high = r.nextInt(20) + t.low + 1;
      jugs.add(t);
    }
    int l = r.nextInt(10000);
    int h = r.nextInt(5000) + l + 1;
    System.out.println(checkFeasible(jugs, l, h));
  }
}
