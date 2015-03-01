package com.epi;

public class UpdateBST {
  // @include
  public static class BinarySearchTree {
    private static class TreeNode {
      public Integer data;
      public TreeNode left, right;

      public TreeNode(Integer data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
      }
    }

    private TreeNode root;

    public boolean empty() {
      return root == null;
    }

    public void clear() {
      root = null;
    }

    public boolean insert(Integer key) {
      if (empty()) {
        root = new TreeNode(key, null, null);
      } else {
        TreeNode curr = root;
        TreeNode parent = curr;
        while (curr != null) {
          parent = curr;
          if (key.compareTo(curr.data) == 0) {
            return false; // key already present, no duplicates to be added.
          } else if (key.compareTo(curr.data) < 0) {
            curr = curr.left;
          } else { // key.compareTo(curr.data) > 0.
            curr = curr.right;
          }
        }

        // Insert key according to key and parent.
        if (key.compareTo(parent.data) < 0) {
          parent.left = new TreeNode(key, null, null);
        } else {
          parent.right = new TreeNode(key, null, null);
        }
      }
      return true;
    }

    public boolean erase(Integer key) {
      // Find the node with key.
      TreeNode curr = root, parent = null;
      while (curr != null && curr.data.compareTo(key) != 0) {
        parent = curr;
        curr = key.compareTo(curr.data) < 0 ? curr.left : curr.right;
      }

      if (curr == null) {
        // There's no node with key in this binary tree.
        return false;
      }

      TreeNode keyNode = curr;
      if (keyNode.right != null) {
        // Find the minimum of the right subtree.
        TreeNode rKeyNode = keyNode.right;
        TreeNode rParent = keyNode;
        while (rKeyNode.left != null) {
          rParent = rKeyNode;
          rKeyNode = rKeyNode.left;
        }
        // Move links to erase the node.
        rKeyNode.left = keyNode.left;
        keyNode.left = null;
        TreeNode rKeyNodeRight = rKeyNode.right;
        rKeyNode.right = null;
        if (keyNode.right != rKeyNode) {
          rKeyNode.right = keyNode.right;
          keyNode.right = null;
        }
        if (rParent.left == rKeyNode) {
          rKeyNode = rParent.left;
          rParent.left = null;
          rParent.left = rKeyNodeRight;
        } else { // rParent.left != rKeyNode.
          rKeyNode = rParent.right;
          rParent.right = rKeyNodeRight;
        }
        replaceParentChildLink(parent, keyNode, rKeyNode);

        // Update root link if needed.
        if (root == keyNode) {
          root = rKeyNode;
        }
      } else {
        // Update root link if needed.
        if (root == keyNode) {
          root = keyNode.left;
          keyNode.left = null;
        }
        replaceParentChildLink(parent, keyNode, keyNode.left);
      }
      return true;
    }

    // Replace the link between parent and child by new_link.
    private void replaceParentChildLink(TreeNode parent, TreeNode child,
                                        TreeNode newLink) {
      if (parent == null) {
        return;
      }

      if (parent.left == child) {
        parent.left = newLink;
      } else { // parent->right.get() == child.
        parent.right = newLink;
      }
    }

    // @exclude
    public Integer getRootVal() {
      return root.data;
    }
    // @include
  }
  // @exclude

  public static void main(String[] args) {
    BinarySearchTree bst = new BinarySearchTree();
    assert (bst.empty());
    assert (bst.insert(4));
    assert (bst.insert(5));
    assert (bst.insert(2));
    assert (bst.insert(3));
    assert (bst.insert(1));
    assert (!bst.empty());
    assert (!bst.erase(0));
    assert (bst.erase(2));
    assert (!bst.erase(2));
    assert (!bst.insert(4));
    // should output 4
    assert (bst.getRootVal() == 4);
    System.out.println(bst.getRootVal());
    assert (bst.erase(4));
    // should output 5
    assert (bst.getRootVal() == 5);
    System.out.println(bst.getRootVal());
    assert (bst.erase(5));
    // should output 3
    assert (bst.getRootVal() == 3);
    System.out.println(bst.getRootVal());
    assert (bst.erase(3));
    // should output 1
    assert (bst.getRootVal() == 1);
    System.out.println(bst.getRootVal());
    assert (bst.erase(1));
    assert (bst.empty());
  }
}
