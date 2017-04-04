#include <cassert>

class Foo {
 public:
  int readA() { return a; }
  Foo() : a(123) {}

 private:
  int a;
};

int main() {
  //@include
  Foo x;  // x is initialized by the default constructor
  //@exclude
  assert(x.readA() == 123);
}
