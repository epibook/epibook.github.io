package com.epi;

import com.epi.utils.Pair;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ViewSunset {
  // @include
  public static LinkedList<Pair<Integer, Integer>> examineBuildingsWithSunset(
      InputStream sin) {
    int buildingIdx = 0;
    Integer buildingHeight;
    // Stores (buildingIdx, buildingHeight) pair with sunset views.
    LinkedList<Pair<Integer, Integer>> buildingsWithSunset = new LinkedList<>();
    try {
      ObjectInputStream osin = new ObjectInputStream(sin);
      while (true) {
        buildingHeight = (Integer)osin.readObject();
        while (!buildingsWithSunset.isEmpty() &&
               (buildingHeight.compareTo(
                    buildingsWithSunset.getLast().getSecond()) >= 0)) {
          buildingsWithSunset.removeLast();
        }
        buildingsWithSunset.addLast(new Pair<>(buildingIdx++, buildingHeight));
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
        LinkedList<Pair<Integer, Integer>> res = examineBuildingsWithSunset(sin);
        Pair<Integer, Integer> prev = res.pop();
        System.out.println(prev);
        while (!res.isEmpty()) {
          Pair<Integer, Integer> current = res.pop();
          System.out.println(current);
          assert(prev.getFirst() < current.getFirst());
          assert(prev.getSecond() > current.getSecond());
          prev = current;
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
