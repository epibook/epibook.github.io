#include <cassert>

//@include
// Avoid overhead of a function call.
#define isvowel(c) (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
//@exclude

int main(int argc, char** argv) {
  char c = 'a';
  assert(isvowel(c));
}
