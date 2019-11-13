package sample;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class Controller {

  //  @FXML private Button btnAdd;
  @FXML private Button btnRecordProd;
  @FXML private ComboBox<Integer> cboQuantity;
  @FXML private ChoiceBox<ItemType> choType;
  @FXML private TableColumn<?, ?> colName;
  @FXML private TableColumn<?, ?> colManufacturer;
  @FXML private TableColumn<?, ?> colType;
  @FXML private TableView<Product> tblProducts;
  @FXML private Label lblQuantityError;
  @FXML private Label lblBlankError;
  /**
   * Two text boxes serve to take user input and (on button click) create a new database entry with
   * this information
   */
  @FXML private TextField txtName;

  @FXML private TextField txtManufacturer;

  /** List/Text to add/record production events */
  @FXML private ListView<String> prodList;

  @FXML private TextArea prodLog;

  /**
   * Part of Issue 6: Observable list used to populate the table in Product Line and thus populate
   * the list view in the Produce tab
   */
  private ObservableList<Product> products = FXCollections.observableArrayList();

  /** Run Java database driver and prepare connection to the database "prodDB" */
  private static final String JDBC_DRIVER = "org.h2.Driver";

  private static final String DB_URL = "jdbc:h2:./res/prodDB";
  private Statement stmt;
  private Connection conn;
  private String sql;
  private String PASS;

  /**
   * Initialize method fills quantity combobox with 1-10 values and connects to the "prodDB"
   * database 11/2/19 and fills type combobox with valid type options from the enum ItemType
   */
  public void initialize() {
    /**
     * Disable the button used to produce products until a product has been selected from the
     * ListView control
     */
    btnRecordProd
        .disableProperty()
        .bind(prodList.getSelectionModel().selectedItemProperty().isNull());

    /**
     * Part of Issue 6: Tell tblProducts to read from the observable list that is populated whenever
     * the user enters a product for name, manufacturer, and type
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
      PASS = prop.getProperty("password");
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(DB_URL, "", PASS);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    /**
     * Call statement to test multimedia audio/videoplayer functions (may or may not be commented
     * out for testing)
     */
    // testMultimedia();
  }

  /**
   * AddProductClick method tells the user that a product has been successfully added, then calls
   * the AddProduct method to create a new database entry with the information from the two text
   * boxes related to product name and product manufacturer
   *
   * @param event whenever the user clicks the "Add Product" button
   */
  @FXML
  void AddProductClick(MouseEvent event) {
    if (txtManufacturer.getText().isEmpty() || txtName.getText().isEmpty()) {
      lblBlankError.setText("ERROR - Values cannot be left blank");
    } else {
      AddProduct();
      lblBlankError.setText("Product has been successfully added");
    }
  }

  /**
   * The RecordProduction method creates a ProductionRecord object for the type of object selected
   * in the ListView, for however many times specified by the quantity selected or entered by the
   * user, then appends the data collected from each of those objects' toString method into the
   * production log
   *
   * @param event whenever the user clicks the "Record Production" button
   */
  @FXML
  void RecordProduction(MouseEvent event) {
    try {
      for (int itemCount = 0;
          itemCount < Integer.parseInt(String.valueOf(cboQuantity.getValue()));
          itemCount++) {
        ProductionRecord newRecord =
            new ProductionRecord(products.get(prodList.getSelectionModel().getSelectedIndex()));
        prodLog.appendText(newRecord.toString() + "\n");
      }
      lblQuantityError.setText("Production has been Recorded");
    } catch (NumberFormatException nfe) {
      lblQuantityError.setText("ERROR - Quantity must be numeric");
    }
  }

  /**
   * AddProduct is called if the user enters valid product information and clicks the "add product"
   * button; it creates a widget object for testing and attempts to add the information from that
   * widget to the database, to the table in product line, and to the list view in the produce tab
   */
  private void AddProduct() {
    Product pr =
        new Widget(
            txtName.getText(), txtManufacturer.getText(), String.valueOf(choType.getValue()));
    /** Part of Issue 6: Populate observable list whenever the user enters a new product */
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

  /**
   * Sample method to test multimedia audio/videoplayer functions (call statement in intialize may
   * be commented out)
   */
  public static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }
}
