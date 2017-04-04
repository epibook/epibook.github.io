import itertools
import sys
import random
import collections
import fractions

# @include
Point = collections.namedtuple("Point", ("x", "y"))
Rational = collections.namedtuple("Rational", ("numerator", "denominator"))


# Line function of two points, a and b, and the equation is
# y = x(b.y - a.y) / (b.x - a.x) + (b.x * a.y - a.x * b.y) / (b.x - a.x).
class Line:

    def __init__(self, a, b):
        def get_canonical_form(a, b):
            gcd = fractions.gcd(abs(a), abs(b))
            a //= gcd
            b //= gcd
            return Rational(-a, -b) if b < 0 else Rational(a, b)

        # slope is a rational number. Note that if the line is parallel to
        # y-axis that we store 1/0.
        self.slope = (get_canonical_form(b.y - a.y, b.x - a.x)
                      if a.x != b.x else Rational(1, 0))
        # intercept is a rational number for the y-intercept unless the line is
        # parallel to y-axis in which case it is the x-intercept.
        self.intercept = (get_canonical_form(b.x * a.y - a.x * b.y, b.x - a.x)
                          if a.x != b.x else Rational(a.x, 1))

    def __eq__(self, other):
        return self.slope == other.slope and self.intercept == other.intercept

    def __hash__(self):
        return hash(self.slope) ^ hash(self.intercept)
# @exclude

    def __repr__(self):
        return ' '.join(
            map(str, (self.slope.numerator, self.slope.denominator,
                      self.intercept.numerator, self.intercept.denominator)))


# @include


def find_line_with_most_points(P):
    # Add all possible lines into hash table.
    table = collections.defaultdict(set)
    for a, b in itertools.combinations(P, 2):
        # Add points a and b into the set of points on this line.
        table[Line(a, b)].update([a, b])
    # @exclude
    line_max_points = max(table.items(), key=lambda x: len(x[1]))
    res = check(P)
    assert res == len(line_max_points[1])
    # @include
    return max(table.items(), key=lambda x: len(x[1]))[0]
# @exclude


def check(P):
    # n^3 checking
    max_count = 0
    for i in range(len(P) - 1):
        for j in range(i + 1, len(P)):
            count = 2
            temp = Line(P[i], P[j])
            for k in range(j + 1, len(P)):
                if Line(P[i], P[k]) == temp:
                    count += 1
            max_count = max(max_count, count)
    return max_count


def main():
    for times in range(100):
        print(times)
        n = int(sys.argv[1]) if len(sys.argv) == 2 else random.randint(2, 100)
        points = []
        t = set()
        while len(t) < n:
            p = Point(random.randint(0, 999), random.randint(0, 999))
            if p not in t:
                points.append(p)
                t.add(p)
        # print(points)
        l = find_line_with_most_points(points)
        print(l)


if __name__ == '__main__':
    main()
