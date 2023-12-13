package me.thedustbuster.jsonmodels;

import me.thedustbuster.util.math.*;

public record OrbitalParameters(double µ, double semiMajorAxis, double semiMinorAxis, double apoapsis, double periapsis, double eccentricity, double inclination, double argumentOfPeriapsis, double longitudeOfTheAscendingNode, Vector3 specificAngularMomentum, double orbitalPeriod) {}
