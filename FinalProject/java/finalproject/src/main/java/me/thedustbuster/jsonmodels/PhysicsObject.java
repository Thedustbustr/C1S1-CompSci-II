package me.thedustbuster.jsonmodels;

import java.util.UUID;
import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

public class PhysicsObject extends NetworkObject {
  protected Vector3 velocity;
  protected Vector3 position;
  protected double mass;
  protected final Color color;

  public PhysicsObject(UUID uuid, Vector3 velocity, Vector3 position, double mass, Color color) {
    super(uuid);
    this.velocity = velocity;
    this.position = position;
    this.mass = mass;
    this.color = color;
  }

  public Vector3 getVelocity() {
    return velocity;
  }

  public void setVelocity(Vector3 velocity) {
    this.velocity = velocity;
  }

  public Vector3 getPosition() {
    return position;
  }

  public void setPosition(Vector3 position) {
    this.position = position;
  }

  public double getMass() {
    return mass;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public Color getColor() {
    return color;
  }
}
