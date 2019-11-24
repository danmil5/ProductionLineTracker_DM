package sample;

/**
 * @author Daniel Miller
 *     <p>The Item interface contains get and set methods that should be universally found in every
 *     product, and exists to be implemented by the Product class because of this.
 */
interface Item {
  int getId();

  void setName(String n);

  String getName();

  void setManufacturer(String m);

  String getManufacturer();
}
