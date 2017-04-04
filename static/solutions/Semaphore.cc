#include <chrono>
#include <condition_variable>
#include <functional>
#include <iostream>
#include <mutex>
#include <string>
#include <thread>

using std::ref;
using std::string;
using std::cout;
using std::endl;
using std::chrono::milliseconds;
using std::thread;
using std::this_thread::sleep_for;
using std::mutex;
using std::lock_guard;
using std::unique_lock;
using std::condition_variable;

// @include
class Semaphore {
 public:
  Semaphore(int max_available) : max_available_(max_available), taken_(0) {}

  void Acquire() {
    unique_lock<mutex> lock(mx_);
    while (taken_ == max_available_) {
      cond_.wait(lock);
    }
    ++taken_;
  }

  void Release() {
    lock_guard<mutex> lock(mx_);
    --taken_;
    cond_.notify_all();
  }

  // @exclude
  int NumTaken() { return taken_; }
  // @include
 private:
  int max_available_;
  int taken_;
  mutex mx_;
  condition_variable cond_;
};
// @exclude

static int shared_data = 0;

void IncrementThread(Semaphore& semaphore, string name) {
  while (true) {
    cout << name << " is waiting for permit" << endl;
    semaphore.Acquire();
    cout << name << " has got permit, new count is " << semaphore.NumTaken()
         << endl;
    for (int i = 0; i < 3; ++i) {
      sleep_for(milliseconds(200));
      cout << name + " increments shared data: " << shared_data++ << endl;
    }
    cout << name + " has released permit" << endl;
    semaphore.Release();
  }
}

void DecrementThread(Semaphore& semaphore, string name) {
  while (true) {
    cout << name << " is waiting for permit" << endl;
    semaphore.Acquire();
    cout << name << " has got permit, new count is " << semaphore.NumTaken()
         << endl;
    for (int i = 0; i < 4; ++i) {
      sleep_for(milliseconds(300));
      cout << name + " decrements shared data: " << shared_data-- << endl;
    }
    cout << name + " has released permit" << endl;
    semaphore.Release();
  }
}

int main(int argc, char* argv[]) {
  Semaphore semaphore(1);
  cout << "Semaphore with 1 permit has been created" << endl;
  thread increment_thread1(IncrementThread, ref(semaphore),
                           "increment_thread_1");
  thread decrement_thread1(DecrementThread, ref(semaphore),
                           "decrement_thread_1");
  thread increment_thread2(IncrementThread, ref(semaphore),
                           "increment_thread_2");
  thread decrement_thread2(DecrementThread, ref(semaphore),
                           "decrement_thread_2");
  increment_thread1.join();
}
