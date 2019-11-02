package sample;

public abstract class Product implements Item {
    int id;
    ItemType type;
    String manufacturer;
    String name;

    Product(String n, String m, ItemType t) {
        this.name = n;
        this.manufacturer = m;
        this.type = t;
    }

    public void setType(ItemType t) {
        this.type = t;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Name: "+name+"\nManufacturer: "+manufacturer
                +"\nType: "+type;
    }

    /**
     * methods implemented from the Item interface;
     * includes gets/sets for name/manufacturer/id
     */
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
