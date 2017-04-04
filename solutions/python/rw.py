import time
import random
import threading


def do_something_else():
    time.sleep(random.random())


# @include
# LR and LW are class attributes in the RW class.
# They serve as read and write locks. The integer
# variable read_count in RW tracks the number of readers.
class Reader(threading.Thread):
    # @exclude

    def __init__(self, name):
        super().__init__(name=name, daemon=True)
# @include

    def run(self):
        while True:
            with RW.LR:
                RW.read_count += 1

# @exclude
            print('Reader', self.name, 'is about to read')
            # @include
            print(RW.data)
            with RW.LR:
                RW.read_count -= 1
                RW.LR.notify()
            do_something_else()


class Writer(threading.Thread):
    # @exclude

    def __init__(self, name):
        super().__init__(name=name, daemon=True)
# @include

    def run(self):
        while True:
            with RW.LW:
                done = False
                while not done:
                    with RW.LR:
                        if RW.read_count == 0:
                            # @exclude
                            print('Writer', self.name, 'is about to write')
                            # @include
                            RW.data += 1
                            done = True
                        else:
                            # use wait/notify to avoid busy waiting
                            while RW.read_count != 0:
                                RW.LR.wait()
            do_something_else()
# @exclude


class RW:
    data = 0
    LR = threading.Condition()
    read_count = 0
    LW = threading.Lock()


def main():
    r0 = Reader('r0')
    r1 = Reader('r1')
    w0 = Writer('w0')
    w1 = Writer('w1')
    r0.start()
    r1.start()
    w0.start()
    w1.start()
    time.sleep(10)


if __name__ == '__main__':
    main()
