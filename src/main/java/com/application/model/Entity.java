package com.application.model;

public abstract class Entity {

  private String ownerUsername;
  //somethin

  public Entity(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }



  public String getOwnerUsername() {
    return ownerUsername;
  }


  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public abstract void validate();

  @Override
  public String toString() {
    return "";
  }

}
