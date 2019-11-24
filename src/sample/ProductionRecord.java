package sample;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Daniel Miller
 *     <p>The ProductionRecord class serves to interpret the information in a product in such a way
 *     that it can be printed in a singular line to a text area. This text area will be seen by
 *     employees to monitor which types of products and how many of those types have been produced.
 *     <p>productionNumber = Running total of all completed products
 *     <p>productID = Corresponds to the order in which products were added and available for
 *     production
 *     <p>productName = The name of the product being produced
 *     <p>serialNumber = Separated into three different strings: s1, s2, and s3
 *     <p>s1 = The first three characters in the product manufacturer's name
 *     <p>s2 = The shorthand code for the product's item type from the ItemType enum assigned to it
 *     <p>s3 = The total products produced, unique to each of the four item types
 *     <p>dateProduced = The date and time when the product was produced
 */
public class ProductionRecord {
  private static int productionNumber = 0;
  private int productID;
  private String productName;
  private String serialNumber;
  private Date dateProduced;
  private static int auSerial = 00000;
  private static int viSerial = 00000;
  private static int amSerial = 00000;
  private static int vmSerial = 00000;

  ProductionRecord(Product p) {
    this.productID = p.getId();
    productName = p.name;
    String s1 = "";
    /* Substring the first three characters of the manufacturer's name to add to the serial number,
    but add leading spaces if the manufacturer's name is less than three characters, then
    replace all spaces with underscores.
     */
    try {
      s1 = p.manufacturer.substring(0, 3);
    } catch (Exception e) {
      s1 = String.format("%3s", p.manufacturer);
    }
    s1 = s1.replaceAll(" ", "_");
    productionNumber = 1 + getProductionNumber();

    String s2 = "";
    String s3 = "";
    switch (p.type) {
      case "AUDIO":
        s2 = ItemType.AUDIO.getCode();
        s3 = String.format("%05d", ++auSerial);
        break;
      case "VISUAL":
        s2 = ItemType.VISUAL.getCode();
        s3 = String.format("%05d", ++viSerial);
        break;
      case "AUDIO_MOBILE":
        s2 = ItemType.AUDIO_MOBILE.getCode();
        s3 = String.format("%05d", ++amSerial);
        break;
      case "VISUAL_MOBILE":
        s2 = ItemType.VISUAL_MOBILE.getCode();
        s3 = String.format("%05d", ++vmSerial);
        break;
      default:
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
        + new Timestamp(dateProduced.getTime());
  }

  public int getProductionNumber() {
    return productionNumber;
  }

  public static void setProductionNumber(String productionNumber) {
    /* Accepts a string to lower time spent parsing to an int while database info is extracted. */
    ProductionRecord.productionNumber = Integer.parseInt(productionNumber);
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
    return (Date) dateProduced.clone();
  }

  /*
   * These four set methods are called upon program startup to keep an accurate running total of
   * each of the four types of products that can be created.
   *
   * The parameter XXSerial is the highest number derived from the last 5 characters amongst
   * every serial number of type XX that are searched and appended to the Production Log at startup.
   */
  public static void setAuSerial(String auSerial) {
    ProductionRecord.auSerial = Integer.parseInt(auSerial);
  }

  public static void setViSerial(String viSerial) {
    ProductionRecord.viSerial = Integer.parseInt(viSerial);
  }

  public static void setAmSerial(String amSerial) {
    ProductionRecord.amSerial = Integer.parseInt(amSerial);
  }

  public static void setVmSerial(String vmSerial) {
    ProductionRecord.vmSerial = Integer.parseInt(vmSerial);
  }
}
