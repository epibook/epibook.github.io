#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <vector>

using namespace std;

// @include
class Employee {
 public:
  int start, end;

  Employee(int s = -1, int e = -1) : start(s), end(e) {}

  bool operator<(const Employee &e) const { return end < e.end; }
};

vector<int> arrange_events(vector<Employee> &A) {
  sort(A.begin(), A.end());
  vector<int> events;
  for (int i = 0; i < A.size(); ++i) {
    if (events.size() < 2 || events.back() < A[i].start) {
      events.emplace_back(A[i].end - 1);
      events.emplace_back(A[i].end);
    } else {
      if (events[events.size() - 2] < A[i].start) {
        if (events.back() == A[i].end) {
          events.pop_back();
          events.emplace_back(A[i].end - 1);
        }
        events.emplace_back(A[i].end);
      }
    }
  }
  return events;
}
// @exclude

int main(int argc, char *argv[]) {
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = 1 + rand() % 10000;
  }
  vector<Employee> A;
  for (int i = 0; i < n; ++i) {
    int s = rand() % 10000;
    int e = s + 1 + rand() % 1000;
    A.push_back(Employee(s, e));
  }
  vector<int> events(arrange_events(A));
  for (int i = 0; i < A.size(); ++i) {
    cout << A[i].start << ' ' << A[i].end << endl;
  }
  cout << events.size() << endl;
  for (int i = 0; i < events.size(); ++i) {
    cout << events[i] << ' ';
  }
  cout << endl;
  return 0;
}
