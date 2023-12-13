package me.thedustbuster.jsonmodels;

import java.util.UUID;

public class NetworkObject {
  private final UUID networkID;

  public NetworkObject(UUID networkID) {
    this.networkID = networkID;
  }

  public UUID getNetworkID() {
    return networkID;
  }
}
