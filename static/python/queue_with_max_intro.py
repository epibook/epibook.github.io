# @include
class Queue:

    def __init__(self):
        self._data = []

    def enqueue(self, x):
        self._data.append(x)

    def dequeue(self):
        return self._data.pop(0)

    def max(self):
        return max(self._data)
# @exclude


def simple_test():
    Q = Queue()
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
    except ValueError as e:
        print(e)  # throw


def main():
    simple_test()
    Q = Queue()
    Q.enqueue(1)
    Q.enqueue(2)
    assert 2 == Q.max()
    assert 1 == Q.dequeue()
    assert 2 == Q.max()
    assert 2 == Q.dequeue()
    Q.enqueue(3)
    assert 3 == Q.max()
    assert 3 == Q.dequeue()
    try:
        Q.max()
        assert False
    except ValueError as e:
        print(e)
    try:
        Q.dequeue()
        assert False
    except IndexError as e:
        print(e)


if __name__ == '__main__':
    main()
