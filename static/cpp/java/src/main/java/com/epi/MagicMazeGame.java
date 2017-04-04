package com.epi;

// @include
public class MagicMazeGame extends MazeGame {
  // The default constructor will call superclass constructor.
  @Override
  protected Room makeRoom() {
    return new MagicRoom();
  }
}
//@exclude

class MagicRoom implements Room {
  public void connect(Room that) {}
}
