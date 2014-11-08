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
bool IsIntersect(const Rectangle& R, const Rectangle& S);

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

Rectangle IntersectRectangle(const Rectangle& R, const Rectangle& S) {
  if (IsIntersect(R, S)) {
    return {max(R.x, S.x), max(R.y, S.y),
            min(R.x + R.width, S.x + S.width) - max(R.x, S.x),
            min(R.y + R.height, S.y + S.height) - max(R.y, S.y)};
  } else {
    return {0, 0, -1, -1};  // No intersection.
  }
}

bool IsIntersect(const Rectangle& R, const Rectangle& S) {
  return R.x <= S.x + S.width && R.x + R.width >= S.x &&
         R.y <= S.y + S.height && R.y + R.height >= S.y;
}
// @exclude

int main(int argc, char* argv[]) {
  for (int times = 0; times < 10000; ++times) {
    Rectangle R, S;
    if (argc == 9) {
      R.x = atoi(argv[1]), R.y = atoi(argv[2]), R.width = atoi(argv[3]),
      R.height = atoi(argv[4]);
      S.x = atoi(argv[5]), S.y = atoi(argv[6]), S.width = atoi(argv[7]),
      S.height = atoi(argv[8]);
    } else {
      default_random_engine gen((random_device())());
      uniform_int_distribution<int> dis(1, 100);
      R.x = dis(gen), R.y = dis(gen), R.width = dis(gen), R.height = dis(gen);
      S.x = dis(gen), S.y = dis(gen), S.width = dis(gen), S.height = dis(gen);
    }
    // Intersect rectangle.
    bool res = IsIntersect(R, S);
    cout << boolalpha << IsIntersect(R, S) << endl;
    Rectangle ans = IntersectRectangle(R, S);
    ans.print("ans: ");
    assert(res == false || (ans.width >= 0 && ans.height >= 0));
  }
  return 0;
}
