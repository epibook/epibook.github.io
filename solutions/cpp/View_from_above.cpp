// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <map>
#include <memory>
#include <unordered_set>
#include <vector>

using std::cout;
using std::endl;
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

  int Value() const { return isLeft_ ? l_->left : l_->right; }

  bool isLeft_;
  const LineSegment* l_;
};

void CalculateViewFromAbove(const vector<LineSegment>& A) {
  vector<Endpoint> E;
  for (const auto& a : A) {
    E.emplace_back(Endpoint{true, &a});
    E.emplace_back(Endpoint{false, &a});
  }
  sort(E.begin(), E.end());

  int prev_xaxis = E.front().Value();  // The first left end point.
  unique_ptr<LineSegment> prev = nullptr;
  map<int, const LineSegment*> T;
  for (const auto& e : E) {
    if (!T.empty() && prev_xaxis != e.Value()) {
      if (prev == nullptr) {  // Found first segment.
        prev = unique_ptr<LineSegment>(new LineSegment{
            prev_xaxis, e.Value(),
            T.crbegin()->second->color, T.crbegin()->second->height});
      } else {
        if (prev->height == T.crbegin()->second->height &&
            prev->color == T.crbegin()->second->color) {
          prev->right = e.Value();
        } else {
          cout << "[" << prev->left << ", " << prev->right << "]"
               << ", color = " << prev->color << ", height = " << prev->height
               << endl;
          *prev = {prev_xaxis, e.Value(),
                   T.crbegin()->second->color, T.crbegin()->second->height};
        }
      }
    }
    prev_xaxis = e.Value();

    if (e.isLeft_ == true) {  // left end point.
      T.emplace(e.l_->height, e.l_);
    } else {  // right end point.
      T.erase(e.l_->height);
    }
  }

  // Output the remaining segment if any.
  if (prev) {
    cout << "[" << prev->left << ", " << prev->right << "]"
         << ", color = " << prev->color << ", height = " << prev->height
         << endl;
  }
}
// @exclude

int main(int argc, char* argv[]) {
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
