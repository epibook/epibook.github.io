#include <iostream>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <queue>
#include <cassert>

using namespace std;

// @include
template <typename T>
void pop_unqualified(const vector<T> &A, const T &A_i, const int &i,
                     deque<T> &Q) {
  // Delete elements from tail of Q whose values are not greater than A[i]
  while (!Q.empty() && A[i] >= A[Q.back()]) {
    Q.pop_back();
  }
  Q.emplace_back(i);
}

template <typename T>
vector<T> max_sliding_window(const vector<T> &A, const int &w) {
  deque<T> Q;
  for (int i = 0; i < w; ++i) {
    pop_unqualified(A, A[i], i, Q);
  }

  // Find the maximum for A[i - w : i - 1]
  vector<T> ret;
  for (int i = w; i < A.size(); ++i) {
    ret.emplace_back(A[Q.front()]);
    // Expire the element at the head if its index <= i - w
    if (!Q.empty() && Q.front() <= i - w) {
      Q.pop_front();
    }
    pop_unqualified(A, A[i], i, Q);
  }
  ret.emplace_back(A[Q.front()]);
  return ret;
}
// @exclude

template <typename T>
void check_ret(vector<T> &A, int w, vector<T> &ret) {
  int idx = 0;
  for (size_t i = w - 1; i < A.size(); ++i) {
    T max = A[i];
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
  srand(time(nullptr));
  vector<int> A;
  int w;
  if (argc >= 2) {
    w = atoi(argv[1]);
    for (size_t i = 2; i < argc; ++i) {
      A.emplace_back(atoi(argv[i]));
    }
  } else {
    int n = 1 + rand() % 100000;
    for (size_t i = 0; i < n; ++i) {
      A.emplace_back(rand());
    }
    w = 1 + rand() % n;
  }
  vector<int> ret = max_sliding_window<int>(A, w);
  copy(ret.begin(), ret.end(), ostream_iterator<int>(cout, "\n"));
  check_ret<int>(A, w, ret);
  return 0;
}
