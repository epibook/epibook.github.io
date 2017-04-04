// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

struct Rectangle;
typedef vector<Rectangle> Skyline;
void MergeIntersectSkylines(Skyline*, Rectangle*, int*, Rectangle*, int*);
Skyline MergeSkylines(Skyline*, Skyline*);
Skyline ComputeSkylineInInterval(const vector<Rectangle>&, int, int);

// @include
struct Rectangle {
  int left, right, height;
};
typedef vector<Rectangle> Skyline;

Skyline ComputeSkyline(const vector<Rectangle>& buildings) {
  return ComputeSkylineInInterval(buildings, 0, buildings.size());
}

Skyline ComputeSkylineInInterval(const vector<Rectangle>& buildings,
                                 int left_endpoint, int right_endpoint) {
  if (right_endpoint - left_endpoint <= 1) {  // 0 or 1 skyline, just copy it.
    return {buildings.cbegin() + left_endpoint,
            buildings.cbegin() + right_endpoint};
  }
  int mid = left_endpoint + ((right_endpoint - left_endpoint) / 2);
  auto left_skyline = ComputeSkylineInInterval(buildings, left_endpoint, mid);
  auto right_skyline =
      ComputeSkylineInInterval(buildings, mid, right_endpoint);
  return MergeSkylines(&left_skyline, &right_skyline);
}

Skyline MergeSkylines(Skyline* left_skyline, Skyline* right_skyline) {
  int i = 0, j = 0;
  Skyline merged;

  while (i < left_skyline->size() && j < right_skyline->size()) {
    if ((*left_skyline)[i].right < (*right_skyline)[j].left) {
      merged.emplace_back((*left_skyline)[i++]);
    } else if ((*right_skyline)[j].right < (*left_skyline)[i].left) {
      merged.emplace_back((*right_skyline)[j++]);
    } else if ((*left_skyline)[i].left <= (*right_skyline)[j].left) {
      MergeIntersectSkylines(&merged, &(*left_skyline)[i], &i,
                             &(*right_skyline)[j], &j);
    } else {  // left_skyline[i].left > right_skyline[j].left.
      MergeIntersectSkylines(&merged, &(*right_skyline)[j], &j,
                             &(*left_skyline)[i], &i);
    }
  }

  merged.insert(merged.end(), left_skyline->begin() + i, left_skyline->end());
  merged.insert(merged.end(), right_skyline->begin() + j,
                right_skyline->end());
  return merged;
}

void MergeIntersectSkylines(Skyline* merged, Rectangle* a, int* a_idx,
                            Rectangle* b, int* b_idx) {
  if (a->right <= b->right) {
    if (a->height > b->height) {
      if (b->right != a->right) {
        merged->emplace_back(*a), ++*a_idx;
        b->left = a->right;
      } else {
        ++*b_idx;
      }
    } else if (a->height == b->height) {
      b->left = a->left, ++*a_idx;
    } else {  // a->height < b->height.
      if (a->left != b->left) {
        merged->emplace_back(Rectangle{a->left, b->left, a->height});
      }
      ++*a_idx;
    }
  } else {  // a->right > b->right.
    if (a->height >= b->height) {
      ++*b_idx;
    } else {
      if (a->left != b->left) {
        merged->emplace_back(Rectangle{a->left, b->left, a->height});
      }
      a->left = b->right;
      merged->emplace_back(*b), ++*b_idx;
    }
  }
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
