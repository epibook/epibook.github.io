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
using std::max;
using std::min;
using std::random_device;
using std::stoul;
using std::uniform_int_distribution;
using std::vector;

// @include
int GetMaxArea(const vector<int>& heights) {
  int i = 0, j = heights.size() - 1, max_area = 0;
  while (i < j) {
    max_area = max(max_area, min(heights[i], heights[j]) * (j - i));
    if (heights[i] > heights[j]) {
      --j;
    } else if (heights[i] < heights[j]) {
      ++i;
    } else {  // heights[i] == heights[j].
      ++i, --j;
    }
  }
  return max_area;
}
// @exclude

// O(n^2) checking answer.
int CheckAns(const vector<int>& heights) {
  int res = 0;
  for (size_t i = 0; i < heights.size(); ++i) {
    for (size_t j = i + 1; j < heights.size(); ++j) {
      res = max(res, min(heights[i], heights[j]) * static_cast<int>(j - i));
    }
  }
  return res;
}

void SmallTest() {
  vector<int> A = {1,2,1,3,4,4,5,6,2,1,3,1,3,2,1,2,4,1};
  assert(48 == GetMaxArea(A));
}

int main(int argc, char** argv) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    size_t n;
    if (argc == 2) {
      n = stoul(argv[1]);
    } else {
      uniform_int_distribution<size_t> dis(2, 10000);
      n = dis(gen);
    }
    uniform_int_distribution<int> dis_1000(1, 1000);
    vector<int> heights;
    generate_n(back_inserter(heights), n, [&] { return dis_1000(gen); });
    cout << GetMaxArea(heights) << endl;
    assert(GetMaxArea(heights) == CheckAns(heights));
  }
  return 0;
}
