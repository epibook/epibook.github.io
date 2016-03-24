// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <limits>
#include <vector>

using std::cout;
using std::endl;
using std::max;
using std::vector;

// @include
class TournamentTree {
 public:
  // n items, and each box has unit_cap.
  TournamentTree(int n, double unit_cap)
      :  // Complete binary tree with n leafs has 2n - 1 nodes.
        tree_(vector<TreeNode>((n * 2) - 1, {unit_cap})) {}

  void Insert(int item, double item_cap) { InsertHelper(0, item, item_cap); }
  // @exclude
  void PrintLeaf() {
    for (int i = 0; i < tree_.size(); ++i) {
      cout << "i = " << i << ", cap = " << tree_[i].cap << endl;
      for (int item : tree_[i].items) {
        cout << item << ' ';
      }
      cout << endl;
    }
  }
  // @include

 private:
  struct TreeNode {
    double cap;  // Leaf: remaining capacity in the box.
    // Non-leaf: max remaining capacity in the subtree.
    vector<int> items;  // Stores the items in the leaf node.
  };

  // Stores the complete binary tree. For tree_[i],
  // left subtree is tree_[2i + 1], and right subtree is tree_[2i + 2].
  vector<TreeNode> tree_;

  // Recursively inserts item in tournament tree.
  void InsertHelper(int idx, int item, double cap) {
    int left = (idx * 2) + 1, right = (idx * 2) + 2;
    if (left < tree_.size()) {  // tree_[idx] is an internal node.
      InsertHelper(tree_[left].cap >= cap ? left : right, item, cap);
      tree_[idx].cap = max(tree_[left].cap, tree_[right].cap);
    } else {  // tree_[idx] is a leaf node.
      tree_[idx].cap -= cap, tree_[idx].items.emplace_back(item);
    }
  }
};
// @exclude

int main(int argc, char* argv[]) {
  // Following is the example in the book.
  TournamentTree t(6, 1.0);
  t.Insert(0, 0.60);
  t.Insert(1, 0.60);
  t.Insert(2, 0.55);
  t.Insert(3, 0.80);
  t.Insert(4, 0.50);
  t.Insert(5, 0.45);
  // Due to the precision error of floating point number, Item 5 will be
  // inserted into 5-th box. However, if we are not using floating point
  // number, everything is fine.
  t.PrintLeaf();
  return 0;
}
