#include <cassert>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <vector>

using namespace std;

// @include
int max_container_area(const vector<int> &A) {
  int largest = 0;
  int l = 0, r = A.size() - 1;
  while (l < r) {
    int l_h = A[l], r_h = A[r];
    int area = min(l_h, r_h) * (r - l);
    if (area > largest) {
      largest = area;
    }
    if (l_h <= r_h) {
      while (l < r && A[l] <= l_h) {
        ++l;
      }
    }
    if (l_h >= r_h) {
      while (l < r && A[r] <= r_h) {
        --r;
      }
    }
  }
  cout << largest << endl;
  return largest;
}
// @exclude

int check_answer(const vector<int> &A) {
  int largest = 0;
  for (int i = 0; i < A.size(); ++i) {
    int area = 0;
    for (int j = i + 1; j < A.size(); ++j) {
      area = min(A[i], A[j]) * (j - i);
      largest = max(largest, area);
    }
  }
  return largest;
}

int main(int argc, char *argv[]) {
  int n;
  srand(time(NULL));
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = 1 + rand() % 100000;
  }
  vector<int> A;
  for (int i = 0; i < n; ++i) {
    A.push_back(rand() % 10000);
  }
  assert(check_answer(A) == max_container_area(A));
  return 0;
}
