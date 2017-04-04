#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <limits>
#include <vector>

using namespace std;

// @include
int DP(const vector<int> &A, int a, int b, int k,
       vector<vector<vector<int>>> &table) {
  cout << a << ' ' << b << ' ' << k << endl;
  if (table[a][b][k] == -1) {
    if (k == 1) {
      table[a][b][k] = numeric_limits<int>::max();
    } else if (k == 2) {
      table[a][b][k] = A[b] - A[a];
    } else {
      for (int i = a; i <= b - k + 1; ++i) {
        int val = min(DP(A, a, i, 1, table), DP(A, i + 1, b, k - 1, table));
        table[a][b][k] = max(table[a][b][k], val);
      }
      for (int i = a + 1; i <= b - k + 2; ++i) {
        int val = min(DP(A, a, i, 2, table), DP(A, i + 1, b, k - 2, table));
        table[a][b][k] = max(table[a][b][k], val);
      }
    }
  }
  return table[a][b][k];
}

int max_diff;

void rec(const vector<int> &A, int level, int cur, int k, vector<int> &ans,
         int min_diff) {
  if (level == k) {
    for (int i = 0; i < k; ++i) {
      cout << ans[i] << ' ';
    }
    cout << endl << min_diff << endl;
    max_diff = max(max_diff, min_diff);
  } else {
    for (int i = cur; i + (k - level) - 1 < A.size(); ++i) {
      ans[level] = A[i];
      rec(A, level + 1, i + 1, k, ans,
          min(min_diff, ans[level] - ans[level - 1]));
    }
  }
}

int check_ans(vector<int> &A, int k) {
  sort(A.begin(), A.end());
  vector<int> ans(k);
  max_diff = numeric_limits<int>::min();
  for (int i = 0; i + k - 1 < A.size(); ++i) {
    ans[0] = A[i];
    rec(A, 1, i + 1, k, ans, numeric_limits<int>::max());
  }
  return max_diff;
}

int max_min_diff_k(vector<int> &A, int k) {
  sort(A.begin(), A.end());
  for (int i = 0; i < A.size(); ++i) {
    cout << A[i] << ' ';
  }
  cout << endl;
  vector<vector<vector<int>>> table(
      A.size(), vector<vector<int>>(A.size(), vector<int>(A.size() + 1, -1)));
  return DP(A, 0, A.size() - 1, k, table);
}
// @exclude

int main(int argc, char *argv[]) {
  srand(time(NULL));
  int n, k;
  if (argc == 2) {
    n = atoi(argv[1]);
    k = 1 + rand() % n;
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    n = 1 + rand() % 10000;
    k = 1 + rand() % n;
  }
  vector<int> A;
  for (int i = 0; i < n; ++i) {
    A.push_back(rand() % 100000);
  }
  cout << "n = " << n << ", k = " << k << endl;
  cout << max_min_diff_k(A, k) << endl;
  cout << check_ans(A, k) << endl;
  return 0;
}
