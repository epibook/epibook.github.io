#include <iostream>

using namespace std;

struct BinaryTree {
  int data;
  BinaryTree *left, *right;
  BinaryTree(int val = 0, BinaryTree* l = nullptr, BinaryTree* r = nullptr)
      : data(val), left(l), right(r) {}
};

// @include
BinaryTree* convert_tree_to_doubly_list(BinaryTree* n) {
  if (n == nullptr) {
    return nullptr;
  }

  BinaryTree* L = convert_tree_to_doubly_list(n->left);
  BinaryTree* R = convert_tree_to_doubly_list(n->right);
  // Join L and n as a doubly linked list
  BinaryTree* L_tail = nullptr;
  if (L) {
    L_tail = L->left;
    L_tail->right = n, n->left = L_tail;
    L_tail = n;
  } else {
    L = L_tail = n;
  }

  // Join L and R as a doubly linked list
  BinaryTree* R_tail = nullptr;
  if (R) {
    R_tail = R->left;
    L_tail->right = R, R->left = L_tail;
  } else {
    R_tail = L_tail;
  }
  R_tail->right = L, L->left = R_tail;
  return L;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  BinaryTree* root = new BinaryTree(3);
  root->left = new BinaryTree(2);
  root->left->left = new BinaryTree(1);
  root->right = new BinaryTree(5);
  root->right->left = new BinaryTree(4);
  root->right->right = new BinaryTree(6);
  // should output 1, 2, 3, 4, 5, 6
  BinaryTree* head = convert_tree_to_doubly_list(root);
  BinaryTree* temp = head;
  do {
    cout << temp->data << endl;
    temp = temp->right;
  } while (temp != head);
  return 0;
}
