#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

string rand_string(int len) {
  string ret;
  int x = rand() % 10;
  ret += '0' + x;
  if (x) {
    while (--len) {
      ret += '0' + rand() % 10;
    }
  }
  return ret;
}

// @include
bool comp(const string &a, const string &b) { return a + b > b + a; }

string max_concatenation(vector<string> &A) {
  sort(A.begin(), A.end(), comp);
  string ret;
  for (int i = 0; i < A.size(); ++i) {
    ret += A[i];
  }
  return ret;
}
// @exclude

int main(int argc, char *argv[]) {
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = 1 + rand() % 100000;
  }
  vector<string> A;
  for (int i = 0; i < n; ++i) {
    A.push_back(rand_string(1 + rand() % 4));
  }
  for (int i = 0; i < n; ++i) {
    cout << A[i] << ' ';
  }
  cout << endl;
  string x(max_concatenation(A));
  cout << x << endl;
  return 0;
}
