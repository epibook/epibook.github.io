from stack_with_max import Stack


# @include
class QueueWithMax:

    def __init__(self):
        self._enqueue = Stack()
        self._dequeue = Stack()

    def enqueue(self, x):
        self._enqueue.push(x)

    def dequeue(self):
        if self._dequeue.empty():
            while not self._enqueue.empty():
                self._dequeue.push(self._enqueue.pop())
        if not self._dequeue.empty():
            return self._dequeue.pop()
        raise IndexError('empty queue')

    def max(self):
        if not self._enqueue.empty():
            return self._enqueue.max() if self._dequeue.empty() else max(
                self._enqueue.max(), self._dequeue.max())
        if not self._dequeue.empty():
            return self._dequeue.max()
        raise IndexError('empty queue')
# @exclude


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
    maxint = 2**64 - 1
    Q.enqueue(maxint - 1)
    Q.enqueue(maxint)
    Q.enqueue(-2)
    Q.enqueue(-1)
    Q.enqueue(-1)
    minint = -2**64
    Q.enqueue(minint)
    assert maxint == Q.max()
    assert maxint - 1 == Q.dequeue()
    assert maxint == Q.max()
    assert maxint == Q.dequeue()
    assert -1 == Q.max()
    assert -2 == Q.dequeue()
    assert -1 == Q.max()
    assert -1 == Q.dequeue()
    assert -1 == Q.dequeue()
    assert minint == Q.max()
    assert minint == Q.dequeue()
    try:
        print('Q is empty, max() call should except = ' % Q.max())
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
        assert False
    except IndexError as e:
        print(e)  # throw


if __name__ == '__main__':
    main()
