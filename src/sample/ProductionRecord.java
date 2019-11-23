package sample;

import java.util.Date;

/**
 * Daniel Miller
 * The ProductionRecord class serves to interpret the information in a product in such a way that
 * it can be printed in a singular line to a text area. This text area will be seen by employees
 * to monitor which types of products and how many of those types have been produced.
 * productionNumber = Running total of all completed products
 * productID = Corresponds to the order in which products were added and available for production
 * productName = The name of the product being produced
 * serialNumber = Separated into three different strings: s1, s2, and s3
 * s1 = The first three characters in the product manufacturer's name
 * s2 = The shorthand code for the product's item type from the ItemType enum assigned to it
 * s3 = The total products produced, unique to each of the four item types
 * dateProduced = The date and time when the product was produced
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
    s1 = s1.replaceAll(" ", "_");
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

  /** Gets and sets for various information that should be recorded during production
   * @return productionNumber returns the running production number value for the current product.
   */
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
