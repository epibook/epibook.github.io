// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;

// @include
class Player implements Comparable<Player> {
  public Integer height;

  public Player(Integer h) { height = h; }

  @Override
  public int compareTo(Player that) {
    return height.compareTo(that.height);
  }
}

class Team {
  public Team(int[] height) {
    players = new Player[height.length];
    for (int i = 0; i < height.length; ++i) {
      players[i] = new Player(height[i]);
    }
  }

  // Checks if A can be placed in front of B.
  public static boolean validPlacementExists(Team A, Team B) {
    Player[] ASorted = A.sortPlayersByHeight();
    Player[] BSorted = B.sortPlayersByHeight();
    for (int i = 0; i < ASorted.length && i < BSorted.length; ++i) {
      if (ASorted[i].compareTo(BSorted[i]) >= 0) {
        return false;
      }
    }
    return true;
  }

  private Player[] sortPlayersByHeight() {
    Player[] sortedPlayers = players;
    Arrays.sort(sortedPlayers);
    return sortedPlayers;
  }

  private Player[] players;
}
// @exclude

class TeamPhoto1 {
  public static void main(String[] args) {
    int[] height = new int[] {1, 5, 4};
    Team t1 = new Team(height);
    height = new int[] {2, 3, 4};
    Team t2 = new Team(height);
    assert(!Team.validPlacementExists(t1, t2) &&
           !Team.validPlacementExists(t2, t1));
    height = new int[] {0, 3, 2};
    Team t3 = new Team(height);
    assert(
        Team.validPlacementExists(t3, t1) && !Team.validPlacementExists(t1, t3) &&
        Team.validPlacementExists(t3, t2) && !Team.validPlacementExists(t1, t2));
  }
}
