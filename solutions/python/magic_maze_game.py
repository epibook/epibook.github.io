from maze_game import MazeGame
from maze_game import Room

# @include
class MagicMazeGame(MazeGame):

    def make_room(self):
        return MagicRoom()


class MagicRoom(Room):

    def connect(self, room2):
        print("Connecting magic room")
# @exclude

x = MagicMazeGame()
assert x is not None
assert x.make_room() is not None
