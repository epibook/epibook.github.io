// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

#include "./Smallest_subarray_covering_set.h"
#include "./Smallest_subarray_covering_set_stream.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::istringstream;
using std::pair;
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

string rand_string(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// O(n^2) solution
int check_ans(const vector<string>& A, const vector<string>& Q) {
  unordered_set<string> dict;
  for (const string& s : Q) {
    dict.emplace(s);
  }

  pair<int, int> ans(0, A.size() - 1);
  for (int l = 0; l < A.size(); ++l) {
    unordered_map<string, int> count;
    for (int r = l; r < A.size() && r - l < ans.second - ans.first; ++r) {
      if (dict.find(A[r]) != dict.end()) {
        ++count[A[r]];
      }
      if (count.size() == Q.size()) {
        if (r - l < ans.second - ans.first) {
          ans = {l, r};
        }
        break;
      }
    }
    count.clear();
  }
  return ans.second - ans.first;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<string> A;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 10);
      A.emplace_back(rand_string(dis(gen)));
    }
    /*
    for (int i = 0; i < A.size(); ++i) {
      cout << A[i] << ' ';
    }
    cout << endl;
    */
    unordered_set<string> dict;
    string s;
    for (int i = 0; i < A.size(); ++i) {
      dict.emplace(A[i]);
      s += A[i];
      s += ' ';
    }
    uniform_int_distribution<int> dis(1, dict.size());
    int m = dis(gen);
    vector<string> Q;
    for (auto it = dict.cbegin(); it != dict.cend(); ++it) {
      Q.emplace_back(*it);
      if (--m == 0) {
        break;
      }
    }

    /*
    for (int i = 0; i < Q.size(); ++i) {
      cout << Q[i] << ' ';
    }
    cout << endl;
    */
    pair<int, int> res(FindSmallestSubarrayCoveringSubset(A, Q));
    cout << res.first << ", " << res.second << endl;
    dict.clear();
    for (int i = 0; i < Q.size(); ++i) {
      dict.emplace(Q[i]);
    }
    for (int i = res.first; i <= res.second; ++i) {
      if (dict.find(A[i]) != dict.end()) {
        dict.erase(A[i]);
      }
    }
    assert(dict.empty() == true);
    istringstream sin(s);
    pair<int, int> res2(FindSmallestSubarrayCoveringSubset(&sin, Q));
    cout << res2.first << ", " << res2.second << endl;
    dict.clear();
    for (int i = 0; i < Q.size(); ++i) {
      dict.emplace(Q[i]);
    }
    for (int i = res.first; i <= res.second; ++i) {
      if (dict.find(A[i]) != dict.end()) {
        dict.erase(A[i]);
      }
    }
    assert(dict.empty() == true);
    assert(res.second - res.first == res2.second - res2.first);
    assert(res.second - res.first == check_ans(A, Q));
  }
  return 0;
}
