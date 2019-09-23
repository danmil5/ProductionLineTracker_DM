package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

  @FXML private Button btnAdd;
  @FXML private Button btnRecordProd;
  @FXML private ComboBox<Integer> cboQuantity;
  final String JDBC_DRIVER = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/prodDB";
  static Statement stmt = null;
  static Connection conn = null;
  static String sql;

  public void initialize() {
    for (int i = 1; i <= 10; i++) {
      cboQuantity.getItems().add(i);
    }
    cboQuantity.getSelectionModel().selectFirst();
    cboQuantity.setEditable(true);
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(DB_URL);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  void AddProductClick(MouseEvent event) {
    System.out.println("Product has been Added");
    AddProduct();
  }

  @FXML
  void RecordProduction(MouseEvent event) {
    System.out.println("Production has been Recorded");
  }

  public static void AddProduct() {
    try {
      sql = "INSERT INTO PRODUCT (type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' )";
      try {
        stmt.execute(sql);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (NullPointerException ex) {
      ex.printStackTrace();
    }
  }
}
