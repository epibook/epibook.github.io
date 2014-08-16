// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>

using std::cout;
using std::endl;

// @include
template <typename ValueType, size_t N>
class Array {
 public:
  bool read(size_t i, ValueType* v) const {
    if (isValid(i)) {
      *v = A_[i];
      return true;
    }
    return false;
  }

  void write(size_t i, const ValueType& v) {
    if (!isValid(i)) {
      S_[t_] = i;
      P_[i] = t_++;
    }
    A_[i] = v;
  }

 private:
  bool isValid(size_t i) const {
    return (0 <= P_[i] && P_[i] < t_ && S_[P_[i]] == i);
  }

  ValueType A_[N];
  int P_[N], S_[N], t_ = 0;
};
// @exclude

int main(int argc, char* argv[]) {
  Array<int, 11> A;
  int x;
  assert(A.read(0, &x) == false);
  assert(A.read(1, &x) == false);
  A.write(1, 5);
  assert(A.read(1, &x) == true && x == 5);
  assert(A.read(2, &x) == false);
  A.write(2, 27);
  assert(A.read(2, &x) == true && x == 27);
  assert(A.read(7, &x) == false);
  A.write(7, -19);
  assert(A.read(7, &x) == true && x == -19);
  return 0;
}
