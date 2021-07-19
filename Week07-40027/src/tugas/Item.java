package tugas;

public class Item {
    private String name, type;
    private int price;

    public Item(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
