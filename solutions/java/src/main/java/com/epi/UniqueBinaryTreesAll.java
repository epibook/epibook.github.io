package com.epi;

import static com.epi.BinaryTreeUtils.generateInOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

public class UniqueBinaryTreesAll {
  // @include
  public static List<BinaryTree<Integer>> generateAllBinaryTrees(int n) {
    return generateAllBinaryTreesHelper(1, n);
  }

  private static List<BinaryTree<Integer>> generateAllBinaryTreesHelper(
      int start, int end) {
    List<BinaryTree<Integer>> res = new ArrayList<BinaryTree<Integer>>();
    if (start > end) {
      res.add(null);
      return res;
    }

    for (int i = start; i <= end; ++i) {
      // Try all possible combinations of left subtrees and right subtrees.
      List<BinaryTree<Integer>> leftRes = generateAllBinaryTreesHelper(start,
          i - 1);
      List<BinaryTree<Integer>> rightRes = generateAllBinaryTreesHelper(i + 1,
          end);
      for (BinaryTree<Integer> left : leftRes) {
        for (BinaryTree<Integer> right : rightRes) {
          // Use of unique_ptr means that we do not have tree nodes shared
          // across distinct trees.
          res.add(new BinaryTree<Integer>(i, left, right));
        }
      }
    }
    return res;
  }

  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
    }
    System.out.println("n = " + n);
    List<BinaryTree<Integer>> res = generateAllBinaryTrees(n);
    for (BinaryTree<Integer> tree : res) {
      List<Integer> sequence = generateInOrder(tree);
      for (int i = 1; i < sequence.size(); i++) {
        assert (sequence.get(i - 1) < sequence.get(i));
      }
    }
  }
}
