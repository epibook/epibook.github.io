package com.epi;

import java.io.*;
import java.util.*;

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
      return Double.valueOf(distance).compareTo(rhsDistance);
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
    // @include
  }

  public static List<Star> findClosestKStars(InputStream stars, int k) {
    // maxHeap to store the closest k stars seen so far.
    PriorityQueue<Star> maxHeap =
        new PriorityQueue<>(k, Collections.reverseOrder());
    try {
      ObjectInputStream osin = new ObjectInputStream(stars);

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
      // Do nothing, was read last element in stream.
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException: " + e.getMessage());
    }

    return new ArrayList<>(maxHeap);
  }
  // @exclude

  public static void main(String[] args) {
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
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ByteArrayInputStream sin = null;
      try {
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        for (Star s : stars) {
          oos.writeObject(s);
          // System.out.println(s.distance());
        }
        oos.close();
        sin = new ByteArrayInputStream(baos.toByteArray());
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
      List<Star> closestStars = findClosestKStars(sin, k);
      Collections.sort(closestStars);
      Collections.sort(stars);
      System.out.println("k = " + k);
      assert(stars.get(k - 1).equals(closestStars.get(closestStars.size() - 1)));
    }
  }
}
