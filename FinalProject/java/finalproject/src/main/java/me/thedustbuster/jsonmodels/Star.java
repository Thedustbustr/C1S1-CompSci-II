package me.thedustbuster.jsonmodels;

import java.util.List;
import java.util.UUID;
import me.thedustbuster.util.Color;
import me.thedustbuster.util.math.Vector3;

/**
 * Stores properties of stars
 */
public class Star extends CelestialBody {
  private char starClass;
  private String starType;
  private int temperature;
  private double luminosity;
  private List<Planet> satellites;

  /**
   * Constructor
   * @param starClass The stellar class of the star
   * @param starType The true name of the star; ex: "Class O Black Hole"
   * @param temperature The temperature of the star
   * @param luminosity The luminosity of the star
   * @param networkID The network id of the star
   * @param color The color of the star
   * @param mass The mass of the star
   * @param position The position of the star
   * @param velocity The velocity of the star
   * @param radius The radius of the star
   * @param gravity The gravaity of the star
   * @param density The density of the star
   * @param maximumGravitationalReach Used for unvierse generation
   * @param minimumSafeDistanceToGenerate Used for unvierse generation
   * @param satellites The satellites of the star
   */
  public Star(UUID uuid, char starClass, String starType, int temperature, double luminosity, UUID networkID,
      Color color, double mass, Vector3 position, Vector3 velocity, double gravity, double density,
      double maximumGravitationalReach, double minimumSafeDistanceToGenerate, double radius, List<Planet> satellites) {

    super(uuid, velocity, position, mass, color, gravity, radius, density, minimumSafeDistanceToGenerate,
        maximumGravitationalReach);

    this.starClass = starClass;
    this.starType = starType;
    this.temperature = temperature;
    this.luminosity = luminosity;
    this.satellites = satellites;
  }

  /** 
   * @return char
   */
  public char getStarClass() {
    return starClass;
  }

  /** 
   * @return String
   */
  public String getStarType() {
    return starType;
  }

  /** 
   * @return int
   */
  public int getTemperature() {
    return temperature;
  }

  /** 
   * @return double
   */
  public double getLuminosity() {
    return luminosity;
  }

  /** 
   * @return List<Planet>
   */
  public List<Planet> getSatellites() {
    return this.satellites;
  }
}
