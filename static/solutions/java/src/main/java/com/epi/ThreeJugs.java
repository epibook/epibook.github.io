package com.epi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class ThreeJugs {
  // @include
  public static class Jug {
    public int low, high;

    public Jug() {}

    public Jug(int low, int high) {
      this.low = low;
      this.high = high;
    }
  }

  private static class VolumeRange {
    public Integer low;
    public Integer high;

    public VolumeRange(Integer low, Integer high) {
      this.low = low;
      this.high = high;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof VolumeRange)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      VolumeRange vr = (VolumeRange)obj;
      return low.equals(vr.low) && high.equals(vr.high);
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(low, high); }
    // clang-format on
  }

  public static boolean checkFeasible(List<Jug> jugs, int L, int H) {
    Set<VolumeRange> cache = new HashSet<>();
    return checkFeasibleHelper(jugs, L, H, cache);
  }

  private static boolean checkFeasibleHelper(List<Jug> jugs, int L, int H,
                                             Set<VolumeRange> c) {
    if (L > H || c.contains(new VolumeRange(L, H)) || (L < 0 && H < 0)) {
      return false;
    }

    // Checks the volume for each jug to see if it is possible.
    for (Jug j : jugs) {
      if ((L <= j.low && j.high <= H) || // Base case: j is contained in [L, H].
          checkFeasibleHelper(jugs, L - j.low, H - j.high, c)) {
        return true;
      }
    }
    c.add(new VolumeRange(L, H)); // Marks this as impossible.
    return false;
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    List<Jug> jugs = new ArrayList<>();
    jugs.add(new Jug(230, 240));
    jugs.add(new Jug(290, 310));
    jugs.add(new Jug(500, 515));
    assert(checkFeasible(jugs, 2100, 2300));
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
