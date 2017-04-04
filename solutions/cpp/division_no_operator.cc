#include <cassert>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <iostream>

using namespace std;

// @include
int division_no_operator(int x, int y) {
  int left = 0, right = x;
  while (left <= right) {
    int mid = left + ((right - left) / 2);
    if (mid * y < x) {
      left = mid + 1;
    } else if (mid * y == x) {
      return mid;
    } else {
      right = mid - 1;
    }
  }
  return left;
}
// @exclude

int main(int argc, char *argv[]) {
  if (argc == 3) {
    int x = atoi(argv[1]), y = atoi(argv[2]);
    int res = division_no_operator(x, y);
    cout << x << '/' << y << '=' << res << endl;
    assert(ceil(static_cast<double>(x) / y) == res);
  } else {
    // random test, only works if the product not greater than 2^32 - 1
    srand(time(NULL));
    for (int i = 0; i < 10000; ++i) {
      int x = rand() % 65536, y = rand() % 65536;
      int res = division_no_operator(x, y);
      cout << x << '/' << y << '=' << res << endl;
      assert(ceil(static_cast<double>(x) / y) == res);
    }
  }
  return 0;
}
