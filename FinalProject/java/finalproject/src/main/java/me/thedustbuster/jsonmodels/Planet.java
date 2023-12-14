package me.thedustbuster.jsonmodels;

import java.util.UUID;

import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

/**
 * The differnt planet types
 */
enum PlanetType {
  /**
   * The gas giant type
   */
  Gas,
  /**
   * The rocky type
   */
  Rocky
}

/**
 * Stores properties of planets
 */
public class Planet extends CelestialBody {
  private final PlanetType typ;
  private final double orbitalPeriod;
  private final OrbitalParameters orbitalParameters;

  /**
   * Constructor
   * @param uuid The network object of the planet
   * @param typ The type of planet
   * @param orbitalPeriod The orbital period of the planet
   * @param centralOrbitalBody The body the planet orbits
   * @param orbitalParameters The orbital parameters of the plant
   * @param networkID The network id of the planet
   * @param color The color of the planet
   * @param mass The mass of the planet
   * @param position The position of the planet
   * @param velocity The velocity of the planet
   * @param radius The radius of the planet
   * @param gravity The gravaity of the planet
   * @param density The density of the planet
   * @param maximumGravitationalReach Used for unvierse generation
   * @param minimumSafeDistanceToGenerate Used for unvierse generation
   */
  public Planet(UUID uuid, PlanetType typ, double orbitalPeriod, CelestialBody centralOrbitalBody,
      OrbitalParameters orbitalParameters, UUID networkID, Color color, double mass, Vector3 position, Vector3 velocity,
      double radius, double gravity, double density, double maximumGravitationalReach,
      double minimumSafeDistanceToGenerate) {

    super(uuid, velocity, position, mass, color, gravity, radius, density, minimumSafeDistanceToGenerate,
        maximumGravitationalReach);
    this.typ = typ;
    this.orbitalPeriod = orbitalPeriod;
    this.orbitalParameters = orbitalParameters;
  }

  /** 
   * @return PlanetType
   */
  public PlanetType getTyp() {
    return typ;
  }

  /** 
   * @return double
   */
  public double getOrbitalPeriod() {
    return orbitalPeriod;
  }

  /** 
   * @return OrbitalParameters
   */
  public OrbitalParameters getOrbitalParameters() {
    return orbitalParameters;
  }
}
