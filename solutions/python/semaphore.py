# @include
import threading


class Semaphore():

    def __init__(self, max_available):
        self.cv = threading.Condition()
        self.MAX_AVAILABLE = max_available
        self.taken = 0

    def acquire(self):
        self.cv.acquire()
        while (self.taken == self.MAX_AVAILABLE):
            self.cv.wait()
        self.taken += 1
        self.cv.release()

    def release(self):
        self.cv.acquire()
        self.taken -= 1
        self.cv.notify()
        self.cv.release()

    # @exclude
    def num_taken(self):
        return self.taken

import time
import random


class Worker(threading.Thread):

    def __init__(self, name, semaphore):
        threading.Thread.__init__(self)
        self.semaphore = semaphore
        self.name = name

    def run(self):
        while True:
            self.semaphore.acquire()
            print(self.name +
                  " has acquired semaphore, the number taken is  " +
                  str(self.semaphore.taken) +
                  "\n")
            rnd_sleep = random.randint(200, 600) / 1000.0
            time.sleep(rnd_sleep)
            print(self.name + " is about to release acquired semaphore\n")
            self.semaphore.release()
            rnd_sleep = random.randint(2000, 6000) / 1000.0
            time.sleep(rnd_sleep)

if __name__ == '__main__':
    S = Semaphore(9)
    S.acquire()
    S.acquire()
    S.release()
    S.release()

    for i in range(10):
        w = Worker("worker-" + str(i), S)
        w.start()
