module me.thedustbuster {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.google.gson;

  // https://stackoverflow.com/questions/72769462/failed-making-field-property-accessible-either-change-its-visibility-or-write
  opens me.thedustbuster.jsonmodels to com.google.gson;
  opens me.thedustbuster.util.math to com.google.gson;
  opens me.thedustbuster.util to com.google.gson;

  opens me.thedustbuster to javafx.fxml;

  exports me.thedustbuster;
}
