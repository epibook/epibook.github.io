package com.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Deque;
import java.util.LinkedList;
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
      InputStream sin) throws ClassNotFoundException, IOException {
    int buildingIdx = 0;
    Integer buildingHeight;
    Deque<BuildingWithHeight> buildingsWithSunset = new LinkedList<>();
    try {
      ObjectInputStream osin = new ObjectInputStream(sin);
      while (true) {
        buildingHeight = (Integer)osin.readObject();
        while (!buildingsWithSunset.isEmpty()
               && (Integer.compare(buildingHeight,
                                   buildingsWithSunset.getLast().height)
                   >= 0)) {
          buildingsWithSunset.removeLast();
        }
        buildingsWithSunset.addLast(
            new BuildingWithHeight(buildingIdx++, buildingHeight));
      }
    } catch (EOFException e) {
      // Do nothing since the end of the stream has been reached.
    }
    return buildingsWithSunset;
  }
  // @exclude

  public static void main(String[] args) throws ClassNotFoundException {
    Random r = new Random();
    try {
      for (int times = 0; times < 1000; ++times) {
        int n;
        if (args.length == 1) {
          n = Integer.parseInt(args[0]);
        } else {
          n = r.nextInt(10000) + 1;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        for (int i = 0; i < n; ++i) {
          Integer height = r.nextInt(2 * n) + 1;
          oos.writeObject(height);
        }
        ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
        Deque<BuildingWithHeight> res = examineBuildingsWithSunset(sin);
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
