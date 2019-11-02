package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

  @FXML private Button btnAdd;
  @FXML private Button btnRecordProd;
  @FXML private ComboBox<Integer> cboQuantity;
  @FXML private ChoiceBox<ItemType> choType;
  @FXML private TableColumn<?, ?> colWidgets;
  @FXML private TableView<Product> tblProducts;
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
   * Observable list used to populate the table in Product Line and thus populate the listview in the Produce tab
   */
  ObservableList<Product> products = FXCollections.observableArrayList();

  /**
   * Initialize method fills quantity combobox with 1-10 values and connects to the "prodDB" database
   * 11/2/19 and fills type combobox with valid type options from the enum ItemType
   */
  public void initialize() {
    colWidgets.setCellValueFactory(new PropertyValueFactory("name"));
    tblProducts.setItems(products);

    for (int i = 1; i <= 10; i++) {
      cboQuantity.getItems().add(i);
    }
    cboQuantity.setEditable(true);
    cboQuantity.getSelectionModel().selectFirst();
    for (ItemType type : ItemType.values()) {
      choType.getItems().add(type);
    }
    choType.getSelectionModel().selectFirst();
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
    if (txtManufacturer.getText().isEmpty() || txtName.getText().isEmpty()) {
      System.out.println("ERROR - Values cannot be blank");
    } else {
      AddProduct();
      System.out.println("Product has been Added");
    }
  }

  @FXML
  void RecordProduction(MouseEvent event) {
    System.out.println("Production has been Recorded");
  }

  public void AddProduct() {
    Product pr = new Widget(txtName.getText(), txtManufacturer.getText(), choType.getValue());
    products.add(pr);

    try {
      sql =
          "INSERT INTO PRODUCT (type, manufacturer, name) VALUES ( '"+pr.getType()+"', '"
              + pr.getManufacturer()
              + "', '"
              + pr.getName()
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
