public class GroceryItem {
    private String name;
    private String Price;
    private String type;
    private String expiration;

    public GroceryItem() {}

    public GroceryItem(String name, String price, String type, String expiration) {
        this.name = name;
        this.Price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s, %s]", name, Price, type, expiration);
    }
}
