package me.thedustbuster.jsonmodels;

import java.util.List;
import java.util.UUID;
import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

public class Star extends CelestialBody {
  private char starClass;
  private String starType;
  private int temperature;
  private double luminosity;
  private List<Planet> satellites;

  public Star(UUID uuid, char starClass, String starType, int temperature, double luminosity, UUID networkID, Color color, double mass, Vector3 position, Vector3 velocity, double gravity, double density, double maximumGravitationalReach, double minimumSafeDistanceToGenerate, double radius, List<Planet> satellites) {

    super(uuid, velocity, position, mass, color, gravity, radius, density, minimumSafeDistanceToGenerate, maximumGravitationalReach);

    this.starClass = starClass;
    this.starType = starType;
    this.temperature = temperature;
    this.luminosity = luminosity;
    this.satellites = satellites;
  }

  public char getStarClass() {
    return starClass;
  }

  public String getStarType() {
    return starType;
  }

  public int getTemperature() {
    return temperature;
  }

  public double getLuminosity() {
    return luminosity;
  }

  public List<Planet> getSatellites() {
    return this.satellites;
  }
}
