package sample;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * @author Daniel Miller
 *     <p>A Text-Based Production Line Simulator.
 */
public class Controller {

  /*
   * Define common FXML components that will be populated with information to interact with the
   * user.
   */
  @FXML private Button btnRecordProd;
  @FXML private ComboBox<Integer> cboQuantity;
  @FXML private ChoiceBox<ItemType> choType;
  @FXML private TableColumn<?, ?> colName;
  @FXML private TableColumn<?, ?> colManufacturer;
  @FXML private TableColumn<?, ?> colType;
  @FXML private TableView<Product> tblProducts;
  @FXML private Label lblQuantityError;
  @FXML private Label lblBlankError;
  @FXML private Label lblProdLog;
  @FXML private TabPane tabPane;
  /*
   * Two text boxes serve to take user input and (on button click) create a new database entry with
   * this information.
   */
  @FXML private TextField txtName;
  @FXML private TextField txtManufacturer;

  /* List/Text to add/record production events. */
  @FXML private ListView<String> prodList;
  @FXML private TextArea prodLog;

  /*
   * Part of Issue 6: Observable list used to populate the table in Product Line and thus populate
   * the list view in the Produce tab.
   */
  private ObservableList<Product> products = FXCollections.observableArrayList();

  /*
   * Run Java database driver and prepare connection to the database "prodDB" - Includes other
   * variables used in database-related functions.
   */
  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/prodDB";
  private Statement stmt;
  private Connection conn;
  private String sql;
  private String pass;
  PreparedStatement ps;
  PreparedStatement psNameFromID;
  ResultSet rs;
  ResultSet rsNameFromID;
  String lastID;

  /* Fade transitions for error and confirmation messages. */
  private FadeTransition fadeOutBlankError = new FadeTransition(Duration.millis(6000));
  private FadeTransition fadeOutQuantityError = new FadeTransition(Duration.millis(6000));

  /**
   * @author Daniel Miller
   *     <p>The initialize statement is primarily used to fill the tableview, listview, and text
   *     area with pre-existing database information, and to prepare features that enchance the
   *     user's experience such as fading labels and preset combobox values.
   */
  public void initialize() {
    /*
     * Assign the defined fade transition objects to the two labels that are used to present
     * error/confirmation messages to the user, and set the specifics so that these labels fade out
     * of view.
     */
    fadeOutBlankError.setNode(lblBlankError);
    fadeOutBlankError.setFromValue(1.0);
    fadeOutBlankError.setToValue(0.0);
    fadeOutBlankError.setCycleCount(1);
    fadeOutBlankError.setAutoReverse(false);
    fadeOutQuantityError.setNode(lblQuantityError);
    fadeOutQuantityError.setFromValue(1.0);
    fadeOutQuantityError.setToValue(0.0);
    fadeOutQuantityError.setCycleCount(1);
    fadeOutQuantityError.setAutoReverse(false);
    /*
     * Disable the button used to produce products until a product has been selected from the
     * ListView control.
     */
    btnRecordProd
        .disableProperty()
        .bind(prodList.getSelectionModel().selectedItemProperty().isNull());

    /*
     * Part of Issue 6: Tell tblProducts to read from the observable list that is populated whenever
     * the user enters a product for name, manufacturer, and type.
     */
    colName.setCellValueFactory(new PropertyValueFactory("name"));
    colManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    colType.setCellValueFactory(new PropertyValueFactory("type"));
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
      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties"));
      pass = prop.getProperty("password");
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(DB_URL, "", pass);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    /*
     * Get older products from the PRODUCT database table and add them to Product Line and
     * Production tabs.
     */
    try {
      setupProductLineTable();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    /* Update the Production Log table. */
    try {
      setupProductionLog();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    /*
     * "Update" the Production Log table's "Last Updated" date whenever the user selects the
     * Production Log tab to create a better user experience.
     */
    tabPane
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
              if ("tbaProdLog".equals(newValue.getId())) {
                lblProdLog.setText("Production Log - Last Updated " + new Date());
              }
            });
  }

  private void setupProductLineTable() throws SQLException {
    sql = "SELECT * FROM PRODUCT";
    PreparedStatement ps = conn.prepareStatement(sql);
    rs = ps.executeQuery();
    while (rs.next()) {
      Product pr =
          new Widget(rs.getString("NAME"), rs.getString("MANUFACTURER"), rs.getString("TYPE"));
      products.add(pr);
      prodList.getItems().add(pr.getName());
    }
  }

