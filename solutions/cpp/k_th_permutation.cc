#include <cassert>
#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

// @include
int n_factor(int n) {
  int result = 1;
  while (n) {
    result *= n--;
  }
  return result;
}

vector<int> generate_kth_permutation(int n, int k) {
  --k;  // adjust k to start from 0
  vector<int> A;
  for (int i = 0; i < n; ++i) {
    A.push_back(i);
  }

  vector<int> result;
  for (int i = 0; i < n; ++i) {
    int count = n_factor(n - i - 1);
    int index = k / count;
    k %= count;

    int c_idx = 0;
    for (int j = 0; j < n; ++j) {
      if (A[j] != -1) {
        if (c_idx == index) {
          result.push_back(A[j]);
          A[j] = -1;
          break;
        }
        ++c_idx;
      }
    }
  }
  return result;
}
// @exclude

int main(int argc, char *argv[]) {
  int n, k;
  if (argc == 2) {
    n = atoi(argv[1]);
    k = 1 + rand() % n_factor(n);
  } else if (argc == 3) {
    n = atoi(argv[1]);
    k = atoi(argv[2]);
  } else {
    n = 1 + rand() % 10;
    k = 1 + rand() % n_factor(n);
  }
  vector<int> x(generate_kth_permutation(n, k));
  cout << k << "-th permutation of " << n << " elements is ";
  for (int i = 0; i < x.size(); ++i) {
    cout << x[i];
  }
  cout << endl;
  return 0;
}
