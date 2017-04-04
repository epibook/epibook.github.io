// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <utility>
#include <vector>

using std::array;
using std::cout;
using std::endl;
using std::make_unique;
using std::max;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

struct TreeNode;
struct HeightAndDiameter;
HeightAndDiameter ComputeHeightAndDiameter(const unique_ptr<TreeNode>&);

// @include
struct TreeNode {
  struct Edge {
    unique_ptr<TreeNode> root;
    double length;
  };

  vector<Edge> edges;
};

struct HeightAndDiameter {
  double height, diameter;
};

double ComputeDiameter(const unique_ptr<TreeNode>& T) {
  return T ? ComputeHeightAndDiameter(T).diameter : 0.0;
}

HeightAndDiameter ComputeHeightAndDiameter(const unique_ptr<TreeNode>& r) {
  double diameter = numeric_limits<double>::min();
  array<double, 2> heights = {{0.0, 0.0}};  // Stores the max two heights.
  for (const auto& e : r->edges) {
    HeightAndDiameter h_d = ComputeHeightAndDiameter(e.root);
    if (h_d.height + e.length > heights[0]) {
      heights = {{h_d.height + e.length, heights[0]}};
    } else if (h_d.height + e.length > heights[1]) {
      heights[1] = h_d.height + e.length;
    }
    diameter = max(diameter, h_d.diameter);
  }
  return {heights[0], max(diameter, heights[0] + heights[1])};
}
// @exclude

int main(int argc, char* argv[]) {
  unique_ptr<TreeNode> r = nullptr;
  assert(0.0 == ComputeDiameter(r));
  r = make_unique<TreeNode>(TreeNode());
  r->edges.emplace_back(
      TreeNode::Edge{make_unique<TreeNode>(TreeNode()), 10});
  r->edges[0].root->edges.emplace_back(
      TreeNode::Edge{make_unique<TreeNode>(TreeNode()), 50});
  r->edges.emplace_back(
      TreeNode::Edge{make_unique<TreeNode>(TreeNode()), 20});
  assert(80 == ComputeDiameter(r));
  cout << ComputeDiameter(r) << endl;
  r->edges[0].root->edges.emplace_back(
      TreeNode::Edge{make_unique<TreeNode>(TreeNode()), 100});
  assert(150 == ComputeDiameter(r));
  cout << ComputeDiameter(r) << endl;
  return 0;
}
