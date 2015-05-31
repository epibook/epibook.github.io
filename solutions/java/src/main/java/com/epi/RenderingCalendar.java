// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Event {
  int start, finish;

  public Event(int start, int finish) {
    this.start = start;
    this.finish = finish;
  }
}

class Endpoint implements Comparable<Endpoint> {
  // If times are equal, times corresponding to start come first.
  public int compareTo(Endpoint e) {
    if (time < e.time) {
      return -1;
    }
    if (time > e.time) {
      return 1;
    }
    if (isStart && !e.isStart) {
      return -1;
    }
    if (!isStart && e.isStart) {
      return 1;
    }
    return 0;
  }

  Endpoint(int t, boolean is) {
    time = t;
    isStart = is;
  }

  public int time;
  public boolean isStart;
}

class RenderingCalendar {
  // @include
  public static int findMaxSimultaneousEvents(Event[] A) {
    // Builds an array of all endpoints.
    List<Endpoint> E = new ArrayList<>();
    for (Event i : A) {
      E.add(new Endpoint(i.start, true));
      E.add(new Endpoint(i.finish, false));
    }
    // Sorts the endpoint array according to the time, breaking ties
    // by putting start times before end times.
    Collections.sort(E);

    // Track the number of simultaneous events, and record the maximum
    // number of simultaneous events.
    int maxNumSimultaneousEvents = 0, numSimultaneousEvents = 0;
    for (Endpoint e : E) {
      if (e.isStart) {
        ++numSimultaneousEvents;
        maxNumSimultaneousEvents =
            Math.max(numSimultaneousEvents, maxNumSimultaneousEvents);
      } else {
        --numSimultaneousEvents;
      }
    }
    return maxNumSimultaneousEvents;
  }
  // @exclude

  private static void simpleTest() {
    Event[] events = new Event[9];
    events[0] = new Event(1, 5);
    events[1] = new Event(2, 7);
    events[2] = new Event(4, 5);
    events[3] = new Event(6, 10);
    events[4] = new Event(8, 9);
    events[5] = new Event(9, 17);
    events[6] = new Event(11, 13);
    events[7] = new Event(12, 15);
    events[8] = new Event(14, 15);
    assert(3 == findMaxSimultaneousEvents(events));
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = gen.nextInt(100000) + 1;
    }
    Event[] A = new Event[n];
    for (int i = 0; i < n; ++i) {
      int start = gen.nextInt(99999);
      int finish = gen.nextInt(start + 10000) + start + 1;
      Event temp = new Event(start, finish);
      A[i] = temp;
    }
    int ans = findMaxSimultaneousEvents(A);
    System.out.println(ans);
  }
}
