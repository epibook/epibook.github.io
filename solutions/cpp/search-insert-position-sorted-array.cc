// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
// From man page:
// ForwardIterator lower_bound (ForwardIterator first, ForwardIterator last,
//                              const T& val);
// Returns an iterator pointing to the first element in the range [first,last)
// which does not compare less than val.
size_t search_insert_position(const vector<int>& A, int target) {
  return distance(A.cbegin(), lower_bound(A.cbegin(), A.cend(), target));
}
// @exclude

void small_test() {
  vector<int> A = {1, 3, 5, 6};
  assert(2 == search_insert_position(A, 5));
  assert(1 == search_insert_position(A, 2));
  assert(4 == search_insert_position(A, 7));
  assert(0 == search_insert_position(A, 0));
}

size_t check_ans(const vector<int>& A, int target) {
  size_t a = 0;
  while (a < A.size() && A[a] < target) {
    ++a;
  }
  return a;
}

int main(int argc, char** argv) {
  small_test();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> n_dis(0, 100000);
      n = n_dis(gen);
    }
    uniform_int_distribution<int> A_dis(0, static_cast<int>(n));
    vector<int> A;
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
    sort(A.begin(), A.end());
    /*
    for (int a : A) {
      cout << a << " ";
    }
    cout << endl;
    */
    int target = A_dis(gen);
    // cout << "target = " << target << endl;
    auto result = search_insert_position(A, target),
         ans = check_ans(A, target);
    assert(result == ans);
  }
  return 0;
}
