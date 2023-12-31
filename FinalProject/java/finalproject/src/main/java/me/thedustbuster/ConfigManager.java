package me.thedustbuster;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Manages configuration file
 */
public class ConfigManager {
  private ConfigManager() {
  }

  private static final String FILE_PATH = "config.properties";
  public static Properties config = ConfigManager.loadConfiguration();

  /** 
   * Saves the given configuration to disk
   * @param props The configuration
   */
  public static void saveConfiguration(Properties props) {
    try (OutputStream output = new FileOutputStream(FILE_PATH)) {
      props.store(output, "Configuration Properties");
      System.out.println("[Info] Configuration saved successfully");
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

  /** 
   * Loads the configuration from disk
   * @return Properties The configuration
   */
  public static Properties loadConfiguration() {
    Properties props = new Properties();
    try (InputStream input = new FileInputStream(FILE_PATH)) {
      props.load(input);
      System.out.println("[Info] Configuration loaded successfully");
    } catch (IOException io) {
      io.printStackTrace();
    }
    return props;
  }

  /**
   * Resets configuration file
   */
  public static void reset() {
    System.out.println("[WARN] Invalid address format; reverting to default: localhost:8080");

    Properties config = new Properties();

    config.setProperty("httpMethod", "http");
    config.setProperty("address", "localhost");
    config.setProperty("port", "8080");

    saveConfiguration(config);
  }
}
