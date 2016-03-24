#include <iostream>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <queue>
#include <cassert>

using namespace std;

// @include
vector<int> max_sliding_window(const vector<int> &A, int w) {
  deque<int> Q;
  for (int i = 0; i < w; ++i) {
    while (!Q.empty() && A[i] >= A[Q.back()]) {
      Q.pop_back();
    }
    Q.push_back(i);
  }

  // Find the maximum for A[i - w : i - 1]
  vector<int> ret;
  for (int i = w; i < A.size(); ++i) {
    ret.push_back(A[Q.front()]);
    // Expire the elements whose index <= i - w
    while (!Q.empty() && Q.front() <= i - w) {
      Q.pop_front();
    }
    // Add new elements
    while (!Q.empty() && A[i] >= A[Q.back()]) {
      Q.pop_back();
    }
    Q.push_back(i);
  }
  ret.push_back(A[Q.front()]);
  return ret;
}
// @exclude

void check_ret(vector<int> &A, int w, vector<int> &ret) {
  int idx = 0;
  for (size_t i = w - 1; i < A.size(); ++i) {
    int max = A[i];
    for (size_t j = 0; j < w; ++j) {
      if (A[i - j] > max) {
        max = A[i - j];
      }
    }
    assert(max == ret[idx]);
    ++idx;
  }
}

int main(int argc, char *argv[]) {
  vector<int> A;
  srand(time(NULL));
  int w;
  if (argc >= 2) {
    w = atoi(argv[1]);
    for (size_t i = 2; i < argc; ++i) {
      A.push_back(atoi(argv[i]));
    }
  } else {
    int n = 1 + rand() % 100000;
    for (size_t i = 0; i < n; ++i) {
      A.push_back(rand());
    }
    w = 1 + rand() % n;
  }
  vector<int> ret = max_sliding_window(A, w);
  for (size_t i = 0; i < ret.size(); ++i) {
    cout << ret[i] << endl;
  }
  check_ret(A, w, ret);
  return 0;
}
