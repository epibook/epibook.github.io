package com.epi;

import com.epi.QueueWithMaxUsingDeque.Queue;

import java.util.Arrays;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SlidingWindow {
  // @include
  public static class TrafficElement implements Comparable<TrafficElement> {
    private final int time, volume;

    public TrafficElement(int time, int volume) {
      this.time = time;
      this.volume = volume;
    }

    public int getTime() {
      return time;
    }

    public int getVolume() {
      return volume;
    }

    @Override
    public int compareTo(TrafficElement o) {
      int result = volume - o.getVolume();
      return result != 0 ? result : time - o.getTime();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      return compareTo((TrafficElement) o) == 0;
    }
  }

  public static void trafficVolumes(List<TrafficElement> A, int w) {
    Queue<TrafficElement> Q = new Queue<>();
    for (int i = 0; i < A.size(); i++) {
      Q.enqueue(A.get(i));
      while (A.get(i).getTime() - Q.head().getTime() > w) {
        Q.dequeue();
      }
      System.out.println("Max after inserting " + i + " is "
          + Q.max().getVolume());
    }
  }
  // @exclude

  public static void main(String[] args) {
    int w = 3;
    // It should output 0, 1, 3, 3, 3, 3, 2, 2.
    List<TrafficElement> A = Arrays.asList(new TrafficElement(0, 0),
        new TrafficElement(1, 1),
        new TrafficElement(2, 3),
        new TrafficElement(3, 1),
        new TrafficElement(4, 0),
        new TrafficElement(5, 2),
        new TrafficElement(6, 2), new TrafficElement(7, 2)
    );
    trafficVolumes(A, w);
  }
}
