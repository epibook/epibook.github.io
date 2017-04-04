#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

//@include
class Base {
 public:
  virtual string msg() { return "I am base"; }
};

class Child : public Base {
 public:
  virtual string msg() { return "I am child"; }
};

void cast(Base x) { cout << x.msg() << endl; }

int main() {
  Base f;
  Child b;
  cast(f);
  cast(b);
}
//@exclude
