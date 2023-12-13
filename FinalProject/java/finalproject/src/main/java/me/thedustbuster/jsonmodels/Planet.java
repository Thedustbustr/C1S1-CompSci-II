package me.thedustbuster.jsonmodels;

import java.util.UUID;

import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

enum PlanetType {
  Gas, Rocky
}

public class Planet extends CelestialBody {
  private final PlanetType typ;
  private final double orbitalPeriod;
  private final OrbitalParameters orbitalParameters;

  public Planet(UUID uuid, PlanetType typ, double orbitalPeriod, CelestialBody centralOrbitalBody, OrbitalParameters orbitalParameters, UUID networkID, Color color, double mass, Vector3 position, Vector3 velocity, double radius, double gravity, double density, double maximumGravitationalReach, double minimumSafeDistanceToGenerate) {

    super(uuid, velocity, position, mass, color, gravity, radius, density, minimumSafeDistanceToGenerate, maximumGravitationalReach);
    this.typ = typ;
    this.orbitalPeriod = orbitalPeriod;
    this.orbitalParameters = orbitalParameters;
  }

  public PlanetType getTyp() {
    return typ;
  }

  public double getOrbitalPeriod() {
    return orbitalPeriod;
  }

  public OrbitalParameters getOrbitalParameters() {
    return orbitalParameters;
  }
}
