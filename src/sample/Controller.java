package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

  @FXML private Button btnAdd;
  @FXML private Button btnRecordProd;
  @FXML private ComboBox<Integer> cboQuantity;
  /**
   * Two text boxes serve to take user input and (on button click) create a new database entry
   * with this information
   */
  @FXML private TextField txtName;
  @FXML private TextField txtManufacturer;
  /**
   * Run Java database driver and prepare connection to the database "prodDB"
   */
  final String JDBC_DRIVER = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/prodDB";
  Statement stmt = null;
  Connection conn = null;
  String sql;

  /**
   * Initialize method fills combo box with 1-10 values and connects to the "prodDB" database
   */
  public void initialize() {
    for (int i = 1; i <= 10; i++) {
      cboQuantity.getItems().add(i);
    }
    cboQuantity.setEditable(true);
    cboQuantity.getSelectionModel().selectFirst();
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

  /**
   * AddProductClick method tells the user that a product has been successfully added, then calls
   * the AddProduct method to create a new database entry with the information from the two text
   * boxes related to product name and product manufacturer
   */
  @FXML
  void AddProductClick(MouseEvent event) {
    System.out.println("Product has been Added");
    AddProduct();
  }

  @FXML
  void RecordProduction(MouseEvent event) {
    System.out.println("Production has been Recorded");
  }

  public void AddProduct() {
    try {
      sql =
          "INSERT INTO PRODUCT (type, manufacturer, name) VALUES ( 'AUDIO', '"
              + txtManufacturer.getText()
              + "', '"
              + txtName.getText()
              + "' )";
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
