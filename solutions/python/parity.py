# Parity.cpp 848813e190b1b85a8e75107fe8513c3be38ad1a9
import sys
import random
import parity1
import parity2
import parity3
import parity4

def main():
    if len(sys.argv) == 2:
        x = int(sys.argv[1])
        print('x = %#x, parity = %d' % (x, parity4.parity(x)))
        assert parity1.parity(x) == parity4.parity(x)
        assert parity2.parity(x) == parity4.parity(x)
        assert parity3.parity(x) == parity4.parity(x)
    else:
        for _ in range(1000):
            x = random.randint(0, sys.maxsize)
            print('x = %#x, parity = %d' % (x, parity4.parity(x)))
            assert parity1.parity(x) == parity4.parity(x)
            assert parity2.parity(x) == parity4.parity(x)
            assert parity3.parity(x) == parity4.parity(x)

if __name__ == '__main__':
    main()
