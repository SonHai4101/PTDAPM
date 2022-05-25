package model;

public class Product {
    private String id;
    private String name;
    private int quantity;
    private String price;
    private byte[] image;

    public Product(String id, String name, int quantity, String price, byte[] image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "id = " + id + "\n" +
                "name = " + name + "\n" +
                "quantity = " + quantity + "\n" +
                "price = " + price + "\n" +
                "image = " + image;
    }
    public static String takeid = "";
}
