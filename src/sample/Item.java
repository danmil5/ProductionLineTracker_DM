package sample;

/**
 * Interface with sets and gets for common fields
 * among different types of products
 */
interface Item {
    int getId();
    void setName(String n);
    String getName();
    void setManufacturer(String m);
    String getManufacturer();
}
