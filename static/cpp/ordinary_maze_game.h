// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_ORDINARY_MAZE_GAME_H_
#define SOLUTIONS_ORDINARY_MAZE_GAME_H_

#include "./maze_game.h"

class OrdinaryRoom : public Room {
 public:
  virtual ~OrdinaryRoom() override = default;

  void Connect(const Room* that) override {}
};

// @include
class OrdinaryMazeGameCreator : public MazeGameCreator {
  // @exclude
 public:
  virtual ~OrdinaryMazeGameCreator() override = default;
  // @include
  Room* MakeRoom() override { return new OrdinaryRoom(); }
};
// @exclude

#endif  // SOLUTIONS_ORDINARY_MAZE_GAME_H_
