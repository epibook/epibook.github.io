// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;

// @include
class BinarySearchTree {
 public:
  // @exclude
  bool Empty() const { return !root_.get(); }

  // @include
  bool Insert(int key) {
    if (root_ == nullptr) {
      root_ = make_unique<TreeNode>(TreeNode{key, nullptr, nullptr});
    } else {
      TreeNode *curr = root_.get(), *parent = nullptr;
      while (curr) {
        parent = curr;
        if (key == curr->data) {
          return false;  // key already present, no duplicates to be added.
        } else if (key < curr->data) {
          curr = curr->left.get();
        } else {  // key > curr->data.
          curr = curr->right.get();
        }
      }

      // Inserts key according to key and parent.
      if (key < parent->data) {
        parent->left.reset(new TreeNode{key});
      } else {
        parent->right.reset(new TreeNode{key});
      }
    }
    return true;
  }

  bool Delete(int key) {
    // Find the node with key.
    TreeNode *curr = root_.get(), *parent = nullptr;
    while (curr && curr->data != key) {
      parent = curr;
      curr = key < curr->data ? curr->left.get() : curr->right.get();
    }

    if (!curr) {
      // There's no node with key in this tree.
      return false;
    }

    TreeNode *key_node = curr;
    if (key_node->right) {
      // Finds the minimum of the right subtree.
      TreeNode *r_key_node = key_node->right.get(), *r_parent = key_node;
      while (r_key_node->left) {
        r_parent = r_key_node;
        r_key_node = r_key_node->left.get();
      }
      key_node->data = r_key_node->data;
      // Moves links to erase the node.
      if (r_parent->left.get() == r_key_node) {
        r_parent->left.reset(r_key_node->right.release());
      } else {  // r_parent->right.get() == r_key_node.
        r_parent->right.reset(r_key_node->right.release());
      }
    } else {
      // Updates root_ link if needed.
      if (root_.get() == key_node) {
        root_.reset(key_node->left.release());
      } else {
        if (parent->left.get() == key_node) {
          parent->left.reset(key_node->left.release());
        } else {  // parent->right.get() == key_node.
          parent->right.reset(key_node->left.release());
        }
      }
    }
    return true;
  }
  // @exclude
  int GetRootVal() const { return root_->data; }
  // @include

 private:
  struct TreeNode {
    int data;
    unique_ptr<TreeNode> left, right;
  };

  unique_ptr<TreeNode> root_ = nullptr;
};
// @exclude

int main(int argc, char *argv[]) {
  BinarySearchTree BST;
  assert(BST.Empty() == true);
  assert(BST.Insert(7) == true);
  assert(BST.Insert(8) == true);
  assert(BST.Insert(9) == true);
  assert(BST.Insert(4) == true);
  assert(BST.Insert(3) == true);
  assert(BST.Empty() == false);
  assert(BST.Insert(2) == true);
  assert(BST.Insert(5) == true);
  assert(BST.Delete(7) == true);
  assert(BST.Delete(9) == true);
  // should output 8
  assert(BST.GetRootVal() == 8);
  cout << BST.GetRootVal() << endl;
  assert(BST.Delete(4) == true);
  // should output 8
  assert(BST.GetRootVal() == 8);
  cout << BST.GetRootVal() << endl;
  assert(BST.Delete(8) == true);
  // should output 5
  assert(BST.GetRootVal() == 5);
  cout << BST.GetRootVal() << endl;
  assert(BST.Delete(5) == true);
  assert(BST.Delete(3) == true);
  // should output 2
  assert(BST.GetRootVal() == 2);
  cout << BST.GetRootVal() << endl;
  assert(BST.Delete(2) == true);
  assert(BST.Delete(1) == false);
  assert(BST.Empty() == true);
  BST.Insert(7);
  assert(BST.GetRootVal() == 7);
  BST.Insert(9);
  assert(BST.GetRootVal() == 7);
  BST.Delete(7);
  assert(BST.GetRootVal() == 9);
  return 0;
}
