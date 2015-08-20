package com.epi;

import java.io.*;
import java.util.*;
import java.util.LinkedList;

import static com.epi.utils.Utils.objectInputStreamFromList;

public class ClosestStars {
  // @include
  public static class Star implements Comparable<Star>, Serializable {
    private double x, y, z;

    public Star(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public int compareTo(Star rhs) {
      double rhsDistance = rhs.x * rhs.x + rhs.y * rhs.y + rhs.z * rhs.z;
      double distance = x * x + y * y + z * z;
      int cmp = Double.valueOf(distance).compareTo(rhsDistance);
      return cmp;
    }
    // @exclude

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Star)) {
        return false;
      }
      if (obj == this) {
        return true;
      }

      Star rhs = (Star)obj;
      double rhsDistance = rhs.x * rhs.x + rhs.y * rhs.y + rhs.z * rhs.z;
      double distance = x * x + y * y + z * z;
      return distance == rhsDistance;
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + "," + z + ")";
    }
    // @include
  }

  public static List<Star> findClosestKStars(int k, ObjectInputStream osin) {
    // maxHeap to store the closest k stars seen so far.
    PriorityQueue<Star> maxHeap =
        new PriorityQueue<>(k, Collections.reverseOrder());
    try {
      while (true) {
        // Add each star to the max-heap. If the max-heap size exceeds k,
        // remove the maximum element from the max-heap.
        Star star = (Star)osin.readObject();
        maxHeap.add(star);
        if (maxHeap.size() == k + 1) {
          maxHeap.remove();
        }
      }
    } catch (IOException e) {
      // Do nothing, read last element in stream.
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException: " + e.getMessage());
    }

    // We cannot go directly to an ArrayList from PriorityQueue, since
    // unlike LinkedList, it does not guarantee ordering of entries.
    List<Star> orderedStars = new ArrayList<Star>(maxHeap);
    // We need to reverse the orderedStars list since it goes from
    // largest to smallest because the PriorityQueue used the
    // Collections.reverse() comparator.
    Collections.reverse(orderedStars);
    return orderedStars;
  }
  // @exclude

  private static void simpleTest() {
    List<Star> stars = new ArrayList<>();
    stars.add(new Star(1, 2, 3));
    stars.add(new Star(5, 5, 5));
    stars.add(new Star(0, 2, 1));
    stars.add(new Star(9, 2, 1));
    stars.add(new Star(1, 2, 1));
    stars.add(new Star(2, 2, 1));
    ObjectInputStream ois = objectInputStreamFromList(stars);

    List<Star> closestStars = findClosestKStars(3, ois);
    assert(3 == closestStars.size());
    assert(closestStars.get(0).equals(new Star(0, 2, 1)));
    assert(closestStars.get(0).equals(new Star(2, 0, 1)));
    assert(closestStars.get(1).equals(new Star(1, 2, 1)));
    assert(closestStars.get(1).equals(new Star(1, 1, 2)));

    stars = new ArrayList<>();
    stars.add(new Star(1, 2, 3));
    stars.add(new Star(5, 5, 5));
    stars.add(new Star(4, 4, 4));
    stars.add(new Star(3, 2, 1));
    stars.add(new Star(5, 5, 5));
    stars.add(new Star(3, 2, 3));
    stars.add(new Star(3, 2, 3));
    stars.add(new Star(3, 2, 1));
    ois = objectInputStreamFromList(stars);
    closestStars = findClosestKStars(2, ois);
    assert(2 == closestStars.size());
    assert(closestStars.get(0).equals(new Star(1, 2, 3)));
    assert(closestStars.get(1).equals(new Star(3, 2, 1)));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int num, k;
      if (args.length == 1) {
        num = Integer.parseInt(args[0]);
        k = r.nextInt(num) + 1;
      } else if (args.length == 2) {
        num = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
      } else {
        num = r.nextInt(10000) + 1;
        k = r.nextInt(num) + 1;
      }
      List<Star> stars = new ArrayList<>();
      // randomly generate num of stars
      for (int i = 0; i < num; ++i) {
        stars.add(
            new Star(r.nextInt(100001), r.nextInt(100001), r.nextInt(100001)));
      }
      ObjectInputStream ois = objectInputStreamFromList(stars);
      List<Star> closestStars = null;
      closestStars = findClosestKStars(k, ois);
      Collections.sort(closestStars);
      Collections.sort(stars);
      assert(stars.get(k - 1).equals(closestStars.get(closestStars.size() - 1)));
    }
  }
}
