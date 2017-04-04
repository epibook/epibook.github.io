// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

bool GreedyAssignment(const vector<int>& user_file_size, int server_num,
                      int limit, vector<int>* assign_res);

// @include
vector<int> DecideLoadBalancing(vector<int> user_file_size, int server_num) {
  // Uses binary search to find the assignment with minimized maximum load.
  int l = 0,
      r = accumulate(user_file_size.cbegin(), user_file_size.cend(), 0);
  vector<int> feasible_assignment;
  while (l <= r) {
    int m = l + ((r - l) / 2);
    vector<int> assign_res(server_num, 0);
    bool is_feasible =
        GreedyAssignment(user_file_size, server_num, m, &assign_res);
    if (is_feasible) {
      feasible_assignment = assign_res;
      r = m - 1;
    } else {
      l = m + 1;
    }
  }
  return feasible_assignment;
}

bool GreedyAssignment(const vector<int>& user_file_size, int server_num,
                      int limit, vector<int>* assign_res) {
  int server_idx = 0;
  for (int file : user_file_size) {
    while (server_idx < server_num &&
           file + (*assign_res)[server_idx] > limit) {
      ++server_idx;
    }

    if (server_idx >= server_num) {
      return false;
    } else {
      (*assign_res)[server_idx] += file;
    }
  }
  return true;
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int n, m;
  if (argc == 3) {
    n = atoi(argv[1]);
    m = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> n_dis(1, 50000);
    n = n_dis(gen);
    uniform_int_distribution<int> m_dis(1, n);
    m = m_dis(gen);
  }
  cout << n << " " << m << endl;
  vector<int> users;  // stores user i's data size.
  for (int i = 0; i < n; ++i) {
    uniform_int_distribution<int> dis(1, 1000);
    users.emplace_back(dis(gen));
  }
  for (int u : users) {
    cout << u << " ";
  }
  cout << endl;
  vector<int> res = DecideLoadBalancing(users, m);
  for (int file : res) {
    cout << file << ' ';
  }
  cout << endl;
  return 0;
}
