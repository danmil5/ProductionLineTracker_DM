package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class Controller {

  @FXML private Button btnAdd;
  @FXML private Button btnRecordProd;
  @FXML private ComboBox<Integer> cboQuantity;

  public void initialize() {
    for (int i=1; i<=10;i++) {
      cboQuantity.getItems().add(i);
      cboQuantity.getSelectionModel().selectFirst();
      cboQuantity.setEditable(true);
    }
  }

  @FXML
  void AddProductClick(MouseEvent event) {
    System.out.println("Product has been Added");
    Main.AddProduct();
  }

  @FXML
  void RecordProduction(MouseEvent event) {
    System.out.println("Production has been Recorded");
  }


}

