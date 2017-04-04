#include "./magic-maze-game.h"
#include "./maze-game.h"
#include "./ordinary-maze-game.h"

int main(int argc, char* argv[]) {
  // @include
  MazeGame* ordinaryMazeGame =
      (new OrdinaryMazeGameCreator())->FactoryMethod();
  MazeGame* magicMazeGame = (new MagicMazeGameCreator())->FactoryMethod();
  // @exclude
  return 0;
}
