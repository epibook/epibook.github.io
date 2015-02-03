// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <queue>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::numeric_limits;
using std::queue;
using std::unique_ptr;

// @include
struct QNode {
  const unique_ptr<BinaryTreeNode<int>>& node;
  int lower, upper;
};

bool IsBinaryTreeBST(const unique_ptr<BinaryTreeNode<int>>& root) {
  queue<QNode> BFS_queue;
  BFS_queue.emplace(
      QNode{root, numeric_limits<int>::min(), numeric_limits<int>::max()});

  while (!BFS_queue.empty()) {
    if (BFS_queue.front().node.get()) {
      if (BFS_queue.front().node->data < BFS_queue.front().lower ||
          BFS_queue.front().node->data > BFS_queue.front().upper) {
        return false;
      }

      BFS_queue.emplace(QNode{BFS_queue.front().node->left, 
                        BFS_queue.front().lower, 
                        BFS_queue.front().node->data});
      BFS_queue.emplace(QNode{BFS_queue.front().node->right,
                        BFS_queue.front().node->data,
                        BFS_queue.front().upper});
    }
    BFS_queue.pop();
  }
  return true;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  auto root = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3});
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1});
  root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6});
  // should output true
  assert(IsBinaryTreeBST(root) == true);
  cout << boolalpha << IsBinaryTreeBST(root) << endl;
  //      10
  //    2   5
  //  1    4 6
  root->data = 10;
  // should output false
  assert(!IsBinaryTreeBST(root));
  cout << boolalpha << IsBinaryTreeBST(root) << endl;
  // empty tree, should output true
  assert(IsBinaryTreeBST(nullptr) == true);
  cout << boolalpha << IsBinaryTreeBST(nullptr) << endl;
  return 0;
}
