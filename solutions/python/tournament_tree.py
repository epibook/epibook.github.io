# @include
class TreeNode:

    def __init__(self, cap):
        self.cap = cap  # Leaf: remaining capacity in the box.
        # Non-leaf: max remaining capacity in the subtree.
        self.items = []  # Stores the items in the leaf node.
# @exclude

    def __repr__(self):
        return '%g(%s)' % (self.cap, ','.join(map(str, self.items)))


# @include


class TournamentTree:
    # n items, and each box has unit_cap.

    def __init__(self, n, unit_cap):
        # Stores the complete binary tree. For _tree[i],
        # left subtree is _tree[2i + 1], and right subtree is _tree[2i + 2].
        # Complete binary tree with n leafs has 2n - 1 nodes.
        self._tree = [TreeNode(unit_cap) for i in range(n * 2 - 1)]

    def insert(self, item, item_cap):
        self.insert_helper(0, item, item_cap)

    # @exclude
    def print_leaf(self):
        for i, node in enumerate(self._tree):
            print('i = %d, cap = %g' % (i, node.cap))
            print(*node.items)

    # @include
    # Recursively inserts item in tournament tree.
    def insert_helper(self, idx, item, cap):
        left = (idx * 2) + 1
        right = (idx * 2) + 2
        if left < len(self._tree):  # _tree[idx] is an internal node.
            self.insert_helper(left if self._tree[left].cap >= cap else right,
                               item, cap)
            self._tree[idx].cap = max(self._tree[left].cap,
                                      self._tree[right].cap)
        else:  # _tree[idx] is a leaf node.
            self._tree[idx].cap -= cap
            self._tree[idx].items.append(item)


# @exclude


def main():
    # Following is the example in the book.
    t = TournamentTree(6, 1.0)
    t.insert(0, 0.60)
    t.insert(1, 0.60)
    t.insert(2, 0.55)
    t.insert(3, 0.80)
    t.insert(4, 0.50)
    t.insert(5, 0.45)
    # Due to the precision error of floating point number, item 5 will be
    # inserted into 5-th box. however, if we are not using floating point
    # number, everything is fine.
    t.print_leaf()


if __name__ == '__main__':
    main()
