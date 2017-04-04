import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.Random;

import static com.epi.BinaryTreeUtils.generateRandBinaryTree;

public class IsBinaryTreeABST {
  // @include
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean areKeysInRange(BinaryTreeNode<Integer> tree,
                                        Integer lower, Integer upper) {
    if (tree == null) {
      return true;
    } else if (Integer.compare(tree.data, lower) < 0
               || Integer.compare(tree.data, upper) > 0) {
      return false;
    }

    return areKeysInRange(tree.left, lower, tree.data)
        && areKeysInRange(tree.right, tree.data, upper);
  }
  // @exclude

  private static BinaryTreeNode<Integer> prev = null;

  private static boolean isBinaryTreeBSTAlternative(
      BinaryTreeNode<Integer> tree) {
    prev = null;
    return inorderTraversal(tree);
  }

  private static boolean inorderTraversal(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    } else if (!inorderTraversal(tree.left)) {
      return false;
    } else if (prev != null && prev.data > tree.data) {
      return false;
    }
    prev = tree;
    return inorderTraversal(tree.right);
  }

  private static void test() {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    assert(isBinaryTreeBST(tree));
    assert(isBinaryTreeBSTAlternative(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.data = 10;
    assert(!isBinaryTreeBST(tree));
    assert(!isBinaryTreeBSTAlternative(tree));
    assert(isBinaryTreeBST(null));
    assert(isBinaryTreeBSTAlternative(null));
  }

  public static void main(String[] args) {
    test();
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(5);
      }
      BinaryTreeNode<Integer> root = generateRandBinaryTree(n, false);
      assert(isBinaryTreeBST(root) == isBinaryTreeBSTAlternative(root));
    }
  }
}
