public class Temperature {
  private double fahrenheit;

  public void setFahrenheit(double ftemp) {
    this.fahrenheit = ftemp;
  }

  public double getFahrenheit() {
    return this.fahrenheit;
  }

  public Temperature(double ftemp) {
    this.fahrenheit = ftemp;
  }

  public double celsius() { // <---- Just a better temperature unit btw
    return (5 / 9d) * (this.fahrenheit - 32);
  }

  public double kelvin() {
    return this.celsius() + 237;
  }
}
