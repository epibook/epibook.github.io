// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <limits>
#include <numeric>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::max;
using std::min;
using std::mt19937;
using std::numeric_limits;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

int find_set(int x, vector<int> *set);
void union_set(vector<int> *set, int x, int y);

// @include
vector<int> offline_minimum(const vector<int> &A, const vector<int> &E) {
  vector<int> R(A.size(), E.size());
  int pre = 0;

  // Initialize the collection of subsets.
  for (int i = 0; i < E.size(); ++i) {
    for (int j = pre; j < E[i]; ++j) {
      R[A[j]] = i;
    }
    pre = E[i];
  }

  vector<int> ret(E.size(), -1);  // stores the answer
  vector<int> set(E.size() + 1);  // the disjoint-set
  iota(set.begin(), set.end(), 0);  // initializes the disjoint-set
  for (int i = 0; i < A.size(); ++i) {
    if (find_set(R[i], &set) != E.size() && ret[find_set(R[i], &set)] == -1) {
      ret[set[R[i]]] = i;
      union_set(&set, set[R[i]], set[R[i]] + 1);
    }
  }
  return ret;
}

int find_set(int x, vector<int> *set) {
  if ((*set)[x] != x) {
    (*set)[x] = find_set((*set)[x], set);  // path compression.
  }
  return (*set)[x];
}

void union_set(vector<int> *set, int x, int y) {
  int x_root = find_set(x, set), y_root = find_set(y, set);
  (*set)[min(x_root, y_root)] = max(x_root, y_root);
}
// @exclude

// O(nm) checking method
vector<int> check_answer(const vector<int> &A, const vector<int> &E) {
  deque<bool> exist(A.size(), false);
  vector<int> ans(E.size());

  for (int i = 0; i < E.size(); ++i) {
    int min_val = numeric_limits<int>::max();
    for (int j = 0; j < E[i]; ++j) {
      if (A[j] < min_val && !exist[A[j]]) {
        min_val = min(A[j], min_val);
      }
    }
    exist[min_val] = true;
    ans[i] = min_val;
  }
  /*
  cout << "ans2 = ";
  copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
  cout << endl;
  */
  return ans;
}

void small_test() {
  vector<int> A = {6, 8, 0, 9, 2, 7, 3, 4, 1, 5};
  vector<int> E = {4, 7, 7, 7, 10};
  auto ans = offline_minimum(A, E);
  copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
  cout << endl;
  vector<int> expected_ans = {0, 2, 3, 6, 1};
  assert(ans.size() == expected_ans.size() &&
         equal(ans.begin(), ans.end(), expected_ans.begin()));
}

int main(int argc, char *argv[]) {
  small_test();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n, m;
    if (argc == 2) {
      n = atoi(argv[1]);
      uniform_int_distribution<int> dis(1, n);
      m = dis(gen);
    } else if (argc == 3) {
      n = atoi(argv[1]);
      m = atoi(argv[2]);
    } else {
      uniform_int_distribution<int> n_dis(1, 1000);
      n = n_dis(gen);
      uniform_int_distribution<int> m_dis(1, n);
      m = m_dis(gen);
    }
    cout << "n = " << n << ", m = " << m << endl;
    vector<int> A(n);
    iota(A.begin(), A.end(), 0);
    random_device rd;
    mt19937 g(rd());
    shuffle(A.begin(), A.end(), g);
    /*
    cout << "A = ";
    copy(A.begin(), A.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    */
    vector<int> E;
    for (int i = 0; i < m; ++i) {
      uniform_int_distribution<int> dis(i + 1, n);
      E.emplace_back(dis(gen));
    }
    sort(E.begin(), E.end());
    //*
    cout << "E = ";
    copy(E.begin(), E.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    //*/
    vector<int> ans = offline_minimum(A, E);
    //*
    cout << "ans1 = ";
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    //*/
    vector<int> tmp = check_answer(A, E);
    assert(ans.size() == tmp.size() &&
           equal(ans.begin(), ans.end(), tmp.begin()));
  }
  return 0;
}
