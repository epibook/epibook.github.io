// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
using std::random_device;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// O(n^2) solution
int CheckAns(const vector<string>& A, const vector<string>& Q) {
  unordered_set<string> dict;
  for (const string& s : Q) {
    dict.emplace(s);
  }

  Subarray ans = Subarray{0, static_cast<int>(A.size() - 1)};
  for (int l = 0; l < A.size(); ++l) {
    unordered_map<string, int> count;
    for (int r = l; r < A.size() && r - l < ans.end - ans.start; ++r) {
      if (dict.find(A[r]) != dict.end()) {
        ++count[A[r]];
      }
      if (count.size() == Q.size()) {
        if (r - l < ans.end - ans.start) {
          ans = {l, r};
        }
        break;
      }
    }
    count.clear();
  }
  return ans.end - ans.start;
}

void SimpleTestCase(const vector<string>& A, const vector<string>& dict,
                    int start, int finish) {
  auto res = FindSmallestSubarrayCoveringSet(A, {dict.begin(), dict.end()});
  cout << "res = " << res.start << " " << res.end << endl;
  assert(res.start == start && res.end == finish);
  string s;
  for (int i = 0; i < A.size(); ++i) {
    s += A[i];
    s += ' ';
  }
  istringstream sin(s);
  Subarray res2(FindSmallestSubarrayCoveringSubset(&sin, dict));
  cout << "res2 = " << res2.start << " " << res2.end << endl;
  assert(res2.start == start && res2.end == finish);
}

void SimpleTest() {
  vector<string> A = {"a", "b", "c", "b", "a", "d", "c",
                      "a", "e", "a", "a", "b", "e"};
  vector<string> dict = {"b", "c", "e"};
  SimpleTestCase(A, dict, 3, 8);
  dict = {"a", "c"};
  SimpleTestCase(A, dict, 6, 7);
  A = {"a", "b"};
  dict = {"a", "b"};
  SimpleTestCase(A, dict, 0, 1);
  A = {"a", "b"};
  dict = {"b", "a"};
  SimpleTestCase(A, dict, 0, 1);
}

int main(int argc, char* argv[]) {
  SimpleTest();
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
      A.emplace_back(RandString(dis(gen)));
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
    unordered_set<string> input(Q.cbegin(), Q.cend());
    Subarray res(FindSmallestSubarrayCoveringSet(A, input));
    cout << res.start << ", " << res.end << endl;
    dict.clear();
    for (int i = 0; i < Q.size(); ++i) {
      dict.emplace(Q[i]);
    }
    for (int i = res.start; i <= res.end; ++i) {
      if (dict.find(A[i]) != dict.end()) {
        dict.erase(A[i]);
      }
    }
    assert(dict.empty() == true);
    istringstream sin(s);
    Subarray res2(FindSmallestSubarrayCoveringSubset(&sin, Q));
    cout << res2.start << ", " << res2.end << endl;
    dict.clear();
    for (int i = 0; i < Q.size(); ++i) {
      dict.emplace(Q[i]);
    }
    for (int i = res.start; i <= res.end; ++i) {
      if (dict.find(A[i]) != dict.end()) {
        dict.erase(A[i]);
      }
    }
    assert(dict.empty() == true);
    assert(res.end - res.start == res2.end - res2.start);
    assert(res.end - res.start == CheckAns(A, Q));
  }
  return 0;
}
