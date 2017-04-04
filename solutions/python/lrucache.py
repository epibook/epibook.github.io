import collections


# @include
class LRUCache:

    def __init__(self, capacity):
        self._isbn_price_table = collections.OrderedDict()
        self._capacity = capacity

    def lookup(self, isbn):
        if isbn not in self._isbn_price_table:
            return False, None
        price = self._isbn_price_table.pop(isbn)
        self._isbn_price_table[isbn] = price
        return True, price

    def insert(self, isbn, price):
        # We add the value for key only if key is not present - we don't update
        # existing values.
        if isbn in self._isbn_price_table:
            price = self._isbn_price_table.pop(isbn)
        elif self._capacity <= len(self._isbn_price_table):
            self._isbn_price_table.popitem(last=False)
        self._isbn_price_table[isbn] = price

    def erase(self, isbn):
        return self._isbn_price_table.pop(isbn, None) is not None
# @exclude


def main():
    k_capacity = 2
    c = LRUCache(k_capacity)
    print('c.insert(1, 1)')
    c.insert(1, 1)
    print('c.insert(1, 10)')
    c.insert(1, 10)
    print('c.lookup(2)')
    assert c.lookup(2) == (False, None)
    print('c.lookup(1)')
    assert c.lookup(1) == (True, 1)
    c.erase(1)
    assert c.lookup(1) == (False, None)

    # test capacity constraints honored, also FIFO ordering
    c = LRUCache(k_capacity)
    c.insert(1, 1)
    c.insert(2, 1)
    c.insert(3, 1)
    c.insert(4, 1)
    assert c.lookup(1) == (False, None)
    assert c.lookup(2) == (False, None)
    assert c.lookup(3) == (True, 1)
    assert c.lookup(4) == (True, 1)

    # test retrieval moves to front
    c = LRUCache(k_capacity)
    c.insert(1, 1)
    c.insert(2, 1)
    c.insert(3, 1)
    c.lookup(2)
    c.insert(4, 1)
    assert c.lookup(1) == (False, None)
    assert c.lookup(2) == (True, 1)
    assert c.lookup(3) == (False, None)
    assert c.lookup(4) == (True, 1)

    # test update moves to front
    c = LRUCache(k_capacity)
    c.insert(1, 1)
    c.insert(2, 1)
    c.insert(3, 1)
    c.insert(2, 2)
    c.insert(4, 1)
    assert c.lookup(1) == (False, None)
    assert c.lookup(2) == (True, 1)
    assert c.lookup(3) == (False, None)
    assert c.lookup(4) == (True, 1)

    # test remove
    c = LRUCache(k_capacity)
    c.insert(1, 1)
    c.insert(2, 1)
    c.erase(2)
    c.insert(3, 3)
    assert c.lookup(1) == (True, 1)
    assert c.lookup(2) == (False, None)
    assert c.lookup(3) == (True, 3)


if __name__ == '__main__':
    main()
