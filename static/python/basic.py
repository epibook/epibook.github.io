# Python 3.4+
from abc import ABC, abstractmethod

class Room(ABC):

  @abstractmethod
  def connect(self, room2):
    pass

class MazeGame(ABC):

  @abstractmethod
  def makeRoom(self):
    print("abstract makeRoom")
    pass

  def addRoom(self, room):
    print("adding room")

  def __init__(self):
    room1 = makeRoom()
    room2 = makeRoom()
    room1.connect(room2)
    self.addRoom(room1)
    self.addRoom(room2)

class MagicMazeGame(MazeGame):
  def makeRoom(room):
    print("MagicMazeGame")


