#include <cassert>

// @include
template <typename T>
void minmax(T& x, T& y) {
  if (x > y) {
    T tmp = x;
    x = y;
    y = tmp;
  }
}
// @exclude

int main(int argc, char** argv) {
  int x = 20;
  int y = 10;
  minmax(x, y);
  assert(x == 10 && y == 20);
}
