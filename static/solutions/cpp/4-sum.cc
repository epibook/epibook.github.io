// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::move;
using std::pair;
using std::random_device;
using std::stoi;
using std::string;
using std::to_string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

string join_vector(const vector<int>&);

// @include
vector<vector<int>> four_sum(vector<int> A, int target) {
  sort(A.begin(), A.end());
  unordered_map<int, vector<vector<size_t>>> T;
  for (size_t i = 0; i < A.size(); ++i) {
    for (size_t j = i + 1; j < A.size(); ++j) {
      bool have_duplicate = false;
      for (const vector<size_t>& vec : T[A[i] + A[j]]) {
        if (A[vec.front()] == A[i] || A[vec.back()] == A[j]) {
          have_duplicate = true;
          break;
        }
      }
      if (!have_duplicate) {
        vector<size_t> new_vec = {i, j};
        T[A[i] + A[j]].emplace_back(move(new_vec));
      }
    }
  }

  unordered_set<string> answers;
  vector<vector<int>> res;
  for (size_t i = 2; i < A.size(); ++i) {
    for (size_t j = i + 1; j < A.size(); ++j) {
      auto it = T.find(target - A[i] - A[j]);
      if (it != T.end()) {
        for (const vector<size_t>& vec : it->second) {
          if (i > vec.back()) {
            vector<int> candidate = {A[vec.front()], A[vec.back()], A[i],
                                     A[j]};
            if (answers.emplace(join_vector(candidate)).second) {
              res.emplace_back(move(candidate));
            }
          }
        }
      }
    }
  }
  return res;
}

string join_vector(const vector<int>& A) {
  string ret;
  for (int a : A) {
    ret += to_string(a);
    ret += ' ';
  }
  return ret;
}
// @exclude

void check_ans(int target, const vector<vector<int>>& vecs) {
  for (const vector<int>& vec : vecs) {
    int sum = 0;
    for (int v : vec) {
      sum += v;
    }
    assert(sum == target);
  }
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> n_dis(1, 100);
    n = n_dis(gen);
  }
  uniform_int_distribution<int> A_dis(-1000, 1000);
  vector<int> A;
  generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
  uniform_int_distribution<int> target_dis(-4000, 4000);
  int target = target_dis(gen);
  auto vecs = four_sum(A, target);
  check_ans(target, vecs);
  A = {-2, -1, 0, 0, 1, 2};
  vecs = four_sum(A, 0);
  for (const vector<int>& vec : vecs) {
    for (int a : vec) {
      cout << a << " ";
    }
    cout << endl;
  }
  check_ans(0, vecs);
  return 0;
}
