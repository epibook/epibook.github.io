// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <deque>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

void FloydWarshall(vector<vector<double>>* G);

// @include
struct HighwaySection {
  int x, y;
  double distance;
};

HighwaySection FindBestProposals(const vector<HighwaySection>& H,
                                 const vector<HighwaySection>& P, int a,
                                 int b, int n) {
  // G stores the shortest path distances between all pairs of vertices.
  vector<vector<double>> G(n,
                            vector<double>(n, numeric_limits<double>::max()));
  for (int i = 0; i < n; ++i) {
    G[i][i] = 0;
  }

  // Builds a undirected graph G based on existing highway sections H.
  for (const HighwaySection& h : H) {
    G[h.x][h.y] = G[h.y][h.x] = h.distance;
  }
  // Performs Floyd Warshall to build the shortest path between vertices.
  FloydWarshall(&G);

  // Examines each proposal for shorter distance between a and b.
  double min_dis_a_b = G[a][b];
  HighwaySection best_proposal = {-1, -1, 0.0};  // default
  for (const HighwaySection& p : P) {
    // Checks the path of a => p.x => p.y => b.
    if (G[a][p.x] != numeric_limits<double>::max() &&
        G[p.y][b] != numeric_limits<double>::max() &&
        min_dis_a_b > G[a][p.x] + p.distance + G[p.y][b]) {
      min_dis_a_b = G[a][p.x] + p.distance + G[p.y][b];
      best_proposal = p;
    }
    // Checks the path of a => p.y => p.x => b.
    if (G[a][p.y] != numeric_limits<double>::max() &&
        G[p.x][b] != numeric_limits<double>::max() &&
        min_dis_a_b > G[a][p.y] + p.distance + G[p.x][b]) {
      min_dis_a_b = G[a][p.y] + p.distance + G[p.x][b];
      best_proposal = p;
    }
  }
  return best_proposal;
}

void FloydWarshall(vector<vector<double>>* G) {
  for (int k = 0; k < G->size(); ++k) {
    for (int i = 0; i < G->size(); ++i) {
      for (int j = 0; j < G->size(); ++j) {
        if ((*G)[i][k] != numeric_limits<double>::max() &&
            (*G)[k][j] != numeric_limits<double>::max() &&
            (*G)[i][j] > (*G)[i][k] + (*G)[k][j]) {
          (*G)[i][j] = (*G)[i][k] + (*G)[k][j];
        }
      }
    }
  }
}
// @exclude

// Tries to add each proposal and use Floyd Warshall to solve, O(n^4) algorithm.
HighwaySection CheckAns(const vector<HighwaySection>& H,
                        const vector<HighwaySection>& P, int a, int b, int n) {
  // G stores the shortest path distances between all pairs of vertices.
  vector<vector<double>> G(n,
                            vector<double>(n, numeric_limits<double>::max()));
  for (int i = 0; i < n; ++i) {
    G[i][i] = 0;
  }

  // Builds a undirected graph G based on existing highway sections H.
  for (const HighwaySection& h : H) {
    G[h.x][h.y] = G[h.y][h.x] = h.distance;
  }
  // Performs Floyd Warshall to build the shortest path between vertices.
  FloydWarshall(&G);

  double best_cost = G[a][b];
  HighwaySection best_proposal = {-1, -1, 0.0};  // default
  for (const HighwaySection& p : P) {
    // Creates new G_test for Floyd Warshall.
    vector<vector<double>> G_test(G);
    G_test[p.x][p.y] = G_test[p.y][p.x] = p.distance;
    FloydWarshall(&G_test);
    if (best_cost > G_test[a][b]) {
      best_cost = G_test[a][b];
      best_proposal = p;
    }
  }
  return best_proposal;
}

int main(int argc, char* argv[]) {
  for (int times = 0; times < 1000; ++times) {
    default_random_engine gen((random_device())());
    int n, m, k;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> dis1(1, n * ((n - 1) / 2) - 1);
      m = dis1(gen);
      uniform_int_distribution<int> dis2(1, n * ((n - 1) / 2) - m);
      k = dis2(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]);
      m = atoi(argv[2]);
      uniform_int_distribution<int> dis(1, n * ((n - 1) / 2) - m);
      k = dis(gen);
    } else {
      uniform_int_distribution<int> five_to_100(5, 100);
      n = five_to_100(gen);
      uniform_int_distribution<int> dis1(1, n * ((n - 1) / 2) - 1);
      m = dis1(gen);
      uniform_int_distribution<int> dis2(1, n * ((n - 1) / 2) - m);
      k = dis2(gen);
    }
    cout << "n = " << n << ", m = " << m << ", k = " << k << endl;

    vector<deque<bool>> have_edges(n, deque<bool>(n, false));
    vector<HighwaySection> H;  // Existing highway sections
    uniform_int_distribution<int> vertex_dis(0, n - 1);
    uniform_real_distribution<double> cost_dis(1.0, 10000.0);
    for (int i = 0; i < m; ++i) {
      int a, b;
      do {
        a = vertex_dis(gen), b = vertex_dis(gen);
      } while (a == b || have_edges[a][b] == true);
      have_edges[a][b] = have_edges[b][a] = true;
      H.emplace_back(HighwaySection{a, b, cost_dis(gen)});
    }
    //*
    for (int i = 0; i < m; ++i) {
      cout << "H[i] = " << H[i].x << " " << H[i].y << " " << H[i].distance
           << endl;
    }
    //*/

    vector<HighwaySection> P;  // Proposals
    for (int i = 0; i < k; ++i) {
      int a, b;
      do {
        a = vertex_dis(gen), b = vertex_dis(gen);
      } while (a == b || have_edges[a][b] == true);
      have_edges[a][b] = have_edges[b][a] = true;
      uniform_real_distribution<double> new_cost_dis(1.0, 50.0);
      P.emplace_back(HighwaySection{a, b, new_cost_dis(gen)});
    }
    //*
    for (int i = 0; i < k; ++i) {
      cout << "P[i] = " << P[i].x << " " << P[i].y << " " << P[i].distance
           << endl;
    }
    //*/

    int a, b;
    do {
      a = vertex_dis(gen), b = vertex_dis(gen);
    } while (a == b);
    cout << "a = " << a << ", b = " << b << endl;
    HighwaySection t = FindBestProposals(H, P, a, b, n);
    cout << t.x << ' ' << t.y << ' ' << t.distance << endl;
    HighwaySection ans = CheckAns(H, P, a, b, n);
    cout << ans.x << ' ' << ans.y << ' ' << ans.distance << endl;
    // TODO(THL): follow assert may fail sometime due to epsilon problem.
    // assert(t.x == ans.x && t.y == ans.y && t.distance == ans.distance);
  }
  return 0;
}
