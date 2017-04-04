#include <algorithm>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <limits>
#include <vector>

using namespace std;

// @include
int conversion_sorted_array(const vector<int> &A) {
  vector<int> s(A);
  sort(s.begin(), s.end());
  vector<vector<int>> table(A.size(), vector<int>(s.size()));
  for (int j = 0; j < s.size(); ++j) {
    table[0][j] = max(A[0] - s[j], 0);
  }

  for (int i = 1; i < A.size(); ++i) {
    for (int j = 0; j < s.size(); ++j) {
      int decre_cost = A[i] - s[j];
      if (decre_cost < 0) {
        decre_cost = A[i];
      }
      table[i][j] = numeric_limits<int>::max();
      for (int k = 0; k <= j; ++k) {
        table[i][j] = min(table[i][j], table[i - 1][k] + decre_cost);
      }
    }
  }

  int best = numeric_limits<int>::max();
  for (int j = 0; j < s.size(); ++j) {
    best = min(best, table.back()[j]);
  }
  return best;
}
// @exclude

int main(int argc, char *argv[]) {
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = 1 + rand() % 1000;
  }
  vector<int> A;
  A.push_back(10);
  A.push_back(1);
  A.push_back(2);
  A.push_back(3);
  A.push_back(4);
  A.push_back(5);
  assert(9 == conversion_sorted_array(A));
  A.clear();
  for (int i = 0; i < n; ++i) {
    A.push_back(rand() % 10000);
  }
  cout << conversion_sorted_array(A) << endl;
  return 0;
}
