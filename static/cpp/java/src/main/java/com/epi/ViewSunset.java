package com.epi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ViewSunset {
  // @include
  private static class BuildingWithHeight {
    public Integer id;
    public Integer height;

    public BuildingWithHeight(Integer id, Integer height) {
      this.id = id;
      this.height = height;
    }
    // @exclude

    @Override
    public String toString() {
      return "(id = " + id + ", height = " + height + ")";
    }
    // @include
  }

  public static Deque<BuildingWithHeight> examineBuildingsWithSunset(
      Iterator<Integer> sequence) {
    int buildingIdx = 0;
    Deque<BuildingWithHeight> buildingsWithSunset = new LinkedList<>();
    while (sequence.hasNext()) {
      Integer buildingHeight = sequence.next();
      while (!buildingsWithSunset.isEmpty()
             && (Integer.compare(buildingHeight,
                                 buildingsWithSunset.getLast().height)
                 >= 0)) {
        buildingsWithSunset.removeLast();
      }
      buildingsWithSunset.addLast(
          new BuildingWithHeight(buildingIdx++, buildingHeight));
    }
    return buildingsWithSunset;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        Integer height = r.nextInt(2 * n) + 1;
        A.add(height);
      }
      Deque<BuildingWithHeight> res = examineBuildingsWithSunset(A.iterator());
      BuildingWithHeight prev = res.removeFirst();
      System.out.println(prev);
      while (!res.isEmpty()) {
        BuildingWithHeight current = res.removeFirst();
        System.out.println(current);
        assert(prev.id < current.id);
        assert(prev.height > current.height);
        prev = current;
      }
    }
  }
}
