// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <boost/chrono.hpp>
#include <boost/thread/scoped_thread.hpp>
#include <functional>
#include <iostream>
#include <string>

using std::string;
using std::bind;
using std::cout;
using std::endl;
using boost::chrono::milliseconds;
using boost::scoped_thread;
using boost::this_thread::sleep_for;
using boost::thread_interrupted;

const milliseconds TIMEOUT(500);

// @include
class Requestor {
  // @exclude
 public:
  Requestor(const string& request, int delay)
      : req_(request), delay_(delay) {}

  string Finished() { return "response:" + req_; }

  string Error() { return "response:" + req_ + ":TIMEDOUT"; }

  // @include
  string Execute() {
    try {
      // simulate the time taken to perform a computation
      sleep_for(delay_);
    } catch (const thread_interrupted&) {
      return Error();
    }
    return Finished();
  }
  // @exclude

  void ProcessResponse(const string& response) {
    cout << "ProcessResponse:" << response << endl;
  }

  // @include
  void ActualTask() {
    const string& response = Execute();
    ProcessResponse(response);
  }

  void Task() {
    scoped_thread<> inner_thread(&Requestor::ActualTask, this);
    if (!inner_thread.try_join_for(TIMEOUT)) {
      inner_thread.interrupt();
    }
  }

  void Dispatch() { thread_ = scoped_thread<>(&Requestor::Task, this); }

  // @exclude
 private:
  string req_;
  milliseconds delay_;
  scoped_thread<> thread_;
  // @include
};
// @exclude

int main(int argc, char* argv[]) {
  Requestor r1("t1", 1000);
  r1.Dispatch();
  Requestor r2("t2", 100);
  r2.Dispatch();
  Requestor r3("t3", 10);
  r3.Dispatch();
  Requestor r4("t4", 1);
  r4.Dispatch();
  Requestor r5("t5", 2);
  r5.Dispatch();
  return 0;
}