  private void setupProductionLog() throws SQLException {
    sql = "SELECT * FROM PRODUCTIONRECORD";
    ps = conn.prepareStatement(sql);
    rs = ps.executeQuery();
    while (rs.next()) {
      /* Checking for consecutive id numbers reduces unneeded database searching. */
      if (rs.getString("PRODUCT_ID") != lastID) {
        sql = "SELECT NAME FROM PRODUCT WHERE ID='" + rs.getInt("PRODUCT_ID") + "'";
        psNameFromID = conn.prepareStatement(sql);
        rsNameFromID = psNameFromID.executeQuery();
        rsNameFromID.next();
      }
      lastID = rs.getString("PRODUCT_ID");
      prodLog.appendText(
          "Prod. Num: "
              + rs.getString("PRODUCTION_NUM")
              + " - Product Name: "
              + rsNameFromID.getString(1)
              + " - Serial Num: "
              + rs.getString("SERIAL_NUM")
              + " - Date: "
              + rs.getString("DATE_PRODUCED")
              + "\n");
      /*
       * Searches each serial number for its type, then sets the running total 5-digit serial number
       * code to whatever the highest 5-digit code is for each type among pre-existing logs at
       * startup.
       */
      if (rs.getString("SERIAL_NUM").substring(3, 5).equals("AU")) {
        ProductionRecord.setAuSerial(
            rs.getString("SERIAL_NUM").substring(rs.getString("SERIAL_NUM").length() - 5));
      }
      if (rs.getString("SERIAL_NUM").substring(3, 5).equals("VI")) {
        ProductionRecord.setViSerial(
            rs.getString("SERIAL_NUM").substring(rs.getString("SERIAL_NUM").length() - 5));
      }
      if (rs.getString("SERIAL_NUM").substring(3, 5).equals("AM")) {
        ProductionRecord.setAmSerial(
            rs.getString("SERIAL_NUM").substring(rs.getString("SERIAL_NUM").length() - 5));
      }
      if (rs.getString("SERIAL_NUM").substring(3, 5).equals("VM")) {
        ProductionRecord.setVmSerial(
            rs.getString("SERIAL_NUM").substring(rs.getString("SERIAL_NUM").length() - 5));
      }
    }
    lblProdLog.setText("Production Log - Last Updated " + new Date());
  }

  /**
   * AddProductClick method tells the user that a product has been successfully added, then calls
   * the AddProduct method to create a new database entry with the information from the two text
   * boxes related to product name and product manufacturer.
   *
   * @param event whenever the user clicks the "Add Product" button.
   */
  @FXML
  void addProductClick(MouseEvent event) {
    if (txtManufacturer.getText().isEmpty() || txtName.getText().isEmpty()) {
      lblBlankError.setText("ERROR - Values cannot be left blank");
      lblBlankError.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
      fadeOutBlankError.playFromStart();
    } else {
      addProduct();
      lblBlankError.setText("Product has been successfully added");
      lblBlankError.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
      fadeOutBlankError.playFromStart();
    }
  }

  /**
   * The RecordProduction method creates a ProductionRecord object for the type of object selected
   * in the ListView, for however many times specified by the quantity selected or entered by the
   * user, then appends the data collected from each of those objects' toString method into the
   * production log.
   *
   * @param event whenever the user clicks the "Record Production" button.
   */
  @FXML
  void recordProduction(MouseEvent event) {
    try {
      for (int itemCount = 0;
          itemCount < Integer.parseInt(String.valueOf(cboQuantity.getValue()));
          itemCount++) {
        ProductionRecord newRecord =
            new ProductionRecord(products.get(prodList.getSelectionModel().getSelectedIndex()));
        prodLog.appendText(newRecord.toString() + "\n");
        sql =
            "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES ( '"
                + newRecord.getProductID()
                + "', '"
                + newRecord.getSerialNumber()
                + "', '"
                + new Timestamp(newRecord.getDateProduced().getTime())
                + "' )";
        try {
          stmt.execute(sql);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      lblQuantityError.setText("Production has been Recorded");
      lblQuantityError.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
      fadeOutQuantityError.playFromStart();
    } catch (NumberFormatException nfe) {
      lblQuantityError.setText("ERROR - Quantity must be numeric");
      lblQuantityError.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
      fadeOutQuantityError.playFromStart();
    }
    lblProdLog.setText("Production Log - Last Updated " + new Date());
  }

  /**
   * AddProduct is called if the user enters valid product information and clicks the "add product"
   * button; it creates a widget object for testing and attempts to add the information from that
   * widget to the database, to the table in product line, and to the list view in the produce tab.
   */
  private void addProduct() {
    Product pr =
        new Widget(
            txtName.getText(), txtManufacturer.getText(), String.valueOf(choType.getValue()));
    /* Part of Issue 6: Populate observable list whenever the user enters a new product. */
    products.add(pr);
    prodList.getItems().add(pr.getName());
    try {
      sql =
          "INSERT INTO PRODUCT (type, manufacturer, name) VALUES ( '"
              + pr.getType()
              + "', '"
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
