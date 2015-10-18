// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RenderingCalendar {
  // @include
  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint implements Comparable<Endpoint> {
    public int time;
    public boolean isStart;

    public int compareTo(Endpoint e) {
      if (time != e.time) {
        return Integer.compare(time, e.time);
      }
      // If times are equal, an endpoint that starts an interval comes first.
      return isStart && !e.isStart ? -1 : !isStart && e.isStart ? 1 : 0;
    }

    Endpoint(int t, boolean is) {
      time = t;
      isStart = is;
    }
  }

  public static int findMaxSimultaneousEvents(List<Event> A) {
    // Builds an array of all endpoints.
    List<Endpoint> E = new ArrayList<>();
    for (Event event : A) {
      E.add(new Endpoint(event.start, true));
      E.add(new Endpoint(event.finish, false));
    }
    // Sorts the endpoint array according to the time, breaking ties
    // by putting start times before end times.
    Collections.sort(E);

    // Track the number of simultaneous events, and record the maximum
    // number of simultaneous events.
    int maxNumSimultaneousEvents = 0, numSimultaneousEvents = 0;
    for (Endpoint endpoint : E) {
      if (endpoint.isStart) {
        ++numSimultaneousEvents;
        maxNumSimultaneousEvents
            = Math.max(numSimultaneousEvents, maxNumSimultaneousEvents);
      } else {
        --numSimultaneousEvents;
      }
    }
    return maxNumSimultaneousEvents;
  }
  // @exclude

  private static void simpleTest() {
    List<Event> events = Arrays.asList(
        new Event(1, 5), new Event(2, 7), new Event(4, 5), new Event(6, 10),
        new Event(8, 9), new Event(9, 17), new Event(11, 13), new Event(12, 15),
        new Event(14, 15));
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
    List<Event> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      int start = gen.nextInt(99999);
      int finish = gen.nextInt(start + 10000) + start + 1;
      A.add(new Event(start, finish));
    }
    int ans = findMaxSimultaneousEvents(A);
    System.out.println(ans);
  }
}
