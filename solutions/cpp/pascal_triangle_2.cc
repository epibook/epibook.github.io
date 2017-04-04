// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> get_kth_Pascal_triangle_row(int k) {
  vector<int> prev_row = {1};
  for (int i = 1; i <= k; ++i) {
    vector<int> curr_row;
    curr_row.emplace_back(1);  // for the first element.
    for (int j = 1; j < i; ++j) {
      curr_row.emplace_back(prev_row[j - 1] + prev_row[j]);
    }
    curr_row.emplace_back(1);  // for the last element.
    prev_row.swap(curr_row);  // swaps the contents of prev_row and curr_row.
  }
  return prev_row;
}
// @exclude

void small_test() {
  auto res1 = get_kth_Pascal_triangle_row(0);
  auto res2 = {1};
  assert(equal(res1.begin(), res1.end(), res2.begin(), res2.end()));
  res1 = get_kth_Pascal_triangle_row(5);
  res2 = {1, 5, 10, 10, 5, 1};
  assert(equal(res1.begin(), res1.end(), res2.begin(), res2.end()));
  res1 = get_kth_Pascal_triangle_row(10);
  res2 = {1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1};
  assert(equal(res1.begin(), res1.end(), res2.begin(), res2.end()));
  res1 = get_kth_Pascal_triangle_row(20);
  res2 = {1,     20,     190,    1140,   4845,   15504,  38760,
          77520, 125970, 167960, 184756, 167960, 125970, 77520,
          38760, 15504,  4845,   1140,   190,    20,     1};
  assert(equal(res1.begin(), res1.end(), res2.begin(), res2.end()));
}

int main(int argc, char** argv) {
  small_test();
  default_random_engine gen((random_device())());
  int k;
  if (argc == 2) {
    k = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(0, 10);
    k = dis(gen);
  }
  cout << "k = " << k << endl;
  auto result = get_kth_Pascal_triangle_row(k);
  copy(result.cbegin(), result.cend(), ostream_iterator<int>(cout, " "));
  return 0;
}
