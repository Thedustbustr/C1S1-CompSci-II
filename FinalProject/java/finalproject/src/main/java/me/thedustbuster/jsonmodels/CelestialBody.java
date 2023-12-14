package me.thedustbuster.jsonmodels;

import java.util.UUID;
import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

/**
 * All celestial bodies will have the following properties
 */
public abstract class CelestialBody extends PhysicsObject {
  protected final double gravity;
  protected final double radius;
  protected final double density;
  protected final double minimumSafeDistanceToGenerate;
  protected final double maximumGravitationalReach;

  /**
   * Constructor
   * @param uuid The network id of the body
   * @param velocity The velocity of the body
   * @param position The position of the body
   * @param mass The mass of the body
   * @param color The color of the body
   * @param gravity The gravity of the body
   * @param radius The radius of the body
   * @param density The density of the body
   * @param minimumSafeDistanceToGenerate Used for universe generation
   * @param maximumGravitationalReach Used for universe generation
   */
  public CelestialBody(UUID uuid, Vector3 velocity, Vector3 position, double mass, Color color, double gravity,
      double radius, double density, double minimumSafeDistanceToGenerate, double maximumGravitationalReach) {
    super(uuid, velocity, position, mass, color);
    this.gravity = gravity;
    this.radius = radius;
    this.density = density;
    this.minimumSafeDistanceToGenerate = minimumSafeDistanceToGenerate;
    this.maximumGravitationalReach = maximumGravitationalReach;
  }

  /** 
   * @return double
   */
  public double getGravity() {
    return gravity;
  }

  /** 
   * @return double
   */
  public double getRadius() {
    return radius;
  }

  /** 
   * @return double
   */
  public double getDensity() {
    return density;
  }

  /** 
   * @return double
   */
  public double getMinimumSafeDistanceToGenerate() {
    return minimumSafeDistanceToGenerate;
  }

  /** 
   * @return double
   */
  public double getMaximumGravitationalReach() {
    return maximumGravitationalReach;
  }
}
