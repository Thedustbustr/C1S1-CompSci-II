package me.thedustbuster.jsonmodels;

import java.util.UUID;
import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

public abstract class CelestialBody extends PhysicsObject {

  protected final double gravity;
  protected final double radius;
  protected final double density;
  protected final double minimumSafeDistanceToGenerate;
  protected final double maximumGravitationalReach;

  public CelestialBody(UUID uuid, Vector3 velocity, Vector3 position, double mass, Color color, double gravity, double radius, double density, double minimumSafeDistanceToGenerate, double maximumGravitationalReach) {
    super(uuid, velocity, position, mass, color);
    this.gravity = gravity;
    this.radius = radius;
    this.density = density;
    this.minimumSafeDistanceToGenerate = minimumSafeDistanceToGenerate;
    this.maximumGravitationalReach = maximumGravitationalReach;
  }

  public double getGravity() {
    return gravity;
  }

  public double getRadius() {
    return radius;
  }

  public double getDensity() {
    return density;
  }

  public double getMinimumSafeDistanceToGenerate() {
    return minimumSafeDistanceToGenerate;
  }

  public double getMaximumGravitationalReach() {
    return maximumGravitationalReach;
  }
}
