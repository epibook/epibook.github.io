// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <limits>
#include <stack>
#include <vector>

using std::max;
using std::stack;
using std::vector;

struct GraphVertex;
stack<GraphVertex*> BuildTopologicalOrdering(vector<GraphVertex>* G);
int FindLongestPath(stack<GraphVertex*>* vertex_order);
void DFS(GraphVertex* curr, stack<GraphVertex*>* vertex_order);

// @include
struct GraphVertex {
  vector<GraphVertex*> edges;
  // Set max_distance = 0 to indicate unvisited vertex.
  int max_distance = 0;
};

int FindLargestNumberTeams(vector<GraphVertex>* G) {
  stack<GraphVertex*> vertex_order(BuildTopologicalOrdering(G));
  return FindLongestPath(&vertex_order);
}

stack<GraphVertex*> BuildTopologicalOrdering(vector<GraphVertex>* G) {
  stack<GraphVertex*> vertex_order;
  for (GraphVertex& g : *G) {
    if (!g.max_distance) {
      DFS(&g, &vertex_order);
    }
  }
  return vertex_order;
}

int FindLongestPath(stack<GraphVertex*>* vertex_order) {
  int max_distance = 0;
  while (!vertex_order->empty()) {
    GraphVertex* u = vertex_order->top();
    max_distance = max(max_distance, u->max_distance);
    for (GraphVertex*& v : u->edges) {
      v->max_distance = max(v->max_distance, u->max_distance + 1);
    }
    vertex_order->pop();
  }
  return max_distance;
}

void DFS(GraphVertex* curr, stack<GraphVertex*>* vertex_order) {
  curr->max_distance = 1;
  for (GraphVertex* vertex : curr->edges) {
    if (!vertex->max_distance) {
      DFS(vertex, vertex_order);
    }
  }
  vertex_order->emplace(curr);
}
// @exclude

int main(int argc, char* argv[]) {
  vector<GraphVertex> G(3);
  G[0].edges.emplace_back(&G[2]);
  G[1].edges.emplace_back(&G[2]);
  assert(2 == FindLargestNumberTeams(&G));
  return 0;
}
