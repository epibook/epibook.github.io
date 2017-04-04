#include <algorithm>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <vector>

using namespace std;

// @include
bool two_difference(const vector<int> &A, int k) {
  int i = A.size() - 1, j = A.size() - 1;
  while (i >= 0 && j >= 0) {
    if (A[i] - A[j] == k) {
      cout << A[i] << ' ' << A[j] << ' ' << k << endl;
      return true;
    } else if (A[i] - A[j] > k) {
      --i;
    } else {
      --j;
    }
  }
  return false;
}
// @exclude

// n^2 solution
bool check_ans(const vector<int> &A, int k) {
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A.size(); ++j) {
      if (A[i] - A[j] == k) {
        return true;
      }
    }
  }
  return false;
}

int main(int argc, char *argv[]) {
  int n, k;
  srand(time(NULL));
  if (argc == 2) {
    n = atoi(argv[1]);
    k = rand() % n;
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    n = 1 + rand() % 10000;
    k = rand() % n;
  }
  vector<int> A;
  for (size_t i = 0; i < n; ++i) {
    A.push_back(((rand() & 1) ? -1 : 1) * rand() % 100000);
  }
  sort(A.begin(), A.end());
  for (int i = 0; i < A.size(); ++i) {
    cout << A[i] << ' ';
  }
  cout << endl;
  cout << ((two_difference(A, k) == true) ? "true" : "false") << endl;
  assert(check_ans(A, k) == two_difference(A, k));
  return 0;
}
