// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <queue>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::queue;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
struct GraphVertex {
  int label;
  vector<GraphVertex*> edges;
};

GraphVertex* CloneGraph(GraphVertex* G) {
  if (!G) {
    return nullptr;
  }

  unordered_map<GraphVertex*, GraphVertex*> vertex_map;
  queue<GraphVertex*> q;
  q.emplace(G);
  vertex_map.emplace(G, new GraphVertex({G->label}));
  while (!q.empty()) {
    auto v = q.front();
    q.pop();
    for (GraphVertex* e : v->edges) {
      // Try to copy vertex e.
      if (vertex_map.find(e) == vertex_map.end()) {
        vertex_map.emplace(e, new GraphVertex({e->label}));
        q.emplace(e);
      }
      // Copy edge v->e.
      vertex_map[v]->edges.emplace_back(vertex_map[e]);
    }
  }
  return vertex_map[G];
}
// @exclude

vector<int> CopyLabels(const vector<GraphVertex*>& edges) {
  vector<int> labels;
  for (GraphVertex* e : edges) {
    labels.emplace_back(e->label);
  }
  return labels;
}

void CheckGraph(GraphVertex* node, const vector<GraphVertex>& G) {
  unordered_set<GraphVertex*> vertex_set;
  queue<GraphVertex*> q;
  q.emplace(node);
  vertex_set.emplace(node);
  while (!q.empty()) {
    auto vertex = q.front();
    q.pop();
    assert(vertex->label < G.size());
    vector<int> label1 = CopyLabels(vertex->edges),
                label2 = CopyLabels(G[vertex->label].edges);
    sort(label1.begin(), label1.end()), sort(label2.begin(), label2.end());
    assert(equal(label1.begin(), label1.end(), label2.begin(), label2.end()));
    for (GraphVertex* e : vertex->edges) {
      if (vertex_set.find(e) == vertex_set.end()) {
        vertex_set.emplace(e);
        q.emplace(e);
      }
    }
  }
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    vector<GraphVertex> G;
    int n;
    if (argc == 2) {
      n = stoi(argv[1]);
    } else {
      uniform_int_distribution<int> n_dis(2, 101);
      n = n_dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      G.emplace_back(GraphVertex{i});
    }
    uniform_int_distribution<int> dis(1, n * (n - 1) / 2);
    int m = dis(gen);
    vector<deque<bool>> is_edge_exist(n, deque<bool>(n, false));
    // Make the graph become connected.
    for (int i = 1; i < n; ++i) {
      G[i - 1].edges.emplace_back(&G[i]);
      G[i].edges.emplace_back(&G[i - 1]);
      is_edge_exist[i - 1][i] = is_edge_exist[i][i - 1] = true;
    }

    // Generate some edges randomly.
    m -= (n - 1);
    while (m-- > 0) {
      int a, b;
      uniform_int_distribution<int> dis(0, n - 1);
      do {
        a = dis(gen), b = dis(gen);
      } while (a == b || is_edge_exist[a][b] == true);
      is_edge_exist[a][b] = is_edge_exist[b][a] = true;
      G[a].edges.emplace_back(&G[b]);
      G[b].edges.emplace_back(&G[a]);
    }
    auto res = CloneGraph(&G[0]);
    CheckGraph(res, G);
  }
  return 0;
}
