// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

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
  virtual ~BinarySearchTree() { Clear(); }

  bool Empty() const { return !root_.get(); }

  void Clear() { Clear(&root_); }

  bool Insert(int key) {
    if (Empty()) {
      root_ = make_unique<TreeNode>(TreeNode{key, nullptr, nullptr});
    } else {
      TreeNode *curr = root_.get(), *parent;
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

  bool Erase(int key) {
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
    TreeNode* key_node = curr;

    if (key_node->right) {
      // Finds the minimum of the right subtree.
      TreeNode *r_key_node = key_node->right.get(), *r_parent = key_node;
      while (r_key_node->left) {
        r_parent = r_key_node;
        r_key_node = r_key_node->left.get();
      }
      // Moves links to erase the node.
      r_key_node->left.reset(key_node->left.release());
      TreeNode* r_key_node_right = r_key_node->right.release();
      if (r_parent->left.get() == r_key_node) {
        r_key_node = r_parent->left.release();
        r_parent->left.reset(r_key_node_right);
      } else {  // r_parent->right.get() == r_key_node.
        r_key_node = r_parent->right.release();
        r_parent->right.reset(r_key_node_right);
      }
      r_key_node->right.reset(key_node->right.release());
      ReplaceParentChildLink(parent, key_node, r_key_node);

      // Updates root_ link if needed.
      if (root_.get() == key_node) {
        root_.reset(r_key_node);
      }
    } else {
      // Updates root_ link if needed.
      if (root_.get() == key_node) {
        root_.reset(key_node->left.release());
      }
      ReplaceParentChildLink(parent, key_node, key_node->left.get());
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

  void Clear(unique_ptr<TreeNode>* node) {
    if (node->get()) {
      if ((*node)->left.get()) {
        Clear(&((*node)->left));
      }
      if ((*node)->right.get()) {
        Clear(&((*node)->right));
      }
      node->reset(nullptr);
    }
  }

  // Replaces the link between parent and child by new_link.
  void ReplaceParentChildLink(TreeNode* parent, TreeNode* child,
                              TreeNode* new_link) {
    if (!parent) {
      return;
    }

    if (parent->left.get() == child) {
      parent->left.reset(new_link);
    } else {  // parent->right.get() == child.
      parent->right.reset(new_link);
    }
  }

  unique_ptr<TreeNode> root_ = nullptr;
};
// @exclude

int main(int argc, char* argv[]) {
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
  assert(BST.Erase(7) == true);
  assert(BST.Erase(9) == true);
  // should output 8
  assert(BST.GetRootVal() == 8);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(4) == true);
  // should output 8
  assert(BST.GetRootVal() == 8);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(8) == true);
  // should output 5
  assert(BST.GetRootVal() == 5);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(5) == true);
  assert(BST.Erase(3) == true);
  // should output 2
  assert(BST.GetRootVal() == 2);
  cout << BST.GetRootVal() << endl;
  assert(BST.Erase(2) == true);
  assert(BST.Erase(1) == false);
  assert(BST.Empty() == true);
  return 0;
}
