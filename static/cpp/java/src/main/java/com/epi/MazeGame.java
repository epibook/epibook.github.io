package com.epi;

// @include
public abstract class MazeGame {
  // Template method pattern - uses subclasses implemention of makeRoom()
  public MazeGame() {
    Room room1 = makeRoom();
    Room room2 = makeRoom();
    room1.connect(room2);
    this.addRoom(room1);
    this.addRoom(room2);
  }

  // Implementing classes provide this method.
  abstract protected Room makeRoom();
  // @exclude
  private void addRoom(Room that) {}
  // @include
}
// @exclude

interface Room {
  public void connect(Room that);
}
