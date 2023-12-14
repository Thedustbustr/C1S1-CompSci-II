package me.thedustbuster.jsonmodels;

import java.util.UUID;

/**
 * All network objects will have the following properties
 */
public abstract class NetworkObject {
  private final UUID networkID;

  /**
   * Constructor
   * @param networkID The unique network id
   */
  public NetworkObject(UUID networkID) {
    this.networkID = networkID;
  }

  
  /** 
   * @return UUID
   */
  public UUID getNetworkID() {
    return networkID;
  }
}
