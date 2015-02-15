package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.BinaryTreeUtils.generateInOrder;

public class UniqueBinaryTreesAll {
  // @include
  public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int n) {
    return generateAllBinaryTreesHelper(1, n);
  }

  private static List<BinaryTreeNode<Integer>> generateAllBinaryTreesHelper(
      int start, int end) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (start > end) {
      result.add(null);
      return result;
    }

    for (int i = start; i <= end; ++i) {
      // Tries all possible combinations of left subtrees and right subtrees.
      List<BinaryTreeNode<Integer>> leftresult =
          generateAllBinaryTreesHelper(start, i - 1);
      List<BinaryTreeNode<Integer>> rightresult =
          generateAllBinaryTreesHelper(i + 1, end);
      for (BinaryTreeNode<Integer> left : leftresult) {
        for (BinaryTreeNode<Integer> right : rightresult) {
          result.add(new BinaryTreeNode<>(i, left, right));
        }
      }
    }
    return result;
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
    List<BinaryTreeNode<Integer>> result = generateAllBinaryTrees(n);
    for (BinaryTreeNode<Integer> tree : result) {
      List<Integer> sequence = generateInOrder(tree);
      for (int i = 1; i < sequence.size(); i++) {
        assert (sequence.get(i - 1) < sequence.get(i));
      }
    }
  }
}
