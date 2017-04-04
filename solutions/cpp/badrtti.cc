#include <cstdlib>
#include <iostream>
#include <typeinfo>

using std::cout;
using std::endl;

//@include
class A {
 public:
  virtual void foo() { cout << "A's foo" << endl; }
};

class B : public A {
 public:
  void foo() override { cout << "B's foo" << endl; }
  virtual void bar() { cout << "B's bar" << endl; }
};

class C : public B {
 public:
  void bar() override { cout << "C's bar" << endl; }
  void widget() { cout << "C's widget" << endl; }
};

void bad(A* x) {
  if (typeid(*x) == typeid(A)) {
    x->foo();
  } else if (typeid(*x) == typeid(B)) {
    ((B*)x)->foo();
    ((B*)x)->bar();
  } else if (typeid(*x) == typeid(C)) {
    ((C*)x)->foo();
    ((C*)x)->bar();
    ((C*)x)->widget();
  }
}

//@exclude

A* randomBuilder() {
  // int r = random()%3;
  int r = 2;
  if (r == 0) {
    return new A();
  } else if (r == 1) {
    cout << "returning a B" << endl;
    return new B();
  } else if (r == 2) {
    return new C();
  } else {
    return new C();
  }
}

// @include
int main() {
  // Randomly returns a pointer to  an A, B, or C type object.
  A* x = randomBuilder();
  bad(x);
}
// @exclude
