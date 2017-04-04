// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

struct GraphVertex;
void DFS(GraphVertex* cur, int time, vector<GraphVertex*>* contacts);

// @include
struct GraphVertex {
  int visit_time = -1;
  vector<GraphVertex *> edges, extended_contacts;
};

void transitive_closure(vector<GraphVertex>* G) {
  // Build extended contacts for each vertex.
  for (int i = 0; i < G->size(); ++i) {
    (*G)[i].visit_time = i;
    DFS(&(*G)[i], i, &(*G)[i].extended_contacts);
  }
}

void DFS(GraphVertex* cur, int time, vector<GraphVertex*>* contacts) {
  for (GraphVertex* next : cur->edges) {
    if (next->visit_time != time) {
      next->visit_time = time;
      contacts->emplace_back(next);
      DFS(next, time, contacts);
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  vector<GraphVertex> G;
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    n = dis(gen);
  }
  fill_n(back_inserter(G), n, GraphVertex());
  cout << G.size() << endl;
  uniform_int_distribution<int> dis(1, n * (n - 1) / 2);
  int m = dis(gen);
  vector<deque<bool>> is_edge_exist(n, deque<bool>(n, false));
  /*
  // Make the graph become connected
  for (int i = 1; i < n; ++i) {
  G[i - 1].edges.emplace_back(i);
  G[i].edges.emplace_back(i - 1);
  is_edge_exist[i - 1][i] = is_edge_exist[i][i - 1] = true;
  }
   */

  // Generate edges randomly
  while (m-- > 0) {
    uniform_int_distribution<int> dis(0, n - 1);
    int a, b;
    do {
      a = dis(gen), b = dis(gen);
    } while (a == b || is_edge_exist[a][b] == true);
    is_edge_exist[a][b] = is_edge_exist[b][a] = true;
    G[a].edges.emplace_back(&G[b]);
    G[b].edges.emplace_back(&G[a]);
  }

  transitive_closure(&G);
  /*
  for (int i = 0; i < G.size(); ++i) {
    cout << i << endl << '\t';
    for (GraphVertex* &e : G[i].extended_contacts) {
      cout << e << ' ';
    }
    cout << endl;
  }
  for (int i = 0; i < G.size(); ++i) {
    cout << i << endl;
    for (int j = 0; j < G[i].edges.size(); ++j) {
      cout << ' ' << G[i].edges[j];
    }
    cout << endl;
  }
  */
  return 0;
}
