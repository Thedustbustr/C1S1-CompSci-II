package me.thedustbuster;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import me.thedustbuster.api.ApiController;
import me.thedustbuster.jsonmodels.Planet;
import me.thedustbuster.jsonmodels.Star;

public class PrimaryController implements Initializable {
  private ArrayList<Star> stars;

  private int counter = 0;
  private int max;

  @FXML
  private Label densityLabel;

  @FXML
  private Label orbitalPeriodLabel;

  @FXML
  private Label velocityLabel;

  @FXML
  private Label positonLabel;

  @FXML
  private Label massLabel;

  @FXML
  private Label radiusLabel;

  @FXML
  private Label gravityLabel;

  @FXML
  private Label muLabel;

  @FXML
  private Label semiMajorLabel;

  @FXML
  private Label semiMinorLabel;

  @FXML
  private Label apoapsisLabel;

  @FXML
  private Label periapsisLabel;

  @FXML
  private Label eccentrictyLabel;

  @FXML
  private Label inclinationLabel;

  @FXML
  private Label argumentOfPeriapsisLabel;

  @FXML
  private Label longitudeOfTheAcendingNodeLabel;

  @FXML
  private Label specificAngularMomentumLabel;

  @FXML
  private Circle colorCircle;

  @FXML
  private Label connectionLabel;

  @FXML
  private Label nameLabel;

  @FXML
  private Button dataButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.connectionLabel.setText("API Server Address: " + ApiController.instance().getURL());
  }

  @FXML
  private void nextData() throws Exception {
    if (this.stars == null) {
      this.stars = ApiController.instance().requestAllStars();
      this.max = stars.get(0).getSatellites().size();
      System.out.println("[Warn] No data; pulling from API server");
    }

    dataButton.setDisable(true);

    if (this.counter == 0) {
      setStarData(this.stars.get(0));
    } else {
      setPlanetData((Planet) this.stars.get(0).getSatellites().get(this.counter - 1));
    }

    this.counter++;

    if (this.counter > max) {
      this.counter = 0;
    }

    dataButton.setText("Display " + (counter + 1) + getTense(counter + 1) + " Body Data");
    dataButton.setDisable(false);
  }

  private String getTense(int index) {
    switch (index) {
      case 1:
        return "st";
      case 2:
        return "nd";
      case 3:
        return "rd";
      default:
        return "th";
    }
  }

  private void setStarData(Star s) {
    this.nameLabel.setText("" + s.getStarType());

    this.densityLabel.setText("Density: " + s.getDensity());

    this.orbitalPeriodLabel.setText("Orbital Period: N/A");

    this.velocityLabel.setText("Velocity: " + s.getVelocity());

    this.positonLabel.setText("Position: " + s.getPosition());

    this.massLabel.setText("Mass: " + s.getMass());

    this.radiusLabel.setText("Radius: " + s.getRadius());

    this.gravityLabel.setText("Gravity: " + s.getGravity());

    this.muLabel.setText("µ: N/A");

    this.semiMajorLabel.setText("Semi-Major Axis: N/A");

    this.semiMinorLabel.setText("Semi-Minor Axis: N/A");

    this.apoapsisLabel.setText("Apoapsis: N/A");

    this.periapsisLabel.setText("Periapsis: N/A");

    this.eccentrictyLabel.setText("Eccentricty: N/A");

    this.inclinationLabel.setText("Inclination: N/A");

    this.argumentOfPeriapsisLabel.setText("Argument Of Periapsis Label: N/A");

    this.longitudeOfTheAcendingNodeLabel.setText("Longitude Of The Acending Node: N/A");

    this.specificAngularMomentumLabel.setText("Specific Angular Momentum: N/A");

    this.colorCircle.setFill(new Color(s.getColor().r() / 255f, s.getColor().g() / 255f, s.getColor().b() / 255f, s.getColor().a()));
  }

  private void setPlanetData(Planet p) {
    this.nameLabel.setText(p.getTyp() + " Planet");

    this.densityLabel.setText("Density: " + p.getDensity());

    this.orbitalPeriodLabel.setText("Orbital Period: " + p.getOrbitalPeriod());

    this.velocityLabel.setText("Velocity: " + p.getVelocity());

    this.positonLabel.setText("Postion: " + p.getPosition());

    this.massLabel.setText("Mass: " + p.getMass());

    this.radiusLabel.setText("Radius: " + p.getRadius());

    this.gravityLabel.setText("Gravity: " + p.getGravity());

    this.muLabel.setText("µ: " + p.getOrbitalParameters().µ());

    this.semiMajorLabel.setText("Semi-Major Axis: " + p.getOrbitalParameters().semiMajorAxis());

    this.semiMinorLabel.setText("Semi-Minor Axis: " + p.getOrbitalParameters().semiMinorAxis());

    this.apoapsisLabel.setText("Apoapsis: " + p.getOrbitalParameters().apoapsis());

    this.periapsisLabel.setText("Periapsis: " + p.getOrbitalParameters().periapsis());

    this.eccentrictyLabel.setText("Eccentricty: " + p.getOrbitalParameters().eccentricity());

    this.inclinationLabel.setText("Inclination: " + p.getOrbitalParameters().inclination());

    this.argumentOfPeriapsisLabel.setText("Argument Of Periapsis: " + p.getOrbitalParameters().argumentOfPeriapsis());

    this.longitudeOfTheAcendingNodeLabel.setText("Longitude Of The Acending Node: " + p.getOrbitalParameters().longitudeOfTheAscendingNode());

    this.specificAngularMomentumLabel.setText("Specific Angular Momentum: " + p.getOrbitalParameters().specificAngularMomentum());

    this.colorCircle.setFill(new Color(p.getColor().r() / 255f, p.getColor().g() / 255f, p.getColor().b() / 255f, p.getColor().a()));
  }
}
