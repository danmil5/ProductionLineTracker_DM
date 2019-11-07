package sample;

import java.util.Date;

/**
 * The "objects" created from the ProductionRecord class appear as printed statements in the
 * production record tab
 */
public class ProductionRecord {
  private static int productionNumber = 0;
  private int productID;
  private String productName;
  private String serialNumber;
  private Date dateProduced;
  private static int runningSerial = 00001;

  ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 1 + getProductionNumber();
    serialNumber = "0";
    dateProduced = new Date();
  }

  ProductionRecord(Product p) {
    this.productID = p.getId();
    productName = p.name;
    String s1 = " ";
    s1 = p.manufacturer.substring(0, 3);
    for (int managerLetter = 0; managerLetter < 3; managerLetter++) {
      if (s1.charAt(managerLetter) == ' ') {
        // code here
      }
    }
    productionNumber = 1 + getProductionNumber();

    String s2 = "";
    String s3 = "";
    switch (p.type) {
      case "AUDIO":
        s2 = ItemType.AUDIO.getCode();
        break;
      case "VISUAL":
        s2 = ItemType.VISUAL.getCode();
        break;
      case "AUDIO_MOBILE":
        s2 = ItemType.AUDIO_MOBILE.getCode();
        break;
      case "VISUAL_MOBILE":
        s2 = ItemType.VISUAL_MOBILE.getCode();
        break;
    }
    s3 = String.format("%05d", runningSerial++);
    serialNumber = s1 + s2 + s3;
    dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID, String serialNumber) {
    this.productionNumber = 1 + getProductionNumber();
    this.productID = productID;
    this.serialNumber = serialNumber;
    dateProduced = new Date();
  }

  @Override
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Prod. ID: "
        + productID
        + " Product Name: "
        + productName
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced;
  }

  /** Gets and sets for various information that should be recorded during production */
  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }
}
