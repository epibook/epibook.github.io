#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class Base {
 public:
  virtual string msg() { return "I am base"; }
};

class Child : public Base {
 public:
  virtual string msg() { return "I am child"; }
};

//@include
void cast(Base& x) { cout << x.msg() << endl; }
//@exclude

int main(int argc, char** argv) {
  Base f;
  Child b;
  cast(f);
  cast(b);
}
