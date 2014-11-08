// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;

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
  public Team(int[] height) {
    members = new Player[height.length];
    for (int i = 0; i < height.length; ++i) {
      members[i] = new Player(height[i]);
    }
  }

  @Override
  public int compareTo(Team that) {
    Player[] thisSorted = sortHeightMembers();
    Player[] thatSorted = that.sortHeightMembers();
    for (int i = 0; i < thisSorted.length && i < thatSorted.length; ++i) {
      if (thisSorted[i].compareTo(thatSorted[i]) >= 0) {
        return 1;
      }
    }
    return -1;
  }

  private Player[] sortHeightMembers() {
    Player[] sortedMembers = members;
    Arrays.sort(sortedMembers);
    return sortedMembers;
  }

  private Player[] members;
}
// @exclude

class TeamPhoto1 {
  public static void main(String[] args) {
    int[] height = new int[]{1, 5, 4};
    Team t1 = new Team(height);
    height = new int[]{2, 3, 4};
    Team t2 = new Team(height);
    assert (t1.compareTo(t2) >= 0 && t2.compareTo(t1) >= 0);
    height = new int[]{0, 3, 2};
    Team t3 = new Team(height);
    assert (t3.compareTo(t1) < 0 && t1.compareTo(t3) >= 0
            && t3.compareTo(t2) < 0 && t1.compareTo(t2) >= 0);
  }
}
