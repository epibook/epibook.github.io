// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <array>
#include <functional>
#include <iostream>
#include <limits>
#include <memory>
#include <numeric>
#include <random>
#include <unordered_map>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::hash;
using std::numeric_limits;
using std::random_device;
using std::shared_ptr;
using std::uniform_int_distribution;
using std::unordered_map;
using std::vector;

struct Point;

struct TreeNode;

struct HashPoint;

bool is_monochromatic(const vector<vector<int>>& image_sum,
                      const Point& lower_left, const Point& upper_right);

shared_ptr<TreeNode> calculate_optimal_2D_tree_helper(
    const vector<vector<int>>& image, const vector<vector<int>>& image_sum,
    const Point& lower_left, const Point& upper_right,
    unordered_map<Point,
                  unordered_map<Point, shared_ptr<TreeNode>, HashPoint>,
                  HashPoint>& table);

// @include
struct Point {
  bool operator>(const Point& that) const { return i > that.i || j > that.j; }

  // Equal function for hash.
  bool operator==(const Point& that) const {
    return i == that.i && j == that.j;
  }

  int i, j;
};

// Hash function for Point.
struct HashPoint {
  size_t operator()(const Point& p) const {
    return hash<int>()(p.i) ^ hash<int>()(p.j);
  }
};

struct TreeNode {
  int node_num;  // stores the number of node in its subtree.

  Point lowerLeft, upperRight;

  // Store the SW, NW, NE, and SE rectangles if color is mixed.
  vector<shared_ptr<TreeNode>> children;
};

shared_ptr<TreeNode> calculate_optimal_2D_tree(
    const vector<vector<int>>& image) {
  vector<vector<int>> image_sum(image);
  for (int i = 0; i < image.size(); ++i) {
    partial_sum(image_sum[i].cbegin(), image_sum[i].cend(),
                image_sum[i].begin());
    for (int j = 0; i > 0 && j < image[i].size(); ++j) {
      image_sum[i][j] += image_sum[i - 1][j];
    }
  }

  unordered_map<Point, unordered_map<Point, shared_ptr<TreeNode>, HashPoint>,
                HashPoint>
      table;
  return calculate_optimal_2D_tree_helper(
      image, image_sum, Point{0, 0},
      Point{static_cast<int>(image.size() - 1),
            static_cast<int>(image[0].size() - 1)},
      table);
}

bool is_monochromatic(const vector<vector<int>>& image_sum,
                      const Point& lower_left, const Point& upper_right) {
  int pixel_sum = image_sum[upper_right.i][upper_right.j];
  if (lower_left.i >= 1) {
    pixel_sum -= image_sum[lower_left.i - 1][upper_right.j];
  }
  if (lower_left.j >= 1) {
    pixel_sum -= image_sum[upper_right.i][lower_left.j - 1];
  }
  if (lower_left.i >= 1 && lower_left.j >= 1) {
    pixel_sum += image_sum[lower_left.i - 1][lower_left.j - 1];
  }
  return pixel_sum == 0 ||  // totally white.
         pixel_sum ==
             (upper_right.i - lower_left.i + 1) *  // totally black.
                 (upper_right.j - lower_left.j + 1);
}

shared_ptr<TreeNode> calculate_optimal_2D_tree_helper(
    const vector<vector<int>>& image, const vector<vector<int>>& image_sum,
    const Point& lower_left, const Point& upper_right,
    unordered_map<Point,
                  unordered_map<Point, shared_ptr<TreeNode>, HashPoint>,
                  HashPoint>& table) {
  // Illegal rectangle region, returns empty node.
  if (lower_left > upper_right) {
    return shared_ptr<TreeNode>(new TreeNode{0, lower_left, upper_right});
  }

  if (table[lower_left].find(upper_right) == table[lower_left].cend()) {
    if (is_monochromatic(image_sum, lower_left, upper_right)) {
      shared_ptr<TreeNode> p(new TreeNode{1, lower_left, upper_right});
      table[lower_left][upper_right] = p;
    } else {
      shared_ptr<TreeNode> p(
          new TreeNode{numeric_limits<int>::max(), lower_left, upper_right});
      for (int s = lower_left.i; s <= upper_right.i + 1; ++s) {
        for (int t = lower_left.j; t <= upper_right.j + 1; ++t) {
          if ((s != lower_left.i && s != upper_right.i + 1) ||
              (t != lower_left.j && t != upper_right.j + 1)) {
            vector<shared_ptr<TreeNode>> children = {
                // SW rectangle.
                calculate_optimal_2D_tree_helper(image, image_sum, lower_left,
                                                 Point{s - 1, t - 1}, table),
                // NW rectangle.
                calculate_optimal_2D_tree_helper(
                    image, image_sum, Point{lower_left.i, t},
                    Point{s - 1, upper_right.j}, table),
                // NE rectangle.
                calculate_optimal_2D_tree_helper(
                    image, image_sum, Point{s, t}, upper_right, table),
                // SE rectangle.
                calculate_optimal_2D_tree_helper(
                    image, image_sum, Point{s, lower_left.j},
                    Point{upper_right.i, t - 1}, table)};

            int node_num = 1;  // itself.
            for (shared_ptr<TreeNode>& child : children) {
              node_num += child->node_num;
              // Remove the child contains no node.
              if (child->node_num == 0) {
                child = nullptr;
              }
            }
            if (node_num < p->node_num) {
              p->node_num = node_num, p->children = children;
            }
          }
        }
      }
      table[lower_left][upper_right] = p;
    }
  }
  return table[lower_left][upper_right];
}
// @exclude

void recursive_print_tree(const shared_ptr<TreeNode>& r) {
  cout << "(" << r->lowerLeft.i << "," << r->lowerLeft.j << ")-("
       << r->upperRight.i << "," << r->upperRight.j << ")" << endl;
  for (const shared_ptr<TreeNode>& ptr : r->children) {
    if (ptr) {
      recursive_print_tree(ptr);
    }
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int m, n;
  if (argc == 3) {
    m = atoi(argv[1]);
    n = atoi(argv[2]);
  } else {
    uniform_int_distribution<int> dis(1, 20);
    m = dis(gen);
    n = dis(gen);
  }
  vector<vector<int>> image(m, vector<int>(n));
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      uniform_int_distribution<int> zero_or_one(0, 1);
      image[i][j] = zero_or_one(gen);
    }
  }
  cout << m << ' ' << n << endl;
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      cout << image[i][j] << ' ';
    }
    cout << endl;
  }
  cout << endl;
  shared_ptr<TreeNode> r = calculate_optimal_2D_tree(image);
  recursive_print_tree(r);
  return 0;
}
