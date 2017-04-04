#include <assert.h>
#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>
#include <unistd.h>

using std::mutex;
using std::lock_guard;
using std::unique_lock;
using std::condition_variable;
using std::thread;

//@include
class Semaphore {
 public:
  Semaphore(int max_available) : value_(max_available), cond_(), m_() {}
  ~Semaphore() {}
  void acquire();
  void release();
  int value() {
    lock_guard<mutex> lk(m_);
    return value_;
  }

 private:
  int value_;
  condition_variable cond_;
  mutex m_;
};

/**
 * called by thread that is acquiring semaphore. If the value is less than 0
 * then it would wait.
 */
void Semaphore::acquire() {
  unique_lock<mutex> lk(m_);
  while (value_ == 0) {
    cond_.wait(lk);
  }
  assert(value_ > 0);
  /** should only decrement while value is > 0 as otherwise
   * all threads can go to sleep **/
  value_--;
  lk.unlock();
}

/** It is called by thread that is releasing semaphore
 */
void Semaphore::release() {
  lock_guard<mutex> lk(m_);
  value_++;
  assert(value_ > 0);
  if (value_ == 1) {
    /** for all values > 1 there would not be other threads waiting **/
    cond_.notify_one();
  }
}
// @exclude

void worker(int index, Semaphore* sem) {
  while (true) {
    sem->acquire();
    std::cout << " thread " << index << " sem: " << sem->value() << std::endl;
    sem->release();
  }
}
int main(int argc, char* argv[]) {
  Semaphore s1(3);
  thread r0(worker, 0, &s1);
  thread r1(worker, 1, &s1);
  thread r2(worker, 2, &s1);
  thread r3(worker, 3, &s1);
  thread r4(worker, 4, &s1);
  thread r5(worker, 5, &s1);
  usleep(100000000);
  return 0;
}
