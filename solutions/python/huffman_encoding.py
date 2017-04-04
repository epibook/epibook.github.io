import collections
import sys
import random
import heapq

ENGLISH_FREQ = (8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
                0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987,
                6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974, 0.074)

# @include
CharWithFrequency = collections.namedtuple('CharWithFrequency', ('c', 'freq'))


def huffman_encoding(symbols):
    class BinaryTreeNode:
        def __init__(self, aggregate_freq, s, left=None, right=None):
            self.aggregate_freq = aggregate_freq
            self.s = s
            self.left, self.right = left, right

        def __lt__(self, other):
            return self.aggregate_freq <= other.aggregate_freq
    # @exclude

        def symbols(self):
            return self.left.symbols() + self.right.symbols(
            ) if self.s is None else self.s.c

        def __repr__(self):
            return '%s <- %r(%g) -> %s' % (self.left and self.left.symbols(),
                                           self.symbols(), self.aggregate_freq,
                                           self.right and self.right.symbols())

    # @include
    # Initially assigns each symbol into candidates.
    candidates = [BinaryTreeNode(s.freq, s) for s in symbols]
    heapq.heapify(candidates)

    # Keeps combining two nodes until there is one node left.
    while len(candidates) > 1:
        left, right = heapq.heappop(candidates), heapq.heappop(candidates)
        heapq.heappush(candidates,
                       BinaryTreeNode(left.aggregate_freq +
                                      right.aggregate_freq, None, left, right))

    def assign_huffman_code(tree, code):
        if tree:
            if tree.s:
                # This node is a leaf.
                result[tree.s.c] = ''.join(code)
            else:  # Non-leaf node.
                code.append('0')
                assign_huffman_code(tree.left, code)
                code[-1] = '1'
                assign_huffman_code(tree.right, code)
                del code[-1]

    result = {}
    # Traverses the binary tree, assigning codes to nodes.
    assign_huffman_code(candidates[0], [])
    return result
# @exclude


def main():
    if len(sys.argv) == 2:
        n = int(sys.argv[1]) if sys.argv[1] != 'huffman' else 26
    else:
        n = random.randint(1, 255)
    symbols = []
    sum_ = 0
    if len(sys.argv) == 1 or sys.argv[1] != 'huffman':
        temp_symbols = []
        for i in range(n):
            t = CharWithFrequency(chr(i), random.randint(0, 100000))
            sum_ += t.freq
            temp_symbols.append(t)
        for symbol in temp_symbols:
            symbols.append(CharWithFrequency(symbol.c, symbol.freq / sum_))
    else:
        for i in range(n):
            t = CharWithFrequency(chr(ord('a') + i), ENGLISH_FREQ[i])
            symbols.append(t)
    result = huffman_encoding(symbols)
    avg = 0.0
    for symbol in symbols:
        print(symbol.c, symbol.freq, result[symbol.c])
        avg += symbol.freq / 100 * len(result[symbol.c])
    print('average huffman code length =', avg)


if __name__ == '__main__':
    main()
