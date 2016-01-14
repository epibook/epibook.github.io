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
bool HasCycle(GraphVertex*, const GraphVertex*);

// @include
struct GraphVertex {
  enum Color { white, gray, black } color;
  vector<GraphVertex*> edges;
  // @exclude
  GraphVertex() : color(white) {}
  // @include
};

bool IsGraphMinimallyConnected(vector<GraphVertex>* G) {
  return G->empty() || !HasCycle(&G->front(), nullptr);
}

bool HasCycle(GraphVertex* cur, const GraphVertex* pre) {
  // Visiting a gray vertex means a cycle.
  if (cur->color == GraphVertex::gray) {
    return true;
  }

  cur->color = GraphVertex::gray;  // marks current vertex as a gray one.
  // Traverse the neighbor vertices.
  for (GraphVertex*& next : cur->edges) {
    if (next != pre && next->color != GraphVertex::black) {
      if (HasCycle(next, cur)) {
        return true;
      }
    }
  }
  cur->color = GraphVertex::black;  // marks current vertex as black.
  return false;
}
// @exclude

void HasCycleExclusion(GraphVertex* cur, GraphVertex* a, GraphVertex* b) {
  cur->color = GraphVertex::black;
  for (GraphVertex*& next : cur->edges) {
    if (next->color == GraphVertex::white &&
        ((cur != a && cur != b) || (next != a && next != b))) {
      HasCycleExclusion(next, a, b);
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
      HasCycleExclusion(&g, &g, t);
      int count = 0;
      for (GraphVertex& n : *G) {
        if (n.color == GraphVertex::black) {
          ++count;
          n.color = GraphVertex::white;
        }
      }
      if (count == G->size()) {
        return false;
      }
    }
  }
  return true;
}

void TestUndirectedCycle() {
  vector<GraphVertex> G(3);
  G[0].edges.emplace_back(&G[2]);
  G[1].edges.emplace_back(&G[0]);
  G[2].edges.emplace_back(&G[1]);
  G[2].edges.emplace_back(&G[0]);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[2]);
  bool result = IsGraphMinimallyConnected(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
}

void TestUndirectedStarTree() {
  vector<GraphVertex> G(4);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[0]);
  G[0].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[0]);
  G[0].edges.emplace_back(&G[3]);
  G[3].edges.emplace_back(&G[0]);
  bool result = IsGraphMinimallyConnected(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(true == result);
}

void TestUndirectedLineTree() {
  vector<GraphVertex> G(4);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[0]);
  G[1].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[1]);
  G[2].edges.emplace_back(&G[3]);
  G[3].edges.emplace_back(&G[2]);
  bool result = IsGraphMinimallyConnected(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(true == result);
  G[1].edges.emplace_back(&G[3]);
  G[3].edges.emplace_back(&G[1]);
  result = IsGraphMinimallyConnected(&G);
  assert(false == result);
}

void TestUndirectedBinaryTree() {
  vector<GraphVertex> G(7);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[0]);
  G[0].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[0]);
  G[1].edges.emplace_back(&G[3]);
  G[3].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[4]);
  G[4].edges.emplace_back(&G[1]);
  G[2].edges.emplace_back(&G[5]);
  G[5].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[6]);
  G[6].edges.emplace_back(&G[2]);
  bool result = IsGraphMinimallyConnected(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(true == result);
  G[4].edges.emplace_back(&G[6]);
  G[6].edges.emplace_back(&G[4]);
  result = IsGraphMinimallyConnected(&G);
  assert(false == result);
}

void TestUndirectedTwoSeparateCycles() {
  vector<GraphVertex> G(6);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[0]);
  G[1].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[1]);
  G[2].edges.emplace_back(&G[0]);
  G[0].edges.emplace_back(&G[2]);
  G[3].edges.emplace_back(&G[4]);
  G[4].edges.emplace_back(&G[3]);
  G[4].edges.emplace_back(&G[5]);
  G[5].edges.emplace_back(&G[4]);
  G[5].edges.emplace_back(&G[3]);
  G[3].edges.emplace_back(&G[5]);
  bool result = IsGraphMinimallyConnected(&G);
  // since we just check for a cycle, result is false.
  // this is ok, because we assume input is connected.
  assert(false == result);
}

int main(int argc, char* argv[]) {
  TestUndirectedCycle();
  TestUndirectedStarTree();
  TestUndirectedLineTree();
  TestUndirectedBinaryTree();
  TestUndirectedTwoSeparateCycles();
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

    bool result = IsGraphMinimallyConnected(&G);
    cout << boolalpha << result << endl;
    assert(CheckAnswer(&G) == result);
  }
  return 0;
}
