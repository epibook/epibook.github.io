// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <vector>

using std::vector;

// @include
struct Player {
  bool operator<(const Player& that) const { return height < that.height; }

  int height;
};

class Team {
 public:
  explicit Team(const vector<int>& height) {
    for (int h : height) {
      players_.emplace_back(Player{h});
    }
  }

  // Checks if A can be placed in front of B.
  static bool valid_placement_exists(const Team& A, const Team& B) {
    vector<Player> A_sorted(A.SortPlayersByHeight());
    vector<Player> B_sorted(B.SortPlayersByHeight());
    for (int i = 0; i < A_sorted.size() && i < B_sorted.size(); ++i) {
      if (!(A_sorted[i] < B_sorted[i])) {
        // A_sorted[i] cannot be placed behind B_sorted[i].
        return false;
      }
    }
    return true;
  }

 private:
  vector<Player> SortPlayersByHeight() const {
    vector<Player> sorted_players(players_);
    sort(sorted_players.begin(), sorted_players.end());
    return sorted_players;
  }

  vector<Player> players_;
};
// @exclude

int main(int argc, char* argv[]) {
  vector<int> height = {1, 5, 4};
  Team t1(height);
  height = {2, 3, 4};
  Team t2(height);
  assert(!Team::valid_placement_exists(t1, t2) &&
         !Team::valid_placement_exists(t2, t1));
  height = {0, 3, 2};
  Team t3(height);
  assert(Team::valid_placement_exists(t3, t1) &&
         !Team::valid_placement_exists(t1, t3) &&
         Team::valid_placement_exists(t3, t2) &&
         !Team::valid_placement_exists(t1, t2));
  height = {1, 4, 2};
  Team t4(height);
  assert(!Team::valid_placement_exists(t4, t3));
  assert(!Team::valid_placement_exists(t3, t4));
  return 0;
}
