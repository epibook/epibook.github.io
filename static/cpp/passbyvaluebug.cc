#include <array>
#include <cassert>
#include <iostream>

using std::array;
using std::cout;
using std::endl;

int main(int argc, char** argv) {
  //@include
  class Buffer {
   public:
    Buffer(int size, int* buffer) : size(size), buffer(buffer) {}

    int size;
    int* buffer;
  };

  const int kBufSize = 2;
  int* buffer = new int[kBufSize]{1, 2};

  Buffer b1 = Buffer(kBufSize, buffer);
  cout << b1.buffer[0] << endl;

  Buffer b2 = b1;
  b2.buffer[0] = -2;
  cout << b1.buffer[0] << endl;
  //@exclude
  assert(b1.buffer[0] == b2.buffer[0]);
}
