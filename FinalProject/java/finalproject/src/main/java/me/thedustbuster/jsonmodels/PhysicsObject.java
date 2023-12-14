package me.thedustbuster.jsonmodels;

import java.util.UUID;
import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

/**
 * All physics objects will have the following properties
 */
public abstract class PhysicsObject extends NetworkObject {
  protected Vector3 velocity;
  protected Vector3 position;
  protected double mass;
  protected final Color color;

  /**
   * Constructor
   * @param uuid The network id of the object
   * @param velocity The velocity of the object
   * @param position The postion of the object
   * @param mass The mass of the object
   * @param color The color of the Object
   */
  public PhysicsObject(UUID uuid, Vector3 velocity, Vector3 position, double mass, Color color) {
    super(uuid);
    this.velocity = velocity;
    this.position = position;
    this.mass = mass;
    this.color = color;
  }

  /** 
   * @return Vector3
   */
  public Vector3 getVelocity() {
    return velocity;
  }

  /** 
   * @param velocity
   */
  public void setVelocity(Vector3 velocity) {
    this.velocity = velocity;
  }

  /** 
   * @return Vector3
   */
  public Vector3 getPosition() {
    return position;
  }

  /** 
   * @param position
   */
  public void setPosition(Vector3 position) {
    this.position = position;
  }

  /** 
   * @return double
   */
  public double getMass() {
    return mass;
  }

  /** 
   * @param mass
   */
  public void setMass(double mass) {
    this.mass = mass;
  }

  /** 
   * @return Color
   */
  public Color getColor() {
    return color;
  }
}
