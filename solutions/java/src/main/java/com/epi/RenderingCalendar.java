package com.epi;

/*
    @slug
    render-a-calender

    @title
    Render a Calendar

    @problem
    Write a program that takes a set of events, and determines the maximum
   number of
    events that take place concurrently.

    @hint
    Focus on endpoints.

 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RenderingCalendar {
  // @include
  // @judge-include-display
  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }

    // @exclude
    @Override
    public String toString() {
      return "[" + start + "," + finish + "]";
    }
    // @include
  }
  // @judge-exclude-display

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

  // @judge-include-display
  public static int findMaxSimultaneousEvents(List<Event> A) {
    // @judge-exclude-display
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
    // @judge-include-display
  }
  // @judge-exclude-display
  // @exclude

  private static void check(int expected, List<Event> events) {
    int got = findMaxSimultaneousEvents(events);
    if (expected != got) {
      System.err.println("Failed on input " + events);
      System.err.println("Expected " + expected);
      System.err.println("Got " + got);
      System.exit(-1);
    }
  }

  private static void simpleTest() {
    List<Event> events = Arrays.asList(
        new Event(1, 5), new Event(2, 7), new Event(4, 5), new Event(6, 10),
        new Event(8, 9), new Event(9, 17), new Event(11, 13), new Event(12, 15),
        new Event(14, 15));
    check(3, events);

    check(1, Arrays.asList(new Event(1, 2), new Event(3, 4)));
    check(2, Arrays.asList(new Event(1, 3), new Event(3, 4)));
    check(2, Arrays.asList(new Event(1, 3), new Event(0, 4)));
    check(2, Arrays.asList(new Event(1, 3), new Event(0, 4), new Event(-1, 0)));
    check(2, Arrays.asList(new Event(1, 1), new Event(0, 0), new Event(0, 0)));
    int N = 1000000;
    List<Event> big1 = new ArrayList<>(N);
    List<Event> big2 = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      big1.add(new Event(i, i));
      big2.add(new Event(-i, i));
    }
    check(1, big1);
    check(N, big2);
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
