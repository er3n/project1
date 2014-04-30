package org.abacus.common.shared.event;

public class ReadEvent implements Event{
  protected boolean entityFound = true;

  public boolean isEntityFound() {
    return entityFound;
  }
}
