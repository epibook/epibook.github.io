// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <map>
#include <memory>
#include <unordered_set>
#include <vector>

using std::cout;
using std::endl;
using std::equal;
using std::make_unique;
using std::map;
using std::unique_ptr;
using std::vector;
using std::unordered_set;

// @include
struct LineSegment {
  int left, right;  // Specifies the interval.
  int color;
  int height;
};

class Endpoint {
 public:
  bool operator<(const Endpoint& that) const {
    return Value() < that.Value();
  }

  int Value() const { return isLeft_ ? line_->left : line_->right; }

  bool isLeft_;
  const LineSegment* line_;
};

vector<LineSegment> CalculateViewFromAbove(const vector<LineSegment>& A) {
  vector<Endpoint> sorted_endpoints;
  for (const LineSegment& a : A) {
    sorted_endpoints.emplace_back(Endpoint{true, &a});
    sorted_endpoints.emplace_back(Endpoint{false, &a});
  }
  sort(sorted_endpoints.begin(), sorted_endpoints.end());

  vector<LineSegment> result;
  int prev_xaxis = sorted_endpoints.front().Value();  // Leftmost end point.
  unique_ptr<LineSegment> prev = nullptr;
  map<int, const LineSegment*> active_line_segments;
  for (const Endpoint& endpoint : sorted_endpoints) {
    if (!active_line_segments.empty() && prev_xaxis != endpoint.Value()) {
      if (prev == nullptr) {  // Found first segment.
        prev = make_unique<LineSegment>(
            LineSegment{prev_xaxis, endpoint.Value(),
                        active_line_segments.crbegin()->second->color,
                        active_line_segments.crbegin()->second->height});
      } else {
        if (prev->height == active_line_segments.crbegin()->second->height &&
            prev->color == active_line_segments.crbegin()->second->color &&
            prev_xaxis == prev->right) {
          prev->right = endpoint.Value();
        } else {
          result.emplace_back(*prev);
          *prev = {prev_xaxis, endpoint.Value(),
                   active_line_segments.crbegin()->second->color,
                   active_line_segments.crbegin()->second->height};
        }
      }
    }
    prev_xaxis = endpoint.Value();

    if (endpoint.isLeft_ == true) {  // Left end point.
      active_line_segments.emplace(endpoint.line_->height, endpoint.line_);
    } else {  // Right end point.
      active_line_segments.erase(endpoint.line_->height);
    }
  }

  // Output the remaining segment (if any).
  if (prev) {
    result.emplace_back(*prev);
  }
  return result;
}
// @exclude

void SimpleTest() {
  vector<LineSegment> A = {LineSegment{1, 2, 0, 1}, LineSegment{3, 4, 0, 1}};
  auto result = CalculateViewFromAbove(A);
  vector<LineSegment> golden_result = {LineSegment{1, 2, 0, 1},
                                       LineSegment{3, 4, 0, 1}};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end(),
               [](const LineSegment& a, const LineSegment& b) {
                 return a.left == b.left && a.right == b.right &&
                        a.color == b.color && a.height == b.height;
               }));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  vector<LineSegment> A = {
      LineSegment{0, 4, 0, 0},     (LineSegment{1, 3, 1, 2}),
      (LineSegment{2, 7, 2, 1}),   (LineSegment{4, 5, 3, 3}),
      (LineSegment{5, 7, 3, 0}),   (LineSegment{6, 10, 0, 2}),
      (LineSegment{8, 9, 0, 1}),   (LineSegment{9, 18, 4, 0}),
      (LineSegment{11, 13, 3, 2}), (LineSegment{12, 15, 4, 1}),
      (LineSegment{14, 15, 2, 2}), (LineSegment{16, 17, 3, 2})};
  auto result = CalculateViewFromAbove(A);
  vector<LineSegment> golden_result = {
      LineSegment{0, 1, 0, 0},   LineSegment{1, 3, 1, 2},
      LineSegment{3, 4, 2, 1},   LineSegment{4, 5, 3, 3},
      LineSegment{5, 6, 2, 1},   LineSegment{6, 10, 0, 2},
      LineSegment{10, 11, 4, 0}, LineSegment{11, 13, 3, 2},
      LineSegment{13, 14, 4, 1}, LineSegment{14, 15, 2, 2},
      LineSegment{15, 16, 4, 0}, LineSegment{16, 17, 3, 2},
      LineSegment{17, 18, 4, 0}};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end(),
               [](const LineSegment& a, const LineSegment& b) {
                 return a.left == b.left && a.right == b.right &&
                        a.color == b.color && a.height == b.height;
               }));
  return 0;
}
