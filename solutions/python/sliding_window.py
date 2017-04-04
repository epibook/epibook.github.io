from queue_with_max_alternative import QueueWithMax


# @include
class TrafficElement:
    def __init__(self, time, volume):
        self.time = time
        self.volume = volume

    # Following operators are needed for QueueWithMax with maximum.
    def __lt__(self, other):
        return self.volume < other.volume

    def __eq__(self, other):
        return self.time == other.time and self.volume == other.volume


def calculate_traffic_volumes(A, w):
    sliding_window = QueueWithMax()
    maximum_volumes = []
    for traffic_info in A:
        sliding_window.enqueue(traffic_info)
        while traffic_info.time - sliding_window.head().time > w:
            sliding_window.dequeue()
        maximum_volumes.append(
            TrafficElement(traffic_info.time, sliding_window.max().volume))
    return maximum_volumes
# @exclude


def main():
    w = 3
    A = [
        TrafficElement(0, 1.3), TrafficElement(2, 2.5), TrafficElement(3, 3.7),
        TrafficElement(5, 1.4), TrafficElement(6, 2.6), TrafficElement(8, 2.2),
        TrafficElement(9, 1.7), TrafficElement(14, 1.1)
    ]
    result = calculate_traffic_volumes(A, w)
    golden = [
        TrafficElement(0, 1.3), TrafficElement(2, 2.5), TrafficElement(3, 3.7),
        TrafficElement(5, 3.7), TrafficElement(6, 3.7), TrafficElement(8, 2.6),
        TrafficElement(9, 2.6), TrafficElement(14, 1.1)
    ]
    assert golden == result


if __name__ == '__main__':
    main()
