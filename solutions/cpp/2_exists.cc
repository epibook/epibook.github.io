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
bool HasCycle(GraphVertex*);

// @include
struct GraphVertex {
  enum Color { white, gray, black } color = white;
  vector<GraphVertex*> edges;
};

bool IsDeadlocked(vector<GraphVertex>* G) {
  return any_of(begin(*G), end(*G), [](GraphVertex& vertex) {
    return vertex.color == GraphVertex::white && HasCycle(&vertex);
  });
}

bool HasCycle(GraphVertex* cur) {
  // Visiting a gray vertex means a cycle.
  if (cur->color == GraphVertex::gray) {
    return true;
  }

  cur->color = GraphVertex::gray;  // Marks current vertex as a gray one.
  // Traverse the neighbor vertices.
  for (GraphVertex*& next : cur->edges) {
    if (next->color != GraphVertex::black && HasCycle(next)) {
      return true;
    }
  }
  cur->color = GraphVertex::black;  // Marks current vertex as black.
  return false;
}
// @exclude

bool HasCycleExclusion(GraphVertex* cur) {
  if (cur->color == GraphVertex::black) {
    return true;
  }
  cur->color = GraphVertex::black;
  for (GraphVertex*& next : cur->edges) {
    if (HasCycleExclusion(next)) {
      return true;
    }
  }
  return false;
}

// O(n^2) check answer.
bool CheckAnswer(vector<GraphVertex>* G) {
  // marks all vertices as white.
  for (GraphVertex& n : *G) {
    n.color = GraphVertex::white;
  }

  for (GraphVertex& g : *G) {
    if (HasCycleExclusion(&g)) {
      return true;
    }
    // Reset color to white.
    for (GraphVertex& n : *G) {
      n.color = GraphVertex::white;
    }
  }
  return false;
}

void TestTwoNodesCycle() {
  vector<GraphVertex> G(2);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[0]);
  bool result = IsDeadlocked(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(result);
}

void TestDirectedCycle() {
  vector<GraphVertex> G(3);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[0]);
  bool result = IsDeadlocked(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(result);
}

void TestDirectedStarTree() {
  vector<GraphVertex> G(4);
  G[0].edges.emplace_back(&G[1]);
  G[0].edges.emplace_back(&G[2]);
  G[0].edges.emplace_back(&G[3]);
  bool result = IsDeadlocked(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(!result);
}

void TestDirectedLineTree() {
  vector<GraphVertex> G(4);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[3]);
  bool result = IsDeadlocked(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(!result);
  G[3].edges.emplace_back(&G[1]);
  result = IsDeadlocked(&G);
  assert(result);
}

void TestDirectedBinaryTree() {
  vector<GraphVertex> G(7);
  G[0].edges.emplace_back(&G[1]);
  G[0].edges.emplace_back(&G[2]);
  G[1].edges.emplace_back(&G[3]);
  G[1].edges.emplace_back(&G[4]);
  G[2].edges.emplace_back(&G[5]);
  G[2].edges.emplace_back(&G[6]);
  bool result = IsDeadlocked(&G);
  cout << boolalpha << result << endl;
  assert(CheckAnswer(&G) == result);
  assert(!result);
  G[4].edges.emplace_back(&G[6]);
  G[6].edges.emplace_back(&G[1]);
  result = IsDeadlocked(&G);
  assert(result);
}

void TestDirectedTwoSeparateCycles() {
  vector<GraphVertex> G(6);
  G[0].edges.emplace_back(&G[1]);
  G[1].edges.emplace_back(&G[2]);
  G[2].edges.emplace_back(&G[0]);
  G[3].edges.emplace_back(&G[4]);
  G[4].edges.emplace_back(&G[5]);
  G[5].edges.emplace_back(&G[3]);
  bool result = IsDeadlocked(&G);
  assert(result);
}

int main(int argc, char* argv[]) {
  TestTwoNodesCycle();
  TestDirectedCycle();
  TestDirectedStarTree();
  TestDirectedLineTree();
  TestDirectedBinaryTree();
  TestDirectedTwoSeparateCycles();
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

    bool result = IsDeadlocked(&G);
    cout << boolalpha << result << endl;
    assert(CheckAnswer(&G) == result);
  }
  return 0;
}
