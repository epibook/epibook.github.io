#include "./magic_maze_game.h"
#include "./maze_game.h"
#include "./ordinary_maze_game.h"

int main(int argc, char* argv[]) {
  // @include
  MazeGame* ordinaryMazeGame =
      (new OrdinaryMazeGameCreator())->FactoryMethod();
  MazeGame* magicMazeGame = (new MagicMazeGameCreator())->FactoryMethod();
  // @exclude
  return 0;
}
