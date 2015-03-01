// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <cmath>
#include <deque>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void Eliminate_rows(int i, int j, vector<deque<bool>>* B);

// @include
deque<bool> Gaussian_elimination(const vector<deque<bool>>& A,
                                 const deque<bool>& y) {
  vector<deque<bool>> B(A);
  for (int i = 0; i < B.size(); ++i) {
    B[i].push_back(y[i]);
  }

  for (int i = 0; i < B.size(); ++i) {
    // Find the coefficient starting with 1.
    int idx = i;
    for (int j = i + 1; j < B.size(); ++j) {
      if (B[j][i]) {
        idx = j;
        break;
      }
    }
    swap(B[i], B[idx]);

    // Perform elimination except i-th row.
    if (B[i][i]) {
      Eliminate_rows(i, i, &B);
    }
  }

  for (int i = B.size() - 1; i >= 0; --i) {
    if (B[i][i] == false) {
      bool have_coefficient = false;
      for (int j = i + 1; j < A.size(); ++j) {
        if (B[i][j]) {
          Eliminate_rows(i, j, &B);
          have_coefficient = true;
          swap(B[i], B[j]);  // row permutation.
          break;
        }
      }

      if (!have_coefficient && B[i].back()) {
        cout << "No solution." << endl;
        return {};
      }
    }
  }

  deque<bool> x;
  for (int i = 0; i < B.size(); ++i) {
    x.push_back(B[i].back());
  }
  return x;
}

void Eliminate_rows(int i, int j, vector<deque<bool>>* B) {
  // Use B[i] to eliminate other rows' entry j.
  for (int a = 0; a < B->size(); ++a) {
    if (i != a && (*B)[a][j]) {
      for (int b = 0; b < (*B)[i].size(); ++b) {
        (*B)[a][b] = (*B)[a][b] ^ (*B)[i][b];
      }
    }
  }
}
// @exclude

bool check_answer_with_solution(const vector<deque<bool>>& A,
                                const deque<bool>& b, const deque<bool>& x) {
  for (int i = 0; i < A.size(); ++i) {
    bool res = A[i][0] && x[0];
    for (int j = 1; j < A[i].size(); ++j) {
      res = res ^ (A[i][j] && x[j]);
    }

    if (res != b[i]) {
      return false;
    }
  }
  return true;
}

bool check_answer_no_solution(const vector<deque<bool>>& A,
                              const deque<bool>& b) {
  // Generate all possible combinations of x to test
  // there is no solution actually.
  for (int val = 0; val < (1 << b.size()); ++val) {
    deque<bool> x;
    int temp = val;
    for (int i = 0; i < b.size(); ++i) {
      x.push_back(temp & 1);
      temp >>= 1;
    }
    /*
    copy(x.begin(), x.end(), ostream_iterator<bool>(cout, " "));
    cout << endl;
    */
    assert(check_answer_with_solution(A, b, x) == false);
  }
  return true;
}

void rand_matrix(vector<deque<bool>>* A) {
  default_random_engine gen((random_device())());
  for (int i = 0; i < A->size(); ++i) {
    for (int j = 0; j < (*A)[i].size(); ++j) {
      uniform_int_distribution<int> zero_or_one(0, 1);
      (*A)[i][j] = zero_or_one(gen);
    }
  }
}

void rand_vec(deque<bool>* b) {
  default_random_engine gen((random_device())());
  for (int i = 0; i < b->size(); ++i) {
    uniform_int_distribution<int> zero_or_one(0, 1);
    (*b)[i] = zero_or_one(gen);
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  // Predefined tests.
  vector<deque<bool>> A(4);
  A[0] = {false, false, false, true};
  A[1] = {false, false, false, true};
  A[2] = {false, true, true, true};
  A[3] = {true, false, false, false};
  deque<bool> b = {true, true, false, true};
  deque<bool> x = Gaussian_elimination(A, b);
  copy(x.begin(), x.end(), ostream_iterator<bool>(cout, " "));
  cout << endl;
  if (x.empty()) {  // no solution
    assert(check_answer_no_solution(A, b));
  } else {  // solution
    assert(check_answer_with_solution(A, b, x));
  }

  // Perform random tests below
  for (int times = 0; times < 10000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
      cout << "n = " << n << endl;
    } else {
      uniform_int_distribution<int> dis(1, 16);
      n = dis(gen);
    }
    vector<deque<bool>> A(n, deque<bool>(n));
    rand_matrix(&A);
    deque<bool> b(n);
    rand_vec(&b);
    deque<bool> x = Gaussian_elimination(A, b);
    cout << "n = " << n << endl;
    cout << "A = \n";
    for (int i = 0; i < A.size(); ++i) {
      copy(A[i].begin(), A[i].end(), ostream_iterator<bool>(cout, " "));
      cout << endl;
    }
    cout << "b = \n";
    copy(b.begin(), b.end(), ostream_iterator<bool>(cout, " "));
    cout << endl << endl;
    if (x.empty()) {  // no solution
      assert(check_answer_no_solution(A, b));
    } else {  // solution
      cout << "x = \n";
      copy(x.begin(), x.end(), ostream_iterator<bool>(cout, " "));
      cout << endl << endl;
      assert(check_answer_with_solution(A, b, x));
    }
  }
  return 0;
}
