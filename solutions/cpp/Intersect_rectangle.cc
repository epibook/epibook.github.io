// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <cassert>
#include <random>
#include <string>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::min;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

struct Rectangle;
bool IsIntersect(const Rectangle&, const Rectangle&);

// @include
struct Rectangle {
  int x, y, width, height;
  // @exclude
  void print(string s) const {
    cout << s << this->x << ' ' << this->y << ' ' << this->width << ' '
         << this->height << endl;
  }
  // @include
};

Rectangle IntersectRectangle(const Rectangle& R1, const Rectangle& R2) {
  if (IsIntersect(R1, R2)) {
    return {max(R1.x, R2.x), max(R1.y, R2.y),
            min(R1.x + R1.width, R2.x + R2.width) - max(R1.x, R2.x),
            min(R1.y + R1.height, R2.y + R2.height) - max(R1.y, R2.y)};
  }
  return {0, 0, -1, -1};  // No intersection.
}

bool IsIntersect(const Rectangle& R1, const Rectangle& R2) {
  return R1.x <= R2.x + R2.width && R1.x + R1.width >= R2.x &&
         R1.y <= R2.y + R2.height && R1.y + R1.height >= R2.y;
}
// @exclude

int main(int argc, char* argv[]) {
  for (int times = 0; times < 10000; ++times) {
    Rectangle R1, R2;
    if (argc == 9) {
      R1.x = atoi(argv[1]), R1.y = atoi(argv[2]), R1.width = atoi(argv[3]),
      R1.height = atoi(argv[4]);
      R2.x = atoi(argv[5]), R2.y = atoi(argv[6]), R2.width = atoi(argv[7]),
      R2.height = atoi(argv[8]);
    } else {
      default_random_engine gen((random_device())());
      uniform_int_distribution<int> dis(1, 100);
      R1.x = dis(gen), R1.y = dis(gen), R1.width = dis(gen),
      R1.height = dis(gen);
      R2.x = dis(gen), R2.y = dis(gen), R2.width = dis(gen),
      R2.height = dis(gen);
    }
    // Intersect rectangle.
    bool res = IsIntersect(R1, R2);
    cout << boolalpha << IsIntersect(R1, R2) << endl;
    Rectangle ans = IntersectRectangle(R1, R2);
    ans.print("ans: ");
    assert(res == false || (ans.width >= 0 && ans.height >= 0));
  }
  return 0;
}
