// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <chrono>
#include <condition_variable>
#include <iostream>
#include <mutex>
#include <random>
#include <string>
#include <thread>

using std::string;
using std::cout;
using std::endl;
using std::random_device;
using std::default_random_engine;
using std::uniform_int_distribution;
using std::chrono::milliseconds;
using std::thread;
using std::this_thread::sleep_for;
using std::mutex;
using std::lock_guard;
using std::unique_lock;
using std::condition_variable;

namespace RW {
int data = 0;
mutex LRm;
condition_variable LR;
int read_count = 0;
mutex LW;
}

void DoSomethingElse() {
  static default_random_engine rnd((random_device())());
  uniform_int_distribution<> wait_time(0, 1000);
  sleep_for(milliseconds(wait_time(rnd)));
}

// @include
// LR and LW are variables in the RW namespace.
// They serve as read and write locks. The integer
// variable read_count in RW tracks the number of readers.
void Reader(string name) {
  while (true) {
    {
      lock_guard<mutex> lock(RW::LRm);
      ++RW::read_count;
    }
    // @exclude
    cout << "Reader " << name << " is about to read" << endl;
    // @include
    cout << RW::data << endl;
    {
      lock_guard<mutex> lock(RW::LRm);
      --RW::read_count;
      RW::LR.notify_one();
    }
    DoSomethingElse();
  }
}

void Writer(string name) {
  while (true) {
    {
      lock_guard<mutex> lock_w(RW::LW);
      bool done = false;
      while (!done) {
        unique_lock<mutex> lock(RW::LRm);
        if (RW::read_count == 0) {
          // @exclude
          cout << "Writer " << name << " is about to write" << endl;
          // @include
          ++RW::data;
          done = true;
        } else {
          // use wait/notify to avoid busy waiting
          while (RW::read_count != 0) {
            RW::LR.wait(lock);
          }
        }
      }
    }
    DoSomethingElse();
  }
}
// @exclude

int main(int argc, char* argv[]) {
  thread r0(Reader, "r0");
  thread r1(Reader, "r1");
  thread w0(Writer, "w0");
  thread w1(Writer, "w1");
  sleep_for(milliseconds(10000));
  return 0;
}
