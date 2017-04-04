// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using std::queue;
using std::make_unique;
using std::string;
using std::unordered_map;
using std::unordered_set;
using std::vector;

struct MatchResult;
unordered_map<string, unordered_set<string>> BuildGraph(
    const vector<MatchResult>&);
bool IsReachableDFS(const unordered_map<string, unordered_set<string>>&,
                    const string&, const string&, unordered_set<string>*);
bool IsReachableBFS(const unordered_map<string, unordered_set<string>>&,
                    const string&, const string&, unordered_set<string>*);

// @include
struct MatchResult {
  string winning_team, losing_team;
};

bool CanTeamABeatTeamB(const vector<MatchResult>& matches,
                       const string& team_a, const string& team_b) {
  return IsReachableDFS(BuildGraph(matches), team_a, team_b,
                        make_unique<unordered_set<string>>().get());
}

unordered_map<string, unordered_set<string>> BuildGraph(
    const vector<MatchResult>& matches) {
  unordered_map<string, unordered_set<string>> graph;
  for (const MatchResult& match : matches) {
    graph[match.winning_team].emplace(match.losing_team);
  }
  return graph;
}

bool IsReachableDFS(const unordered_map<string, unordered_set<string>>& graph,
                    const string& curr, const string& dest,
                    unordered_set<string>* visited_ptr) {
  unordered_set<string>& visited = *visited_ptr;
  if (curr == dest) {
    return true;
  } else if (visited.find(curr) != visited.end() ||
             graph.find(curr) == graph.end()) {
    return false;
  }
  visited.emplace(curr);
  const auto& team_list = graph.at(curr);
  return any_of(begin(team_list), end(team_list), [&](const string& team) {
    return IsReachableDFS(graph, team, dest, visited_ptr);
  });
}
// @exclude

bool IsReachableBFS(const unordered_map<string, unordered_set<string>>& graph,
                    const string& curr, const string& dest,
                    unordered_set<string>* visited_ptr) {
  unordered_set<string>& visited = *visited_ptr;
  queue<string> frontier;
  visited.emplace(curr);
  frontier.emplace(curr);
  while (!frontier.empty()) {
    const string just_reached = frontier.front();
    frontier.pop();
    if (just_reached == dest) {
      return true;
    } else if (graph.find(just_reached) != graph.end()) {
      for (const string& team : graph.at(just_reached)) {
        if (visited.find(team) == visited.end()) {
          visited.emplace(team);
          frontier.emplace(team);
        }
      }
    }
  }
  return false;
}

void Check(const vector<MatchResult>& matches, const string& team_a,
           const string& team_b) {
  assert(CanTeamABeatTeamB(matches, team_a, team_b) ==
         IsReachableBFS(BuildGraph(matches), team_a, team_b,
                        make_unique<unordered_set<string>>().get()));
}

int main(int argc, char* argv[]) {
  vector<MatchResult> matches = {{"Texas", "Cal"},      {"Cal", "Stanford"},
                                 {"Stanford", "Texas"}, {"Stanford", "MIT"},
                                 {"Stanford", "CIT"},   {"UCLA", "USC"}};
  Check(matches, "Texas", "MIT");
  Check(matches, "Cal", "UCLA");
  return 0;
}
