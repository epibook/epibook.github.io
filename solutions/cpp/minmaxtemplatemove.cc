#include <cassert>
#include <iostream>

// @include
template <typename T>
void minmax(T& x, T& y) {
  if (x > y) {
    T tmp = std::move(x);
    x = std::move(y);
    y = std::move(tmp);
    // Alternative: call std::swap(x, y)
  }
}
// @exclude

int main(int argc, char** argv) {
  int x = 20;
  int y = 10;
  std::cout << x << std::endl;
  std::cout << y << std::endl;
  minmax(x, y);
  std::cout << x << std::endl;
  std::cout << y << std::endl;
  assert(x == 10 && y == 20);
}
