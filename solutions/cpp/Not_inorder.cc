#include <iostream>
#include <string>

using namespace std;

// @include
int not_in_order(string s) {
  for (int i = 1; i < s.size(); ++i) {
    if (s[i] < s[i - 1]) {
      return i;
    }
  }
  return -1;
}
// @exclude

int main(int argc, char *argv[]) {
  cout << not_in_order(argv[1]) << endl;
  return 0;
}
