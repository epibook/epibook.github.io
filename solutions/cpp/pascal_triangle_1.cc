// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
vector<vector<int>> GeneratePascalTriangle(int num_rows) {
  vector<vector<int>> pascal_triangle;
  for (int i = 0; i < num_rows; ++i) {
    vector<int> curr_row;
    for (int j = 0; j <= i; ++j) {
      // Sets this entry to the sum of the two above adjacent entries if they
      // exist.
      curr_row.emplace_back(0 < j && j < i
                                ? pascal_triangle.back()[j - 1] +
                                      pascal_triangle.back()[j]
                                : 1);
    }
    pascal_triangle.emplace_back(curr_row);
  }
  return pascal_triangle;
}
// @exclude

void SmallTest() {
  auto result = GeneratePascalTriangle(3);
  vector<vector<int>> golden_result = {{1}, {1, 1}, {1, 2, 1}};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  int n;
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(0, 10);
    n = dis(gen);
  }
  cout << "n = " << n << endl;
  auto result = GeneratePascalTriangle(n);
  for (size_t i = 0; i < result.size(); ++i) {
    for (size_t j = 0; j < result[i].size(); ++j) {
      cout << result[i][j] << ' ';
    }
    cout << endl;
  }
  return 0;
}
