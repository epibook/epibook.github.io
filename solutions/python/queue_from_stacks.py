# @include
class Queue:

    def __init__(self):
        self._enq, self._deq = [], []

    def enqueue(self, x):
        self._enq.append(x)

    def dequeue(self):
        if not self._deq:
            # Transfers the elements in _enq to _deq.
            while self._enq:
                self._deq.append(self._enq.pop())

        if not self._deq:  # _deq is still empty!
            raise IndexError('empty queue')
        return self._deq.pop()
# @exclude


def main():
    Q = Queue()
    Q.enqueue(1)
    Q.enqueue(2)
    assert 1 == Q.dequeue()  # 1
    assert 2 == Q.dequeue()  # 2
    Q.enqueue(3)
    assert 3 == Q.dequeue()  # 3
    try:
        Q.dequeue()
        assert False
    except IndexError as e:
        print(e)  # throw
    Q.enqueue(-1)
    Q.enqueue(123)
    Q.enqueue(2**64 - 1)
    Q.enqueue(-2**64)
    Q.enqueue(0)
    assert -1 == Q.dequeue()
    Q.enqueue(0)
    assert 123 == Q.dequeue()
    assert 2**64 - 1 == Q.dequeue()
    assert -2**64 == Q.dequeue()
    assert 0 == Q.dequeue()
    assert 0 == Q.dequeue()
    try:
        Q.dequeue()
        assert False
    except IndexError as e:
        print(e)  # throw


if __name__ == '__main__':
    main()
