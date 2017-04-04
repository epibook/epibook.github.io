#include <cstdlib>
#include <ctime>
#include <iostream>
#include <numeric>
#include <vector>
#ifdef __clang__
#include <unordered_set>
#else
#include <tr1/unordered_set>
#endif

using namespace std;
#ifndef __clang__
using namespace std::tr1;
#endif

// @include
template <typename T>
vector<T> partition(const vector<T> &A) {
  vector<vector<unordered_set<T>>> table(
      A.size() + 1, vector<unordered_set<int>>(A.size() + 1));
  table[0][0].insert(T(0));
  for (int i = 1; i <= A.size(); ++i) {
    for (int j = 0; j <= i; ++j) {
      for (const T &v : table[i - 1][j]) {
        table[i][j].insert(v);
      }
      for (const T &v : table[i - 1][j]) {
        table[i][j + 1].insert(v + A[i - 1]);
      }
    }
  }

  T sum = accumulate(A.cbegin(), A.cend(), T(0));
  for (int j = 1; j < A.size(); ++j) {
    for (const T &v : table[A.size()][j]) {
      if (v * (A.size() - j) == (sum - v) * j) {
        vector<T> ret;
        int i = A.size();
        T sum = v;
        while (i > 0) {
          if (table[i - 1][j].find(sum) == table[i - 1][j].cend()) {
            --j;
            sum -= A[i - 1];
            ret.push_back(A[i - 1]);
          }
          --i;
        }
        return ret;
      }
    }
  }
  return vector<int>();
}
// @exclude

int main(int argc, char *argv[]) {
  int n;
  srand(time(nullptr));
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = rand() % 1000;
  }
  vector<int> A;
  /*
  for (int i = 0; i < n; ++i) {
    A.push_back(rand() % 1000);
  }
  */
  //*
  A.push_back(2);
  A.push_back(4);
  A.push_back(4);
  A.push_back(6);
  A.push_back(6);
  A.push_back(8);
  //*/
  vector<int> ret = partition(A);
  for (int i = 0; i < ret.size(); ++i) {
    cout << ret[i] << endl;
  }
  return 0;
}
