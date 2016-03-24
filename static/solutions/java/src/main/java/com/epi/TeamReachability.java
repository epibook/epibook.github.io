// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.Set;

public class TeamReachability {
  // @include
  public static class MatchResult {
    public String winningTeam;
    public String losingTeam;

    public MatchResult(String winningTeam, String losingTeam) {
      this.winningTeam = winningTeam;
      this.losingTeam = losingTeam;
    }
  }

  public static boolean canTeamABeatTeamB(List<MatchResult> matches,
                                          String teamA, String teamB) {
    Set<String> visited = new HashSet<>();
    return isReachableDFS(buildGraph(matches), teamA, teamB, visited);
  }

  private static Map<String, Set<String>> buildGraph(
      List<MatchResult> matches) {
    Map<String, Set<String>> graph = new HashMap<>();
    for (MatchResult match : matches) {
      Set<String> edges = graph.get(match.winningTeam);
      if (edges == null) {
        edges = new HashSet<>();
        graph.put(match.winningTeam, edges);
      }
      edges.add(match.losingTeam);
    }
    return graph;
  }

  private static boolean isReachableDFS(Map<String, Set<String>> graph,
                                        String curr, String dest,
                                        Set<String> visited) {
    if (curr.equals(dest)) {
      return true;
    } else if (visited.contains(curr) || graph.get(curr) == null) {
      return false;
    }
    visited.add(curr);
    for (String team : graph.get(curr)) {
      if (isReachableDFS(graph, team, dest, visited)) {
        return true;
      }
    }
    return false;
  }
  // @exclude

  private static boolean isReachableBFS(Map<String, Set<String>> graph,
                                        String curr, String dest,
                                        Set<String> visited) {
    Queue<String> frontier = new LinkedList<>();
    visited.add(curr);
    frontier.add(curr);
    while (!frontier.isEmpty()) {
      String justReached = frontier.poll();
      if (justReached.equals(dest)) {
        return true;
      } else if (graph.get(justReached) != null) {
        for (String team : graph.get(justReached)) {
          if (!visited.contains(team)) {
            visited.add(team);
            frontier.add(team);
          }
        }
      }
    }
    return false;
  }

  private static void check(List<MatchResult> matches, String teamA,
                            String teamB) {
    Set<String> visited = new HashSet<>();
    assert(canTeamABeatTeamB(matches, teamA, teamB)
           == isReachableBFS(buildGraph(matches), teamA, teamB, visited));
  }

  public static void main(String[] args) {
    List<MatchResult> matches = Arrays.asList(
        new MatchResult("Texas", "Cal"), new MatchResult("Cal", "Stanford"),
        new MatchResult("Stanford", "Texas"),
        new MatchResult("Stanford", "MIT"), new MatchResult("Stanford", "CIT"),
        new MatchResult("UCLA", "USC"));
    check(matches, "Texas", "MIT");
    check(matches, "Cal", "UCLA");
  }
}
