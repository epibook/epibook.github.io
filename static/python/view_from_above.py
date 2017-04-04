import bintrees
import collections

# @include
LineSegment = collections.namedtuple('LineSegment',
                                     ('left', 'right', 'color', 'height'))


def calculate_view_from_above(A):
    class Endpoint:
        def __init__(self, is_left, line):
            self.is_left = is_left
            self.line = line

        def __lt__(self, other):
            return self.value() < other.value()

        def value(self):
            return self.line.left if self.is_left else self.line.right

    sorted_endpoints = sorted([Endpoint(True, a)
                               for a in A] + [Endpoint(False, a) for a in A])
    result = []
    prev_xaxis = sorted_endpoints[0].value()  # Leftmost end point.
    prev = None
    active_line_segments = bintrees.RBTree()
    for endpoint in sorted_endpoints:
        if active_line_segments and prev_xaxis != endpoint.value():
            active_segment = active_line_segments.max_item()[1]
            if prev is None:  # Found first segment.
                prev = LineSegment(prev_xaxis,
                                   endpoint.value(), active_segment.color,
                                   active_segment.height)
            else:
                if (prev.height == active_segment.height and
                        prev.color == active_segment.color and
                        prev_xaxis == prev.right):
                    prev = prev._replace(right=endpoint.value())
                else:
                    result.append(prev)
                    prev = LineSegment(prev_xaxis,
                                       endpoint.value(), active_segment.color,
                                       active_segment.height)
        prev_xaxis = endpoint.value()

        if endpoint.is_left:  # Left end point.
            active_line_segments[endpoint.line.height] = endpoint.line
        else:  # Right end point.
            del active_line_segments[endpoint.line.height]

    # Output the remaining segment (if any).
    return result + [prev] if prev else result
# @exclude


def simple_test():
    A = [LineSegment(1, 2, 0, 1), LineSegment(3, 4, 0, 1)]
    result = calculate_view_from_above(A)
    golden_result = [LineSegment(1, 2, 0, 1), LineSegment(3, 4, 0, 1)]
    assert result == golden_result


def main():
    simple_test()
    A = [
        LineSegment(0, 4, 0, 0), LineSegment(1, 3, 1, 2),
        LineSegment(2, 7, 2, 1), LineSegment(4, 5, 3, 3),
        LineSegment(5, 7, 3, 0), LineSegment(6, 10, 0, 2),
        LineSegment(8, 9, 0, 1), LineSegment(9, 18, 4, 0),
        LineSegment(11, 13, 3, 2), LineSegment(12, 15, 4, 1),
        LineSegment(14, 15, 2, 2), LineSegment(16, 17, 3, 2)
    ]
    result = calculate_view_from_above(A)
    golden_result = [
        LineSegment(0, 1, 0, 0), LineSegment(1, 3, 1, 2),
        LineSegment(3, 4, 2, 1), LineSegment(4, 5, 3, 3),
        LineSegment(5, 6, 2, 1), LineSegment(6, 10, 0, 2),
        LineSegment(10, 11, 4, 0), LineSegment(11, 13, 3, 2),
        LineSegment(13, 14, 4, 1), LineSegment(14, 15, 2, 2),
        LineSegment(15, 16, 4, 0), LineSegment(16, 17, 3, 2),
        LineSegment(17, 18, 4, 0)
    ]
    assert result == golden_result


if __name__ == '__main__':
    main()
