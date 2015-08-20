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
// Returns the number of valid entries after deletion.
size_t DeleteKey(int key, vector<int>* A_ptr) {
  auto& A = *A_ptr;
  size_t write_idx = 0;
  for (size_t i = 0; i < A.size(); ++i) {
    if (A[i] != key) {
      A[write_idx++] = A[i];
    }
  }
  return write_idx;
}
// @exclude

void CheckAns(const vector<int>& A, size_t n, int key) {
  for (size_t i = 0; i < n; ++i) {
    assert(A[i] != key);
  }
}

int main(int argc, char** argv) {
  default_random_engine gen((random_device())());
  size_t n;
  if (argc == 2) {
    n = stoul(argv[1]);
  } else {
    uniform_int_distribution<size_t> dis_n(0, 10000);
    n = dis_n(gen);
  }
  for (int times = 0; times < 1000; ++times) {
    uniform_int_distribution<int> A_dis(-1000, 1000);
    vector<int> A;
    generate_n(back_inserter(A), n, [&] { return A_dis(gen); });
    auto copy_A(A);
    int target = A_dis(gen);
    auto size = DeleteKey(target, &A);
    cout << "size = " << size << " key = " << target << endl;
    CheckAns(A, size, target);
    auto it = remove_if(copy_A.begin(), copy_A.end(),
                        [&target](int a) { return a == target; });
    cout << distance(copy_A.begin(), it) << endl;
    assert(size == distance(copy_A.begin(), it));
  }
  return 0;
}
