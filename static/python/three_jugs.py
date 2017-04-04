import sys
import random
import collections

# @include
Jug = collections.namedtuple('Jug', ('low', 'high'))


def check_feasible(jugs, L, H, c=set()):
    VolumeRange = collections.namedtuple('VolumeRange', ('low', 'high'))
    if L > H or VolumeRange(L, H) in c or (L < 0 and H < 0):
        return False

    # Checks the volume for each jug to see if it is possible.
    if any((L <= j.low and j.high <= H) or check_feasible(jugs, L - j.low,
                                                          H - j.high)
           for j in jugs):
        return True
    c.add(VolumeRange(L, H))  # Marks this as impossible.
    return False
# @exclude


def main():
    jugs = [Jug(230, 240), Jug(290, 310), Jug(500, 515)]
    assert check_feasible(jugs, 2100, 2300) is True
    jugs.clear()
    n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(1, 100)
    for i in range(n):
        low = random.randint(0, 999)
        high = random.randint(low + 1, low + 20)
        jugs.append(Jug(high, low))
    l = random.randint(0, 9999)
    h = random.randint(l + 1, l + 5000)
    print(check_feasible(jugs, l, h))


if __name__ == '__main__':
    main()
