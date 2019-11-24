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
  private static int AUSerial = 00000;
  private static int VISerial = 00000;
  private static int AMSerial = 00000;
  private static int VMSerial = 00000;

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
        s3 = String.format("%05d", ++AUSerial);
        break;
      case "VISUAL":
        s2 = ItemType.VISUAL.getCode();
        s3 = String.format("%05d", ++VISerial);
        break;
      case "AUDIO_MOBILE":
        s2 = ItemType.AUDIO_MOBILE.getCode();
        s3 = String.format("%05d", ++AMSerial);
        break;
      case "VISUAL_MOBILE":
        s2 = ItemType.VISUAL_MOBILE.getCode();
        s3 = String.format("%05d", ++VMSerial);
        break;
    }
    serialNumber = s1 + s2 + s3;
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

  /**
   * These four set methods are called upon program startup to keep an accurate running total of
   * each of the four types of products that can be created.
   *
   * <p>The parameter XXSerial is the highest number derived from the last 5 characters amongst
   * every serial number of type XX that are searched and appended to the Production Log at startup.
   */
  public static void setAUSerial(String AUSerial) {
    ProductionRecord.AUSerial = Integer.parseInt(AUSerial);
  }
  public static void setVISerial(String VISerial) {
    ProductionRecord.VISerial = Integer.parseInt(VISerial);
  }
  public static void setAMSerial(String AMSerial) {
    ProductionRecord.AMSerial = Integer.parseInt(AMSerial);
  }
  public static void setVMSerial(String VMSerial) {
    ProductionRecord.VMSerial = Integer.parseInt(VMSerial);
  }
}
