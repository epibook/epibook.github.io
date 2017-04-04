#include <cstdlib>
#include <iostream>
#include <typeinfo>

using std::cout;
using std::endl;

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

//@include
class D : public B {
 public:
  void bar() override { cout << "D's bar" << endl; }
};

void bad(A* x) {
  if (typeid(*x) == typeid(A)) {
    x->foo();
  } else if (typeid(*x) == typeid(B) || typeid(*x) == typeid(D)) {
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
  int r = 3;
  if (r == 0) {
    return new A();
  } else if (r == 1) {
    cout << "returning a B" << endl;
    return new B();
  } else if (r == 2) {
    return new C();
  } else {
    return new D();
  }
}

int main(int argc, char** argv) {
  // Randomly returns a pointer to  an A, B, or C type object.
  A* x = randomBuilder();
  bad(x);
}
