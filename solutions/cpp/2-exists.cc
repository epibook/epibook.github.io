// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <vector>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

struct GraphVertex;
bool DFS(GraphVertex* cur, const GraphVertex* pre);

// @include
struct GraphVertex {
  enum Color { white, gray, black } color;
  vector<GraphVertex*> edges;
  // @exclude
  GraphVertex() : color(white) {}
  // @include
};

bool IsGraphTwoExist(vector<GraphVertex>* G) {
  if (!G->empty()) {
    return DFS(&G->front(), nullptr);
  }
  return false;
}

bool DFS(GraphVertex* cur, const GraphVertex* pre) {
  // Visiting a gray vertex means a cycle.
  if (cur->color == GraphVertex::gray) {
    return true;
  }

  cur->color = GraphVertex::gray;  // marks current vertex as a gray one.
  // Traverse the neighbor vertices.
  for (GraphVertex*& next : cur->edges) {
    if (next != pre && next->color != GraphVertex::black) {
      if (DFS(next, cur)) {
        return true;
      }
    }
  }
  cur->color = GraphVertex::black;  // marks current vertex as black.
  return false;
}
// @exclude

void DFSExclusion(GraphVertex* cur, GraphVertex* a, GraphVertex* b) {
  cur->color = GraphVertex::black;
  for (GraphVertex*& next : cur->edges) {
    if (next->color == GraphVertex::white &&
        ((cur != a && cur != b) || (next != a && next != b))) {
      DFSExclusion(next, a, b);
    }
  }
}

// O(n^2) check answer.
bool CheckAnswer(vector<GraphVertex>* G) {
  // marks all vertices as white.
  for (GraphVertex& n : *G) {
    n.color = GraphVertex::white;
  }

  for (GraphVertex& g : *G) {
    for (GraphVertex*& t : g.edges) {
      DFSExclusion(&g, &g, t);
      int count = 0;
      for (GraphVertex& n : *G) {
        if (n.color == GraphVertex::black) {
          ++count;
          n.color = GraphVertex::white;
        }
      }
      if (count == G->size()) {
        return true;
      }
    }
  }
  return false;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 100; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(2, 2000);
      n = dis(gen);
    }
    vector<GraphVertex> G(n);
    uniform_int_distribution<int> dis(1, n * (n - 1) / 2);
    int m = dis(gen);
    vector<deque<bool>> is_edge_exist(n, deque<bool>(n, false));
    // Make the graph become connected.
    for (int i = 1; i < n; ++i) {
      G[i - 1].edges.emplace_back(&G[i]);
      G[i].edges.emplace_back(&G[i - 1]);
      is_edge_exist[i - 1][i] = is_edge_exist[i][i - 1] = true;
    }
    // Generate edges randomly.
    m -= (n - 1);
    while (m-- > 0) {
      int a, b;
      do {
        uniform_int_distribution<int> dis(0, n - 1);
        a = dis(gen), b = dis(gen);
      } while (a == b || is_edge_exist[a][b] == true);
      is_edge_exist[a][b] = is_edge_exist[b][a] = true;
      G[a].edges.emplace_back(&G[b]);
      G[b].edges.emplace_back(&G[a]);
    }

    /*
    for (int i = 0; i < G.size(); ++i) {
      cout << i << endl;
      for (int j = 0; j < G[i].edges.size(); ++j) {
        cout << ' ' << G[i].edges[j];
      }
      cout << endl;
    }
    */
    bool result = IsGraphTwoExist(&G);
    cout << boolalpha << result << endl;
    assert(CheckAnswer(&G) == result);
  }
  return 0;
}
