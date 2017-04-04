// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <deque>
#include <iostream>
#include <random>
#include <unordered_map>
#include <utility>
#include <vector>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::vector;
using std::uniform_int_distribution;
using std::unordered_map;

struct GraphVertex;
void DFS(const GraphVertex&);

// @include
struct Constraint {
  int a, b;
};

struct GraphVertex {
  int group = -1;  // represents the connected component it belongs.
  vector<GraphVertex*> edges;
};

bool are_constraints_satisfied(
    const vector<Constraint>& E,  // Equality constraints.
    const vector<Constraint>& I) {  // Inequality constraints.
  unordered_map<int, GraphVertex> G;
  // Build graph G according to E.
  for (const Constraint& e : E) {
    G[e.a].edges.emplace_back(&G[e.b]), G[e.b].edges.emplace_back(&G[e.a]);
  }

  // Assign group index for each connected component.
  int group_count = 0;
  for (auto& vertex : G) {
    if (vertex.second.group == -1) {  // is a unvisited vertex.
      vertex.second.group = group_count++;  // assigns a group index.
      DFS(vertex.second);
    }
  }

  // Examine each inequality constraint to see if there is a violation.
  for (const Constraint& i : I) {
    if (G[i.a].group != -1 && G[i.b].group != -1 &&
        G[i.a].group == G[i.b].group) {
      return false;
    }
  }
  return true;
}

void DFS(const GraphVertex& u) {
  for (GraphVertex* v : u.edges) {
    if (v->group == -1) {
      v->group = u.group;
      DFS(*v);
    }
  }
}
// @exclude

void small_test() {
  vector<Constraint> E, I;
  E.emplace_back(Constraint{0, 1});
  I.emplace_back(Constraint{2, 3});
  assert(are_constraints_satisfied(E, I));
  E.clear(), I.clear();
  // Example on the book.
  E.emplace_back(Constraint{1, 2});
  E.emplace_back(Constraint{2, 3});
  E.emplace_back(Constraint{3, 4});
  I.emplace_back(Constraint{1, 4});
  assert(!are_constraints_satisfied(E, I));
}

int main(int argc, char* argv[]) {
  small_test();
  default_random_engine gen((random_device())());
  int n, m, k;
  if (argc == 2) {
    n = atoi(argv[1]);
    uniform_int_distribution<int> dis1(1, n * (n - 1) / 2);
    m = dis1(gen);
    uniform_int_distribution<int> dis2(1, (n * (n - 1) / 2) - m);
    k = dis2(gen);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    m = atoi(argv[2]);
    uniform_int_distribution<int> dis(1, (n * (n - 1) / 2) - m);
    k = dis(gen);
  } else {
    uniform_int_distribution<int> dis1(2, 101);
    n = dis1(gen);
    uniform_int_distribution<int> dis2(1, n * (n - 1) / 2);
    m = dis2(gen);
    uniform_int_distribution<int> dis3(1, (n * (n - 1) / 2) - m);
    k = dis3(gen);
  }
  cout << "n = " << n << ", m = " << m << ", k = " << k << endl;
  vector<deque<bool>> have_edges(n, deque<bool>(n, false));
  vector<Constraint> E;
  uniform_int_distribution<int> dis(0, n - 1);
  for (int i = 0; i < m; ++i) {
    int a, b;
    do {
      a = dis(gen), b = dis(gen);
    } while (a == b || have_edges[a][b] == true);
    have_edges[a][b] = have_edges[b][a] = true;
    E.emplace_back(Constraint{a, b});
  }
  vector<Constraint> I;
  for (int i = 0; i < k; ++i) {
    int a, b;
    do {
      a = dis(gen), b = dis(gen);
    } while (a == b || have_edges[a][b] == true);
    have_edges[a][b] = have_edges[b][a] = true;
    I.emplace_back(Constraint{a, b});
  }
  /*
  cout << "equal constraint" << endl;
  for (const Constraint e : E) {
    cout << e.a << " " << e.b << endl;
  }
  cout << endl;
  cout << "not equal constraint" << endl;
  for (const Constraint i : I) {
    cout << i.a << " " << i.b << endl;
  }
  cout << endl;
  */
  bool res = are_constraints_satisfied(E, I);
  cout << boolalpha << res << endl;
  return 0;
}
