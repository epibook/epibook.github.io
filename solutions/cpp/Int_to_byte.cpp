#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

// @include
void print_int_to_bytes(int x) {
  int nbytes = sizeof(x);
  for (int i = 0; i < nbytes; ++i) {
    putchar(x & 0b11111111);
    x >>= 8;
  }
}
// @exclude

int main(int argc, char *argv[]) {
  srand(time(NULL));
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = rand();
  }
  cout << "n = " << n << endl;
  print_int_to_bytes(n);
  cout << endl;
  return 0;
}
