import sys
import threading

# @include
N = 0
counter = 0

def increment_thread():
    global counter, N
    for i in range(N):
        counter += 1

def main():
    global counter, N
    N = int(sys.argv[1]) if len(sys.argv) > 1 else 1000000
    T1 = threading.Thread(target=increment_thread)
    T2 = threading.Thread(target=increment_thread)

    T1.start()
    T2.start()
    T1.join()
    T2.join()
  
    print(counter)
# @exclude

if __name__ == '__main__':
    main()
