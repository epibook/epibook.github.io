#include <iostream>
#ifdef __clang__
#include <unordered_set>
#else
#include <tr1/unordered_set>
#endif
#include <algorithm>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <vector>

using namespace std;
#ifndef __clang__
using namespace std::tr1;
#endif

// @include
int find_longest_arithmetic_progressions(const vector<int> &A) {
  int len = 2;
  vector<vector<int>> L(A.size(), vector<int>(A.size(), 2));
  for (int j = A.size() - 2; j >= 1; --j) {
    int i = j - 1, k = j + 1;
    while (i >= 0 && k < A.size()) {
      if (A[i] + A[k] < (A[j] * 2)) {
        ++k;
      } else if (A[i] + A[k] > (A[j] * 2)) {
        --i;
      } else {  // A[i] + A[k] == (A[j] * 2)
        L[i][j] = L[j][k] + 1;
        len = max(len, L[i][j]);
        --i, ++k;
      }
    }
  }
  return len;
}
// @exclude

// O(n^3) method solving this
int check_ans(const vector<int> &A) {
  int res = 2;
  for (int i = 0; i < A.size(); ++i) {
    for (int j = i + 1; j < A.size(); ++j) {
      int len = 2;
      for (int k = j + 1; k < A.size(); ++k) {
        if (A[k] - A[i] == len * (A[j] - A[i])) {
          ++len;
        }
      }
      res = max(res, len);
    }
  }
  cout << res << endl;
  return res;
}

int main(int argc, char *argv[]) {
  srand(time(NULL));
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = 1 + rand() % 1000;
  }
  vector<int> A;
  unordered_set<int> table;
  for (int i = 0; i < n; ++i) {
    while (true) {
      int x = rand() % 5000;
      if (table.insert(x).second) {
        A.push_back(x);
        break;
      }
    }
  }
  sort(A.begin(), A.end());
  cout << find_longest_arithmetic_progressions(A) << endl;
  assert(check_ans(A) == find_longest_arithmetic_progressions(A));
  return 0;
}
