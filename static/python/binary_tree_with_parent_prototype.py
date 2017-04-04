# Binary_tree_with_parent_prototype.h 848813e190b1b85a8e75107fe8513c3be38ad1a9
# @include


class BinaryTreeNode:

    def __init__(self, data=None, left=None, right=None, parent=None):
        self.data = data
        self.left = left
        self.right = right
        self.parent = parent

# @exclude

    def __repr__(self):
        return '%s <- %s -> %s' % (self.left and self.left.data, self.data,
                                   self.right and self.right.data)
