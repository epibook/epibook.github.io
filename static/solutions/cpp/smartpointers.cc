#include <memory>

using std::make_shared;
using std::shared_ptr;

int main(int argc, char** argv) {
  struct Cycle2;

  // @include
  struct Cycle1 {
    shared_ptr<Cycle2> next;
  };

  struct Cycle2 {
    shared_ptr<Cycle1> next;
  };

  auto head = make_shared<Cycle1>();  // head's reference count is now 1.
  auto tail = make_shared<Cycle2>();  // tail's reference count is now 1.
  head->next = tail;  // tail's reference count is now 2.
  tail->next = head;  // head's reference count is now 2.
  // On destruction of head and tail, the reference counts go to 1 and stay
  // there.
  // @exclude
}
