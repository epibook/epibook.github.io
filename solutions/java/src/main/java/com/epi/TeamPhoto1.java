// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// @include
class Player implements Comparable<Player> {
  public Integer height;

  public Player(Integer h) {
    height = h;
  }

  @Override
  public int compareTo(Player that) {
    return height.compareTo(that.height);
  }
}

class Team implements Comparable<Team> {
  public Team(List<Integer> height) {
    for (Integer h : height) {
      members.add(new Player(h));
    }
  }

  @Override
  public int compareTo(Team that) {
    List<Player> thisSorted = sortHeightMembers();
    List<Player> thatSorted = that.sortHeightMembers();
    for (int i = 0; i < thisSorted.size() && i < thatSorted.size(); ++i) {
      if (thisSorted.get(i).compareTo(thatSorted.get(i)) >= 0) {
        return 1;
      }
    }
    return -1;
  }

  private List<Player> sortHeightMembers() {
    List<Player> sortedMembers = members;
    Collections.sort(sortedMembers);
    return sortedMembers;
  }

  private List<Player> members = new ArrayList<>();
}
// @exclude

class TeamPhoto1 {
  public static void main(String[] args) {
    List<Integer> height = new ArrayList<>(3);
    height.add(0, 1);
    height.add(1, 5);
    height.add(2, 4);
    Team t1 = new Team(height);
    height.set(0, 2);
    height.set(1, 3);
    height.set(2, 4);
    Team t2 = new Team(height);
    assert (t1.compareTo(t2) >= 0 && t2.compareTo(t1) >= 0);
    height.set(0, 0);
    height.set(1, 3);
    height.set(2, 2);
    Team t3 = new Team(height);
    assert (t3.compareTo(t1) < 0 && t1.compareTo(t3) >= 0
        && t3.compareTo(t2) < 0 && t1.compareTo(t2) >= 0);
  }
}
