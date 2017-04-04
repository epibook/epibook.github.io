import sys
import threading

# @include
N = 1000000
counter = 0


def increment_thread():
    global counter
    for _ in range(N):
        counter = counter + 1

t1 = threading.Thread(target=increment_thread)
t2 = threading.Thread(target=increment_thread)

t1.start()
t2.start()
t1.join()
t2.join()

print(counter)
# @exclude
