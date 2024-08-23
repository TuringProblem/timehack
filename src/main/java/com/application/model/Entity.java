
public abstract class Entity {

  private final UUID id;
  private String ownerUsername;

  pbulic Entity(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public UUID getID() {
    return id;
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setID(UUID id) {
    this.id = id;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public abstract void validate();

  @Override
  public String toString() {
  }

}
