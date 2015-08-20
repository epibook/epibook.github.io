#include <cmath>
#include <limits>
#include <cfloat>
#include <cstdlib>
#include <ctime>
#include <cstdio>
#include <cassert>

// does not converge consistently for smaller values, e.g., 0.000000000001
// #define TOLERANCE 0.000000001
// this is 2^{-52}
// #define MIN_DIFFERENCE 0.00000000000000022204

using namespace std;

// @include
// 0 means equal, -1 means smaller, 1 means larger
int compare(double a, double b) {
  // Use normalization for precision problem
  double diff = (a - b) / b;
  return diff < -numeric_limits<double>::epsilon()
             ? -1
             : diff > numeric_limits<double>::epsilon();
}

double division_no_operator(double x, double y) {
  double left, right;
  if (y < 1) {
    left = x, right = numeric_limits<double>::max();
  } else {
    left = 0, right = x;
  }

  while (compare(left, right) <= 0) {  // left <= right
    double mid = left + 0.5 * (right - left);
    if (compare(x, mid * y) == 0) {  // x == mid * y, found answer
      return mid;
    } else if (compare(x, mid * y) > 0) {  // x > mid * y
      left = mid;
    } else {  // x < mid * y
      right = mid;
    }
  }
  return left;
}
// @exclude

int main(int argc, char *argv[]) {
  if (argc == 3) {
    double x = atof(argv[1]), y = atof(argv[2]);
    double res = division_no_operator(x, y);
    printf("x=%lf,\ty = %lf,\tres = %lf,\tabs( y*res - x ) = %lf\n", x, y, res,
           fabs(y * res - x));
    assert(compare(y * res, x) == 0);
  } else {
    // random test, only works if the product not greater than 2^32 - 1
    srand(time(nullptr));
    int i;
    for (i = 0; i < 100000; ++i) {
      double x = rand() % 65536, y = rand() % 65536;
      if (y == 0) {
        continue;
      }
      double res = division_no_operator(x, y);
      printf(
          "x=%10.0lf,\ty = %10.0lf,\tres = %10.20lf,\tabs( y*res - x ) = "
          "%.20lf\n",
          x, y, res, fabs(y * res - x));
      assert(compare(y * res, x) == 0);
    }
  }
  return 0;
}
