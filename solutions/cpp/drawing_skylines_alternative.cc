// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::numeric_limits;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

struct Rectangle;
typedef vector<Rectangle> Skyline;

// @include
struct Rectangle {
  int left, right, height;
};
typedef vector<Rectangle> Skyline;

Skyline ComputeSkyline(const vector<Rectangle>& buildings) {
  int min_left = numeric_limits<int>::max(),
      max_right = numeric_limits<int>::min();
  for (const Rectangle& building : buildings) {
    min_left = min(min_left, building.left);
    max_right = max(max_right, building.right);
  }

  vector<int> heights(max_right - min_left + 1, 0);
  for (const Rectangle& building : buildings) {
    for (int i = building.left; i <= building.right; ++i) {
      heights[i - min_left] = max(heights[i - min_left], building.height);
    }
  }

  Skyline result;
  int left = 0;
  for (int i = 1; i < heights.size(); ++i) {
    if (heights[i] != heights[i - 1]) {
      result.emplace_back(
          Rectangle{left + min_left, i - 1 + min_left, heights[i - 1]});
      left = i;
    }
  }
  result.emplace_back(Rectangle{left + min_left, max_right, heights.back()});
  return result;
}
// @exclude

void CheckAnswer(const Skyline& ans) {
  // Just check there is no overlap.
  for (int i = 0; i < ans.size(); ++i) {
    assert(ans[i].left <= ans[i].right);
    if (i > 0) {
      assert(ans[i - 1].right <= ans[i].left);
      assert(ans[i - 1].right != ans[i].left ||
             ans[i - 1].height != ans[i].height);
    }
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 2000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 5000);
      n = dis(gen);
    }
    vector<Rectangle> A;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> left_dis(0, 999);
      int left = left_dis(gen);
      uniform_int_distribution<int> right_dis(left, left + 200);
      int right = right_dis(gen);
      uniform_int_distribution<int> height_dis(0, 99);
      int height = height_dis(gen);
      A.emplace_back(Rectangle{left, right, height});
    }
    Skyline ans = ComputeSkyline(A);
    cout << "n = " << n << endl;
    CheckAnswer(ans);
  }
  return 0;
}
