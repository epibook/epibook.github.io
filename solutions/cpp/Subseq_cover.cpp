// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <limits>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::min;
using std::numeric_limits;
using std::pair;
using std::random_device;
using std::string;
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

// @include
pair<int, int> FindSmallestSequentiallyCoveringSubset(
    const vector<string>& A, const vector<string>& Q) {
  unordered_map<string, int> K;  // Stores the order of each Q[i].
  vector<int> L(Q.size(), -1), D(Q.size(), numeric_limits<int>::max());

  // Initializes K.
  for (int i = 0; i < Q.size(); ++i) {
    K.emplace(Q[i], i);
  }

  pair<int, int> res(-1, A.size());  // Default value.
  for (int i = 0; i < A.size(); ++i) {
    auto it = K.find(A[i]);
    if (it != K.cend()) {
      if (it->second == 0) {  // First one, no predecessor.
        D[0] = 1;             // Base condition.
      } else if (D[it->second - 1] != numeric_limits<int>::max()) {
        D[it->second] = i - L[it->second - 1] + D[it->second - 1];
      }
      L[it->second] = i;

      if (it->second == Q.size() - 1 &&
          D.back() < res.second - res.first + 1) {
        res = {i - D.back() + 1, i};
      }
    }
  }
  return res;
}
// @exclude

void small_test() {
  vector<string> A3 = {"0", "1", "2", "3",  "4",  "5",  "6", "7", "8", "9",
                       "2", "4", "6", "10", "10", "10", "3", "2", "1", "0"};
  vector<string> subseq4 = {"0", "2", "9", "4", "6"};
  pair<int, int> rr = FindSmallestSequentiallyCoveringSubset(A3, subseq4);
  assert(rr.first == 0 && rr.second == 12);
}

int main(int argc, char* argv[]) {
  small_test();
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
    uniform_int_distribution<int> dis(1, 5);
    generate_n(back_inserter(A), n, [&] { return RandString(dis(gen)); });
    unordered_set<string> dict;
    copy(A.begin(), A.end(), inserter(dict, dict.end()));
    cout << "A = ";
    copy(A.begin(), A.end(), ostream_iterator<string>(cout, ","));
    cout << endl;
    uniform_int_distribution<int> m_dis(1,
                                        min(static_cast<int>(dict.size()), 10));
    int m = m_dis(gen);
    vector<string> Q;
    auto it = dict.begin();
    generate_n(back_inserter(Q), m, [&] { return *it++; });
    cout << "Q = ";
    copy(Q.begin(), Q.end(), ostream_iterator<string>(cout, ","));
    cout << endl;

    pair<int, int> res(FindSmallestSequentiallyCoveringSubset(A, Q));
    cout << res.first << ", " << res.second << endl;
    if (res.first != -1 && res.second != Q.size()) {
      if (res.first != res.second)
        cout << res.first << ", " << res.second << endl;
      dict.clear();
      copy(Q.begin(), Q.end(), inserter(dict, dict.end()));
      for (int i = res.first; i <= res.second; ++i) {
        if (dict.find(A[i]) != dict.end()) {
          dict.erase(A[i]);
        }
      }
      assert(dict.empty());
    }
  }
  return 0;
}
