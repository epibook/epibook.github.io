package com.epi;

/*

@slug
closest-stars

@summary
Consider a coordinate system for the Milky Way, in which Earth is at $(0,0,0)$.
Model stars as points, and assume distances are in light years.
The Milky Way consists of approximately $10^{12}$ stars,
and their coordinates are stored in a file.

@problem
How would you compute the $k$ stars which are closest to Earth?

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class ClosestStars {
  // @include
  public static class Star implements Comparable<Star> {
    private double x, y, z;

    public Star(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public double distance() { return Math.sqrt(x * x + y * y + z * z); }

    @Override
    public int compareTo(Star rhs) {
      return Double.compare(this.distance(), rhs.distance());
    }
    // @exclude
    // clang-format off
    @Override
    public String toString() { return "(" + x + "," + y + "," + z + ")"; }
    // clang-format on
    // @include
  }

  public static List<Star> findClosestKStars(int k, Iterator<Star> stars) {
    // maxHeap to store the closest k stars seen so far.
    PriorityQueue<Star> maxHeap
        = new PriorityQueue<>(k, Collections.reverseOrder());
    while (stars.hasNext()) {
      // Add each star to the max-heap. If the max-heap size exceeds k, remove
      // the maximum element from the max-heap.
      Star star = stars.next();
      maxHeap.add(star);
      if (maxHeap.size() == k + 1) {
        maxHeap.remove();
      }
    }

    List<Star> orderedStars = new ArrayList<Star>(maxHeap);
    // The only guarantee PriorityQueue makes about ordering is that the
    // maximum element comes first, so we sort orderedStars.
    Collections.sort(orderedStars);
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

    List<Star> closestStars = findClosestKStars(3, stars.iterator());
    assert(3 == closestStars.size());
    assert(0 == closestStars.get(0).compareTo(new Star(0, 2, 1)));
    assert(0 == closestStars.get(0).compareTo(new Star(2, 0, 1)));
    assert(0 == closestStars.get(1).compareTo(new Star(1, 2, 1)));
    assert(0 == closestStars.get(1).compareTo(new Star(1, 1, 2)));

    stars = new ArrayList<>();
    stars.add(new Star(1, 2, 3));
    stars.add(new Star(5, 5, 5));
    stars.add(new Star(4, 4, 4));
    stars.add(new Star(3, 2, 1));
    stars.add(new Star(5, 5, 5));
    stars.add(new Star(3, 2, 3));
    stars.add(new Star(3, 2, 3));
    stars.add(new Star(3, 2, 1));
    closestStars = findClosestKStars(2, stars.iterator());
    assert(2 == closestStars.size());
    assert(0 == closestStars.get(0).compareTo(new Star(1, 2, 3)));
    assert(0 == closestStars.get(1).compareTo(new Star(3, 2, 1)));
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
      // Randomly generate num of stars
      for (int i = 0; i < num; ++i) {
        stars.add(
            new Star(r.nextInt(100001), r.nextInt(100001), r.nextInt(100001)));
      }
      List<Star> closestStars = findClosestKStars(k, stars.iterator());
      Collections.sort(closestStars);
      Collections.sort(stars);
      assert(0
             == stars.get(k - 1)
                    .compareTo(closestStars.get(closestStars.size() - 1)));
    }
  }
}
