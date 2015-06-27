# power-x-y.cc c49e1253eabe0a3ae66bcdfda69fd5c045b18077
import sys
import random


def compare(a, b):
    return abs((a - b) / b) < 0.00001


# @include
def power_x_y(x, y):
    result = 1
    power = y
    if y < 0:
        power, x = -power, 1/x
    while power:
        if power & 1:
            result *= x
        x *= x
        power >>= 1
    return result
# @exclude


def main():
    if len(sys.argv) == 3:
        x, y = float(sys.argv[1]), int(sys.argv[2])
        print(sys.argv[1]+"^"+sys.argv[2], )
        print("power_x_y():", power_x_y(x, y), ", x**y:", x**y)
        assert compare(power_x_y(x, y), x ** y)
    else:
        for _ in range(10000):
            x = random.uniform(0.0, 10.0)
            y = random.randint(-128, 128)
            print("%f^%d"%(x, y), )
            print("power_x_y():", power_x_y(x, y), ", x**y:", x**y)
            assert compare(power_x_y(x, y), x ** y)


if __name__ == '__main__':
    main()
