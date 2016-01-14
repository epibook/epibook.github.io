package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniqueBinaryTreesAll {
  // @include
  public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(
      int numNodes) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (numNodes == 0) { // Empty tree, add as an null.
      result.add(null);
    }

    for (int numLeftTreeNodes = 0; numLeftTreeNodes < numNodes;
         ++numLeftTreeNodes) {
      int numRightTreeNodes = numNodes - 1 - numLeftTreeNodes;
      List<BinaryTreeNode<Integer>> leftSubtrees
          = generateAllBinaryTrees(numLeftTreeNodes);
      List<BinaryTreeNode<Integer>> rightSubtrees
          = generateAllBinaryTrees(numNodes - 1 - numLeftTreeNodes);
      // Generates all combinations of leftSubtrees and rightSubtrees.
      for (BinaryTreeNode<Integer> left : leftSubtrees) {
        for (BinaryTreeNode<Integer> right : rightSubtrees) {
          result.add(new BinaryTreeNode<>(0, left, right));
        }
      }
    }
    return result;
  }
  // @exclude

  private static void smallTest() {
    assert generateAllBinaryTrees(1).size() == 1;
    assert generateAllBinaryTrees(2).size() == 2;
    assert generateAllBinaryTrees(3).size() == 5;
    assert generateAllBinaryTrees(4).size() == 14;
    assert generateAllBinaryTrees(5).size() == 42;
    assert generateAllBinaryTrees(10).size() == 16796;
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
    }
    System.out.println("n = " + n);
    generateAllBinaryTrees(n);
  }
}
