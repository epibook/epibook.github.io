// Copyright (c) 2016 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_MAZE_GAME_H_
#define SOLUTIONS_MAZE_GAME_H_

#include <iostream>
#include <vector>

using std::vector;
using std::cout;

class Room {
 public:
  virtual ~Room() = default;

  virtual void Connect(const Room* that) = 0;
};

class MazeGame {
 public:
  void AddRoom(const Room* that) { rooms.push_back(that); }

 private:
  vector<const Room*> rooms;
};

// @include
class MazeGameCreator {
 public:
  virtual Room* MakeRoom() = 0;
  // @exclude
  virtual ~MazeGameCreator() = default;
  // @include
  // This factory method is a template method for creating MazeGame objects.
  // MazeGameCreator's subclasses implement MakeRoom() as appropriate for
  // the type of room being created.
  MazeGame* FactoryMethod() {
    MazeGame* mazeGame = new MazeGame();
    Room* room1 = MakeRoom();
    Room* room2 = MakeRoom();
    room1->Connect(room2);
    mazeGame->AddRoom(room1);
    mazeGame->AddRoom(room2);
    return mazeGame;
  }
};
// @exclude

#endif  // SOLUTIONS_MAZE_GAME_H_
