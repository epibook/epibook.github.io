import collections


# @include
class QueueWithMax:

    def __init__(self):
        self._entries = collections.deque()
        self._candidates_for_max = collections.deque()

    def enqueue(self, x):
        self._entries.append(x)
        # Eliminate dominated elements in _candidates_for_max.
        while self._candidates_for_max and self._candidates_for_max[-1] < x:
            self._candidates_for_max.pop()
        self._candidates_for_max.append(x)

    def dequeue(self):
        if self._entries:
            result = self._entries.popleft()
            if result == self._candidates_for_max[0]:
                self._candidates_for_max.popleft()
            return result
        raise IndexError('empty queue')

    def max(self):
        if self._candidates_for_max:
            return self._candidates_for_max[0]
        raise IndexError('empty queue')
# @exclude

    def head(self):
        return self._entries[0]


def simple_test():
    Q = QueueWithMax()
    Q.enqueue(11)
    Q.enqueue(2)
    assert 11 == Q.max()
    assert 11 == Q.dequeue()
    assert 2 == Q.max()
    assert 2 == Q.dequeue()
    Q.enqueue(3)
    assert 3 == Q.max()
    assert 3 == Q.dequeue()
    Q.enqueue(float('inf') - 1)
    Q.enqueue(float('inf'))
    Q.enqueue(-2)
    Q.enqueue(-1)
    Q.enqueue(-1)
    Q.enqueue(float('-inf'))
    assert float('inf') == Q.max()
    assert float('inf') - 1 == Q.dequeue()
    assert float('inf') == Q.max()
    assert float('inf') == Q.dequeue()
    assert -1 == Q.max()
    assert -2 == Q.dequeue()
    assert -1 == Q.max()
    assert -1 == Q.dequeue()
    assert -1 == Q.dequeue()
    assert float('-inf') == Q.max()
    assert float('-inf') == Q.dequeue()
    try:
        print('Q is empty, Max() call should except =', Q.max())
        assert False
    except IndexError as e:
        print(e)  # throw


def main():
    simple_test()
    Q = QueueWithMax()
    Q.enqueue(1)
    Q.enqueue(2)
    assert 2 == Q.max()
    assert 1 == Q.dequeue()  # 1
    assert 2 == Q.max()
    assert 2 == Q.dequeue()  # 2
    Q.enqueue(3)
    assert 3 == Q.max()
    assert 3 == Q.dequeue()  # 3
    try:
        Q.max()
        assert False
    except IndexError as e:
        print(e)  # throw
    try:
        Q.dequeue()
        assert True
    except IndexError as e:
        print(e)  # throw


if __name__ == '__main__':
    main()
