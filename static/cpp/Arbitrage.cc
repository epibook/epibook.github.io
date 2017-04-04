// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

bool BellmanFord(const vector<vector<double>>& G, int source);

// @include
bool IsArbitrageExist(vector<vector<double>> G) {
  // Transforms each edge in G.
  for (vector<double>& edge_list : G) {
    for (double& edge : edge_list) {
      edge = -log10(edge);
    }
  }

  // Uses Bellman-Ford to find negative weight cycle.
  return BellmanFord(G, 0);
}

bool BellmanFord(const vector<vector<double>>& G, int source) {
  vector<double> dis_to_source(G.size(), numeric_limits<double>::max());
  dis_to_source[source] = 0;

  for (size_t times = 1; times < G.size(); ++times) {
    bool have_update = false;
    for (size_t i = 0; i < G.size(); ++i) {
      for (size_t j = 0; j < G[i].size(); ++j) {
        if (dis_to_source[i] != numeric_limits<double>::max() &&
            dis_to_source[j] > dis_to_source[i] + G[i][j]) {
          have_update = true;
          dis_to_source[j] = dis_to_source[i] + G[i][j];
        }
      }
    }

    // No update in this iteration means no negative cycle.
    if (have_update == false) {
      return false;
    }
  }

  // Detects cycle if there is any further update.
  for (size_t i = 0; i < G.size(); ++i) {
    for (size_t j = 0; j < G[i].size(); ++j) {
      if (dis_to_source[i] != numeric_limits<double>::max() &&
          dis_to_source[j] > dis_to_source[i] + G[i][j]) {
        return true;
      }
    }
  }
  return false;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> n_dis(1, 100);
    n = n_dis(gen);
  }
  vector<vector<double>> G(n, vector<double>(n));
  // Assume the input is a complete graph.
  for (size_t i = 0; i < G.size(); ++i) {
    G[i][i] = 1;
    for (size_t j = i + 1; j < G.size(); ++j) {
      uniform_real_distribution<double> dis(0, 1);
      G[i][j] = dis(gen);
      G[j][i] = 1.0 / G[i][j];
    }
  }
  bool res = IsArbitrageExist(G);
  cout << boolalpha << res << endl;
  vector<vector<double>> g(3, vector<double>(3));
  for (size_t i = 0; i < 3; ++i) {
    g[i][i] = 1;
  }
  g[0][1] = 2, g[1][0] = 0.5, g[0][2] = g[2][0] = 1, g[1][2] = 4,
  g[2][1] = 0.25;
  res = IsArbitrageExist(g);
  assert(res == true);
  cout << boolalpha << IsArbitrageExist(g) << endl;
  return 0;
}
