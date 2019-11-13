package sample;

/**
 * Daniel Miller
 * The Product class is what every single product produced by this program is derived from.
 * Because of this, the product class is designed to be abstract because it does not contain
 * the additional fields and methods required to set a product apart from other products.
 * The product class also implements the Item interface, which contains methods that should be
 * available for use in all products such as common get and set methods.
 */
public abstract class Product implements Item {
  static private int productCount = 0;
  private int id = 0;
  protected String name;
  protected String manufacturer;
  protected String type;

  Product(String n, String m, String t) {
    this.id = ++productCount;
    this.name = n;
    this.manufacturer = m;
    this.type = t;
  }

  public void setType(String t) {
    this.type = t;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }

  /** methods implemented from the Item interface; includes gets/sets for name/manufacturer/id */
  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setName(String n) {
    this.name = n;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String m) {
    this.manufacturer = m;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }
}
