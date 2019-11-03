package sample;

import java.util.Date;

/**
 * The "objects" created from the ProductionRecord class appear as printed statements in the production record tab
 */
public class ProductionRecord {
  private int productionNumber;
  private int productID;
  private String productName;
  private String serialNumber;
  private Date dateProduced;
  private static int ifAU = 00001;
  private static int ifVI = 25001;
  private static int ifAM = 50001;
  private static int ifVM = 75001;

  ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  ProductionRecord(Product p, int count) {
    productName = p.name;
    String s1 = " ";
    if (p.manufacturer.charAt(2) == ' ') {
      s1 = p.manufacturer.substring(0, 2);
    } else {
      s1 = p.manufacturer.substring(0, 3);
    }

    String s2 = "";
    String s3 = "";
    switch (p.type) {
      case "AUDIO":
        s2 = ItemType.AUDIO.getCode();
        s3 = String.format("%05d" , ifAU++);
        break;
      case "VISUAL":
        s2 = ItemType.VISUAL.getCode();
        s3 = String.format("%05d" , ifVI++);
        break;
      case "AUDIO_MOBILE":
        s2 = ItemType.AUDIO_MOBILE.getCode();
        s3 = String.format("%05d" , ifAM++);
        break;
      case "VISUAL_MOBILE":
        s2 = ItemType.VISUAL_MOBILE.getCode();
        s3 = String.format("%05d" , ifVM++);
        break;
    }
    serialNumber = s1 + s2 + s3;
    dateProduced = new Date();
  }

  ProductionRecord(int productionNumber, int productID, String serialNumber) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    dateProduced = new Date();
  }

  @Override
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product Name: "
        + productName
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced;
  }

    /**
     * Gets and sets for various information that should be recorded during production
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
