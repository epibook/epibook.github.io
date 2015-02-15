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

  public static boolean isBinaryTreeBST(BinaryTree<Integer> tree) {
    LinkedList<QNode> BFSQueue = new LinkedList<>();
    BFSQueue.addLast(new QNode(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

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
    BinaryTree<Integer> tree = new BinaryTree<>(3);
    tree.setLeft(new BinaryTree<>(2));
    tree.getLeft().setLeft(new BinaryTree<>(1));
    tree.setRight(new BinaryTree<>(5));
    tree.getRight().setLeft(new BinaryTree<>(4));
    tree.getRight().setRight(new BinaryTree<>(6));
    // should output true.
    assert isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.setData(10);
    // should output false.
    assert !isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
