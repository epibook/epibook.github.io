package com.epi;

import java.io.*;
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

    @Override
    public String toString() {
      return "(id = " + id + ", height = " + height + ")";
    }
  }
  public static LinkedList<BuildingWithHeight> examineBuildingsWithSunset(
      InputStream sin) {
    int buildingIdx = 0;
    Integer buildingHeight;
    // Stores (buildingIdx, buildingHeight) pair with sunset views.
    LinkedList<BuildingWithHeight> buildingsWithSunset = new LinkedList<>();
    try {
      ObjectInputStream osin = new ObjectInputStream(sin);
      while (true) {
        buildingHeight = (Integer)osin.readObject();
        while (!buildingsWithSunset.isEmpty() &&
               (buildingHeight.compareTo(buildingsWithSunset.getLast().height) >=
                0)) {
          buildingsWithSunset.removeLast();
        }
        buildingsWithSunset.addLast(
            new BuildingWithHeight(buildingIdx++, buildingHeight));
      }
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      // Catching when there no more objects in InputStream
    }
    return buildingsWithSunset;
  }
  // @exclude

  public static void main(String[] args) {
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
        LinkedList<BuildingWithHeight> res = examineBuildingsWithSunset(sin);
        BuildingWithHeight prev = res.pop();
        System.out.println(prev);
        while (!res.isEmpty()) {
          BuildingWithHeight current = res.pop();
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
