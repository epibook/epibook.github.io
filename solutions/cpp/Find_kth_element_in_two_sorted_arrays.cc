// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <limits>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

/*
template <typename T>
T FindKthInTwoSortedArrays(
  const vector<T> &A, const vector<T> &B, int k) {
  // Not enough elements in A and B
  if (k > A.size() + B.size()) {
    return -1;  // no k-th element
  }

  int l_A = 0, r_A = min(static_cast<int>(A.size() - 1), k - 1);
  int l_B = 0, r_B = min(static_cast<int>(B.size() - 1), k - 1);
  while (true) {
    // Averagely get k - 1 elements from A and B
    int num_A = r_A - l_A + 1, num_B = r_B - l_B + 1;
    int i = num_A * (k - 1) / (num_A + num_B);
    int j = (k - 1) - i;

    T A_i_1 = (l_A + i == 0 ? numeric_limits<int>::min() : A[l_A + i - 1]);
    T A_i = (l_A + i == A.size() ? numeric_limits<int>::max() : A[l_A + i]);
    T B_j_1 = (l_B + j == 0 ? numeric_limits<int>::min() : B[l_B + j - 1]);
    T B_j = (l_B + j == B.size() ? numeric_limits<int>::max() : B[l_B + j]);

    if (B_j_1 <= A_i && A_i <= B_j) {
      return A_i;  // A_i is the k-th element
    } else if (A_i_1 <= B_j && B_j <= A_i) {
      return B_j;  // B_j is the k-th element
    }

    if (A_i < B_j) {
      // Exclude the elements <= A_i and >= B_j
      l_A += i + 1, k -= (i + 1);
    } else {
      // Exclude the elements <= B_j and >= A_i
      l_B += j + 1, k -= (j + 1);
    }
  }
}
*/
// @include
int FindKthInTwoSortedArrays(const vector<int>& A, const vector<int>& B,
                             int k) {
  // Lower bound of elements we will choose in A.
  int b = max(0, static_cast<int>(k - B.size()));
  // Upper bound of elements we will choose in A.
  int t = min(static_cast<int>(A.size()), k);

  while (b < t) {
    int x = b + ((t - b) / 2);
    int A_x_1 = (x <= 0 ? numeric_limits<int>::min() : A[x - 1]);
    int A_x = (x >= A.size() ? numeric_limits<int>::max() : A[x]);
    int B_k_x_1 = (k - x <= 0 ? numeric_limits<int>::min() : B[k - x - 1]);
    int B_k_x = (k - x >= B.size() ? numeric_limits<int>::max() : B[k - x]);

    if (A_x < B_k_x_1) {
      b = x + 1;
    } else if (A_x_1 > B_k_x) {
      t = x - 1;
    } else {
      // B[k - x - 1] <= A[x] && A[x - 1] < B[k - x].
      return max(A_x_1, B_k_x_1);
    }
  }

  int A_b_1 = b <= 0 ? numeric_limits<int>::min() : A[b - 1];
  int B_k_b_1 = k - b - 1 < 0 ? numeric_limits<int>::min() : B[k - b - 1];
  return max(A_b_1, B_k_b_1);
}
// @exclude

template <typename T>
T CheckAnswer(const vector<T>& A, const vector<T>& B, int k) {
  int i = 0, j = 0, count = 0;
  T ret;
  while ((i < A.size() || j < B.size()) && count < k) {
    if (i < A.size() && j < B.size()) {
      if (A[i] < B[j]) {
        ret = A[i];
        ++i;
      } else {
        ret = B[j];
        ++j;
      }
    } else if (i < A.size()) {
      ret = A[i];
      ++i;
    } else {
      ret = B[j];
      ++j;
    }
    ++count;
  }
  return ret;
}

void SmallTest() {
  // AA: handwritten test
  vector<int> A0;
  vector<int> B0;
  A0.emplace_back(0);
  A0.emplace_back(1);
  A0.emplace_back(2);
  A0.emplace_back(3);
  B0.emplace_back(0);
  B0.emplace_back(1);
  B0.emplace_back(2);
  B0.emplace_back(3);
  assert(0 == FindKthInTwoSortedArrays(A0, B0, 1));
  assert(0 == FindKthInTwoSortedArrays(A0, B0, 2));
  assert(1 == FindKthInTwoSortedArrays(A0, B0, 3));
  assert(1 == FindKthInTwoSortedArrays(A0, B0, 4));
  assert(2 == FindKthInTwoSortedArrays(A0, B0, 5));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  // Random test 10000 times.
  for (int times = 0; times < 10000; ++times) {
    vector<int> A, B;
    int n, m, k;
    if (argc == 3) {
      n = atoi(argv[1]), m = atoi(argv[2]);
      uniform_int_distribution<int> k_dis(1, n + m);
      k = k_dis(gen);
    } else if (argc == 4) {
      n = atoi(argv[1]), m = atoi(argv[2]), k = atoi(argv[3]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen), m = dis(gen);
      uniform_int_distribution<int> k_dis(1, n + m);
      k = k_dis(gen);
    }
    uniform_int_distribution<int> dis(0, 99999);
    for (size_t i = 0; i < n; ++i) {
      A.emplace_back(dis(gen));
    }
    for (size_t i = 0; i < m; ++i) {
      B.emplace_back(dis(gen));
    }
    sort(A.begin(), A.end()), sort(B.begin(), B.end());
    /*
    for (int i = 0; i < A.size(); ++i) {
      cout << A[i] << ' ';
    }
    cout << endl;
    for (int j = 0; j < B.size(); ++j) {
      cout << B[j] << ' ';
    }
    cout << endl;
    */
    int ans = FindKthInTwoSortedArrays(A, B, k);
    cout << k << "th = " << ans << endl;
    assert(ans == CheckAnswer(A, B, k));
  }
  return 0;
}
