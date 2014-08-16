// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <vector>

using std::vector;

// @include
int bsearch(int t, const vector<int>& A) {
  int L = 0, U = A.size() - 1;
  while (L <= U) {
    int M = (L + U) / 2;
    if (A[M] < t) {
      L = M + 1;
    } else if (A[M] == t) {
      return M;
    } else {
      U = M - 1;
    }
  }
  return -1;
}
// @exclude

int main(int argc, char* argv[]) {
  vector<int> A;
  A.emplace_back(1);
  A.emplace_back(2);
  A.emplace_back(2);
  A.emplace_back(2);
  A.emplace_back(2);
  A.emplace_back(3);
  A.emplace_back(3);
  A.emplace_back(3);
  A.emplace_back(5);
  A.emplace_back(6);
  A.emplace_back(10);
  A.emplace_back(100);
  assert(0 == bsearch(1, A));
  assert(1 <= bsearch(2, A) && bsearch(2, A) <= 4);
  assert(5 <= bsearch(3, A));
  assert(-1 == bsearch(4, A));
  return 0;
}
