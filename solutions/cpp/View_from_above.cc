// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <map>
#include <memory>
#include <unordered_set>
#include <vector>

using std::cout;
using std::endl;
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
  bool operator<(const Endpoint& that) const { return Value() < that.Value(); }

  int Value() const { return isLeft_ ? line_->left : line_->right; }

  bool isLeft_;
  const LineSegment* line_;
};

void CalculateViewFromAbove(const vector<LineSegment>& A) {
  vector<Endpoint> sorted_endpoints;
  for (const LineSegment& a : A) {
    sorted_endpoints.emplace_back(Endpoint{true, &a});
    sorted_endpoints.emplace_back(Endpoint{false, &a});
  }
  sort(sorted_endpoints.begin(), sorted_endpoints.end());

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
          cout << "[" << prev->left << ", " << prev->right << "]"
               << ", color = " << prev->color << ", height = " << prev->height
               << endl;
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
    cout << "[" << prev->left << ", " << prev->right << "]"
         << ", color = " << prev->color << ", height = " << prev->height
         << endl;
  }
}
// @exclude

void SimpleTest() {
  vector<LineSegment> A;
  A.emplace_back(LineSegment{1, 2, 0, 1});
  A.emplace_back(LineSegment{3, 4, 0, 1});
  CalculateViewFromAbove(A);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  vector<LineSegment> A;
  A.emplace_back(LineSegment{0, 4, 0, 0});
  A.emplace_back(LineSegment{1, 3, 1, 2});
  A.emplace_back(LineSegment{2, 7, 2, 1});
  A.emplace_back(LineSegment{4, 5, 3, 3});
  A.emplace_back(LineSegment{5, 7, 3, 0});
  A.emplace_back(LineSegment{6, 10, 0, 2});
  A.emplace_back(LineSegment{8, 9, 0, 1});
  A.emplace_back(LineSegment{9, 18, 4, 0});
  A.emplace_back(LineSegment{11, 13, 3, 2});
  A.emplace_back(LineSegment{12, 15, 4, 1});
  A.emplace_back(LineSegment{14, 15, 2, 2});
  A.emplace_back(LineSegment{16, 17, 3, 2});
  for (const LineSegment& s : A) {
    cout << "line segment, left = " << s.left << ", right = " << s.right
         << ", color = " << s.color << ", height = " << s.height << endl;
  }
  CalculateViewFromAbove(A);
  return 0;
}
