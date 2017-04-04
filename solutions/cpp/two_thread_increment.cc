// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <string>
#include <thread>

using std::cout;
using std::endl;
using std::stoi;
using std::thread;

// @include
static int counter = 0;

void IncrementThread(int N) {
  for (int i = 0; i < N; ++i) {
    ++counter;
  }
}

void TwoThreadIncrementDriver(int N) {
  thread T1(IncrementThread, N);
  thread T2(IncrementThread, N);
  T1.join();
  T2.join();

  cout << counter << endl;
}
// @exclude

int main(int argc, char* argv[]) {
  int N = argc == 2 ? stoi(argv[1]) : 1000000000;
  TwoThreadIncrementDriver(N);
  return 0;
}
