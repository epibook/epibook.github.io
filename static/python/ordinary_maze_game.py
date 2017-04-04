from maze_game import MazeGame
from maze_game import Room

# @include


class OrdinaryMazeGame(MazeGame):

    def make_room(self):
        return OrdinaryRoom()


class OrdinaryRoom(Room):

    def connect(self, room2):
        print("Connecting ordinary room")
# @exclude

x = OrdinaryMazeGame()
assert x is not None
assert x.make_room() is not None
