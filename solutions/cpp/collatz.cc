// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <boost/thread.hpp>
#include <boost/thread/sync_bounded_queue.hpp>
#include <cassert>
#include <chrono>
#include <functional>
#include <iostream>
#include <string>
#include <unordered_set>

using std::stoi;
using std::bind;
using std::ref;
using std::pair;
using std::make_pair;
using std::unordered_set;
using std::cout;
using std::endl;
using std::chrono::system_clock;
using std::chrono::milliseconds;
using std::chrono::duration_cast;
using boost::thread_group;
using boost::sync_bounded_queue;
using boost::queue_op_status;

typedef unsigned long long CollatzInt;
typedef sync_bounded_queue<pair<CollatzInt, CollatzInt>> QueueType;
void Worker(CollatzInt lower, CollatzInt upper);
bool CollatzCheck(CollatzInt x, unordered_set<CollatzInt>& visited);

// @include
void ThreadFunc(QueueType& q) {
  pair<CollatzInt, CollatzInt> args;
  while (q.wait_pull_front(args) == queue_op_status::success) {
    Worker(args.first, args.second);
  }
}

// Performs basic unit of work
void Worker(CollatzInt lower, CollatzInt upper) {
  for (CollatzInt i = lower; i <= upper; ++i) {
    assert(CollatzCheck(i, unordered_set<CollatzInt>()));
  }
  cout << '(' << lower << ',' << upper << ')' << endl;
}

// Checks an individual number
bool CollatzCheck(CollatzInt x, unordered_set<CollatzInt>& visited) {
  if (x == 1) {
    return true;
  }
  if (!visited.emplace(x).second) {
    return false;
  }
  if (x & 1) {  // odd number
    return CollatzCheck(3 * x + 1, visited);
  } else {  // even number
    return CollatzCheck(x >> 1, visited);  // divide by 2
  }
}
// @exclude

// @include

int main(int argc, char* argv[]) {
  CollatzInt N = 10000000;
  CollatzInt RANGESIZE = 1000000;
  int NTHREADS = 4;
  // @exclude
  if (argc > 1) {
    N = stoi(argv[1]);
  }
  if (argc > 2) {
    RANGESIZE = stoi(argv[2]);
  }
  if (argc > 3) {
    NTHREADS = stoi(argv[3]);
  }

  assert(CollatzCheck(1, unordered_set<CollatzInt>()));
  assert(CollatzCheck(3, unordered_set<CollatzInt>()));
  assert(CollatzCheck(8, unordered_set<CollatzInt>()));
  auto start_time = system_clock::now();

  // @include
  // Uses synchronized bounded queue for task assignment and load balancing
  QueueType q(NTHREADS);
  thread_group threads;
  for (int i = 0; i < NTHREADS; ++i) {
    threads.create_thread(bind(ThreadFunc, ref(q)));
  }
  for (CollatzInt i = 0; i < N / RANGESIZE; ++i) {
    q << make_pair(i * RANGESIZE + 1, (i + 1) * RANGESIZE);
  }
  q.close();
  threads.join_all();

  // @exclude
  cout << "Finished all threads" << endl;
  auto running_time =
      duration_cast<milliseconds>(system_clock::now() - start_time).count();
  cout << "time in milliseconds for checking to " << N << " is "
       << running_time << '(' << N / running_time << " per ms)";

  // @include
  return 0;
}
// @exclude
