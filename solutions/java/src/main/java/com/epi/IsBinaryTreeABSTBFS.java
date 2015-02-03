package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.LinkedList;

public class IsBinaryTreeABSTBFS {
  // @include
  public static class QNode {
    public BinaryTree<Integer> node;
    public Integer lower, upper;

    public QNode(BinaryTree<Integer> node, Integer lower, Integer upper) {
      this.node = node;
      this.lower = lower;
      this.upper = upper;
    }
  }

  public static boolean isBinaryTreeBST(BinaryTree<Integer> root) {
    LinkedList<QNode> BFSQueue = new LinkedList<>();
    BFSQueue.addLast(new QNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

    while (!BFSQueue.isEmpty()) {
      if (BFSQueue.getFirst().node != null) {
        if (BFSQueue.getFirst().node.getData().compareTo(BFSQueue.getFirst().lower) < 0
            || BFSQueue.getFirst().node.getData().compareTo(BFSQueue.getFirst().upper) > 0) {
          return false;
        }

        BFSQueue.addLast(new QNode(BFSQueue.getFirst().node.getLeft(), 
                                   BFSQueue.getFirst().lower,
                                   BFSQueue.getFirst().node.getData()));
        BFSQueue.addLast(new QNode(BFSQueue.getFirst().node.getRight(),
                                   BFSQueue.getFirst().node .getData(),
                                   BFSQueue.getFirst().upper));
      }
      BFSQueue.removeFirst();
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3);
    root.setLeft(new BinaryTree<>(2));
    root.getLeft().setLeft(new BinaryTree<>(1));
    root.setRight(new BinaryTree<>(5));
    root.getRight().setLeft(new BinaryTree<>(4));
    root.getRight().setRight(new BinaryTree<>(6));
    // should output true.
    assert isBinaryTreeBST(root);
    System.out.println(isBinaryTreeBST(root));
    // 10
    // 2 5
    // 1 4 6
    root.setData(10);
    // should output false.
    assert !isBinaryTreeBST(root);
    System.out.println(isBinaryTreeBST(root));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
