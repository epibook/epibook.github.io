// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <random>
#include <vector>

#include "./Largest_rectangle_under_skyline.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// O(n^2) implementation checks answer.
int CheckAnswer(const vector<int>& A) {
  int max = -1;
  for (int i = 0; i < A.size(); ++i) {
    int left = i - 1, right = i + 1;
    while (left >= 0 && A[left] >= A[i]) {
      --left;
    }
    while (right < A.size() && A[right] >= A[i]) {
      ++right;
    }
    int area = (right - left - 1) * A[i];
    if (area > max) {
      max = area;
    }
  }
  cout << max << endl;
  return max;
}

void SmallTest() {
  vector<int> A = {2, 3, 4, 1, 2};
  int area = CalculateLargestRectangle(A);
  int alter_area = CalculateLargestRectangleAlternative(A);
  assert(area == alter_area);
  assert(CheckAnswer(A) == area);
  assert(6 == area);
  A = {2, 2, 2};
  assert(6 == CalculateLargestRectangle(A));
  A = {1, 1, 2};
  assert(3 == CalculateLargestRectangle(A));
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 3000; ++times) {
    vector<int> A;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 1000);
      n = dis(gen);
    }
    while (n--) {
      uniform_int_distribution<int> dis(0, 999);
      A.emplace_back(dis(gen));
    }
    int area = CalculateLargestRectangle(A);
    int alter_area = CalculateLargestRectangleAlternative(A);
    cout << area << " " << alter_area << endl;
    assert(area == alter_area);
    assert(CheckAnswer(A) == area);
  }
  return 0;
}
