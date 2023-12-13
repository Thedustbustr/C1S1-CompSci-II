package me.thedustbuster;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import me.thedustbuster.api.ApiController;

public class ConnectController implements Initializable {
  @FXML
  private Label statusLabel;

  @FXML
  private ChoiceBox<String> httpMethod;

  @FXML
  private TextField serverAddressField;

  @FXML
  private Button connectButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    httpMethod.getItems().removeAll(httpMethod.getItems());
    httpMethod.setItems(FXCollections.observableArrayList("http://"));
    httpMethod.setValue(ConfigManager.config.getProperty("httpMethod") + "://");

    serverAddressField.setText(ConfigManager.config.getProperty("address") + ":" + ConfigManager.config.getProperty("port"));
  }

  @FXML
  public void updateStatusText(String text) {
    statusLabel.setText(text);
  }

  @FXML
  private void connect() throws Exception {
    updateConfig();

    String address = httpMethod.getValue() + serverAddressField.getText();

    httpMethod.setDisable(true);
    serverAddressField.setDisable(true);
    connectButton.setDisable(true);

    statusLabel.setText("Attempting connection to " + address + "...");

    if (ApiController.instance().connect(address)) {
      App.setRoot("primary");
    } else {
      httpMethod.setDisable(false);
      serverAddressField.setDisable(false);
      connectButton.setDisable(false);

      statusLabel.setText("Failed to connect to " + address);
    }
  }

  private void updateConfig() {
    if (ConfigManager.config.getProperty("httpMethod") == null || ConfigManager.config.getProperty("address") == null || ConfigManager.config.getProperty("port") == null) {
      ConfigManager.reset();
      return;
    }

    if ((ConfigManager.config.getProperty("httpMethod").equals(httpMethod.getValue().substring(0, httpMethod.getValue().length() - 3))) || ConfigManager.config.getProperty("apiEndpoint").equals(serverAddressField.getText())) {
      Properties config = new Properties();
      config.setProperty("httpMethod", httpMethod.getValue().substring(0, httpMethod.getValue().length() - 3));

      String[] apiEndpoint = serverAddressField.getText().split("\\:");

      if (apiEndpoint.length != 2) {
        ConfigManager.reset();
        return;
      } else {
        config.setProperty("address", apiEndpoint[0]);
        config.setProperty("port", apiEndpoint[1]);
      }

      ConfigManager.saveConfiguration(config);
    }
  }
}
