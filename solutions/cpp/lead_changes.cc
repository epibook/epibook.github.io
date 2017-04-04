#include <cstdlib>
#include <ctime>
#include <iostream>
#include <vector>

using namespace std;

// @include
int count_lead_changes(int s, int t, const vector<int> &score_ways) {
  vector<vector<int>> lead_changes(s + 1, vector<int>(t + 1, -1));
  lead_changes[0][0] = 0;
  for (int i = 0; i <= s; ++i) {
    for (int j = 0; j <= t; ++j) {
      if (lead_changes[i][j] >= 0) {  // legal scores
        for (const int &score : score_ways) {
          int next_i = i + score, next_j = j + score;
          if (next_j <= t && lead_changes[i][next_j] == -1) {
            lead_changes[i][next_j] = 0;
          }
          if (next_j <= t) {
            lead_changes[i][next_j] +=
                lead_changes[i][j] + (i > j && i < next_j);
          }
          if (next_i <= s && lead_changes[next_i][j] == -1) {
            lead_changes[next_i][j] = 0;
          }
          if (next_i <= s) {
            lead_changes[next_i][j] +=
                lead_changes[i][j] + (i < j && next_i > j);
          }
        }
      }
    }
  }
  return lead_changes[s][t];
}
// @exclude

int main(int argc, char *argv[]) {
  srand(time(NULL));
  int s, t;
  vector<int> score_ways;
  if (argc == 3) {
    s = atoi(argv[1]);
    t = atoi(argv[2]);
    score_ways.resize(1 + rand() % 10);
    for (int i = 0; i < score_ways.size(); ++i) {
      score_ways[i] = 1 + rand() % min(s, t);
    }
  } else {
    s = rand() % 100;
    t = rand() % 100;
    score_ways.resize(1 + rand() % 10);
    for (int i = 0; i < score_ways.size(); ++i) {
      score_ways[i] = 1 + rand() % min(s, t);
    }
  }
  cout << count_lead_changes(s, t, score_ways) << endl;
  return 0;
}
