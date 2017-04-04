# @include
from abc import ABC, abstractmethod

class Room(ABC):

  @abstractmethod
  def connect(self, room2):
    pass

class MazeGame(ABC):

  @abstractmethod
  def make_room(self):
    print("abstract make_room")
    pass

  def addRoom(self, room):
    print("adding room")

  def __init__(self):
    room1 = self.make_room()
    room2 = self.make_room()
    room1.connect(room2)
    self.addRoom(room1)
    self.addRoom(room2)
# @exclude
