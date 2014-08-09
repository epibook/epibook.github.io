// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class TInterval {
  int start, finish;
}

class TEndpoint implements Comparable<TEndpoint> {
  public int compareTo(TEndpoint e) {
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
    // return time != e.time ? time < e.time : (isStart && !e.isStart);
  }

  TEndpoint(int t, boolean is) {
    time = t;
    isStart = is;
  }

  public int time;
  public boolean isStart;
}

class RenderingCalendar {
  // @include
  public static int findMaxConcurrentEvents(List<TInterval> A) {
    // Builds the TEndpoint array.
    List<TEndpoint> E = new ArrayList<>();
    for (TInterval i : A) {
      E.add(new TEndpoint(i.start, true));
      E.add(new TEndpoint(i.finish, false));
    }
    // Sorts the TEndpoint array according to the time.
    Collections.sort(E);

    // Finds the maximum number of events overlapped.
    int maxCount = 0, count = 0;
    for (TEndpoint e : E) {
      if (e.isStart) {
        maxCount = Math.max(++count, maxCount);
      } else {
        --count;
      }
    }
    return maxCount;
  }
  // @exclude

  public static void main(String[] args) {
    Random gen = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = gen.nextInt(100000) + 1;
    }
    List<TInterval> A = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      TInterval temp = new TInterval();
      temp.start = gen.nextInt(99999);
      temp.finish = gen.nextInt(temp.start + 10000) + temp.start + 1;
      A.add(temp);
    }
    int ans = findMaxConcurrentEvents(A);
    System.out.println(ans);
  }
}
