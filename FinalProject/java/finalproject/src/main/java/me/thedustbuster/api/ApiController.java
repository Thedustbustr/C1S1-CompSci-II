package me.thedustbuster.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.thedustbuster.jsonmodels.Star;

/**
 * Controls all things api
 */
public class ApiController {
  private ApiController() {
  }

  private static ApiController _instance = new ApiController();

  /** 
   * @return ApiController
   */
  public static ApiController instance() {
    return _instance;
  }

  private String url;

  /** 
   * @return String
   */
  public String getURL() {
    return this.url;
  }

  /** 
   * @param _url
   * @return Boolean
   */
  /* ---------------------------------------------------------------------------- */

  /**
   * Inital handshake
   * @param _url The url to connect too
   * @return If the url is valid
   */
  public Boolean connect(String _url) {
    try {
      URI uri = new URI(_url);
      URL u = uri.toURL();

      String response = apiCall((HttpURLConnection) u.openConnection());
      if (response != null && response.equals("hi")) {
        this.url = _url;
        return true;
      }

      return false;
    } catch (Exception e) {
      System.err.println("[ERR] " + e);
      return false;
    }
  }

  /** 
   * Makes a call to the api
   * @param connection The http connection to the api server
   * @return String The response
   */
  public String apiCall(HttpURLConnection connection) {
    try {
      connection.setRequestMethod("GET");

      // Get the response code
      int responseCode = connection.getResponseCode();
      System.out.println("[Info] Sucessful response from API server; Code: " + responseCode);

      // Read the response from the API
      String line;
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();

      while ((line = reader.readLine()) != null) {
        response.append(line);
      }

      reader.close();
      connection.disconnect();

      return response.toString();
    } catch (Exception e) {
      System.err.println("[ERR] " + e);
      disconnect(connection);
      return null;
    }
  }

  /** 
   * Requests all stars from the api server
   * @return ArrayList<Star> The list of stars returned
   */
  public ArrayList<Star> requestAllStars() {
    try {
      URI uri = new URI(this.url + "/stars");
      URL u = uri.toURL();

      String response = apiCall((HttpURLConnection) u.openConnection());

      //https://stackoverflow.com/questions/5554217/deserialize-a-listt-object-with-gson
      Type listType = new TypeToken<ArrayList<Star>>() {
      }.getType();
      return new Gson().fromJson(response, listType);
    } catch (Exception e) {
      System.err.println("[ERR] " + e);
      return new ArrayList<Star>();
    }
  }

  /** 
   * Does all required actions to safely disconnect from the api server
   * @param connection The connection to the api server
   */
  public void disconnect(HttpURLConnection connection) {
    try {
      connection.disconnect();
    } catch (Exception e) {
      System.err.println("[ERR] " + e);
    }

    this.url = null;
  }
}
