// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <iterator>
#include <numeric>
#include <queue>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::min;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<deque<bool>> find_feasible_job_assignment(const vector<int>& T,
                                                 const vector<int>& S) {
  int T_total = accumulate(T.cbegin(), T.cend(), 0),  // aggregated work units
      S_total = accumulate(S.cbegin(), S.cend(), 0,
                           [&T](const int &x, const int &y) {
                             return x + min(y, static_cast<int>(T.size()));
                           });  // tighter bound of server capacity.
  if (T_total > S_total || *max_element(T.cbegin(), T.cend()) > S.size()) {
    return {};  // too many jobs or one task needs too many servers.
  }

  struct Task {
    int task_id, load;
  };
  vector<Task> T_idx_data;
  for (int i = 0; i < T.size(); ++i) {
    T_idx_data.emplace_back(Task{i, T[i]});
  }
  struct Server {
    int server_id, capacity;
  };
  vector<Server> S_idx_data;
  for (int j = 0; j < S.size(); ++j) {
    S_idx_data.emplace_back(Server{j, S[j]});
  }

  sort(S_idx_data.begin(), S_idx_data.end(),
       [](const Server& a, const Server& b) {
         return a.capacity > b.capacity;
       });
  vector<deque<bool>> X(T.size(), deque<bool>(S.size(), false));
  for (int j = 0; j < S_idx_data.size(); ++j) {
    if (S_idx_data[j].capacity < T_idx_data.size()) {
      nth_element(
          T_idx_data.begin(), T_idx_data.begin() + S_idx_data[j].capacity,
          T_idx_data.end(),
          [](const Task& a, const Task& b) { return a.load > b.load; });
    }

    // Greedily assign jobs.
    int size =
        min(static_cast<int>(T_idx_data.size()), S_idx_data[j].capacity);
    for (int i = 0; i < size; ++i) {
      if (T_idx_data[i].load) {
        X[T_idx_data[i].task_id][S_idx_data[j].server_id] = true;
        --T_idx_data[i].load;
        --T_total;
      }
    }
  }
  if (T_total) {
    return {};  // still some jobs remain, no feasible assignment.
  }
  return X;
}
// @exclude

void check_answer(const vector<int>& T, const vector<int>& S,
                  const vector<deque<bool>>& result) {
  // Check row constraints.
  for (int i = 0; i < T.size(); ++i) {
    int sum = 0;
    for (int j = 0; j < S.size(); ++j) {
      sum += result[i][j];
    }
    assert(sum == T[i]);
  }

  // Check column constraints.
  for (int j = 0; j < S.size(); ++j) {
    int sum = 0;
    for (int i = 0; i < T.size(); ++i) {
      sum += result[i][j];
    }
    assert(sum <= S[j]);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, m;
    vector<int> T, S;
    if (argc == 3) {
      n = atoi(argv[1]), m = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> dis(1, 100);
      n = dis(gen), m = dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 5);
      T.emplace_back(dis(gen));
    }
    for (int i = 0; i < m; ++i) {
      uniform_int_distribution<int> dis(1, 10);
      S.emplace_back(dis(gen));
    }
    cout << "T = ";
    copy(T.cbegin(), T.cend(), ostream_iterator<int>(cout, " "));
    cout << endl;
    cout << "S = ";
    copy(S.cbegin(), S.cend(), ostream_iterator<int>(cout, " "));
    cout << endl;
    vector<deque<bool>> result = find_feasible_job_assignment(T, S);
    if (!result.empty()) {  // there is a feasible answer.
      cout << "found feasible assignment!" << endl;
      for (int i = 0; i < result.size(); ++i) {
        copy(result[i].cbegin(), result[i].cend(),
             ostream_iterator<int>(cout, " "));
        cout << endl;
      }
      check_answer(T, S, result);
    } else {
      // TODO(THL): find a way to verify there is no assignment.
      cout << "no feasible assignment" << endl;
    }
  }
  return 0;
}
