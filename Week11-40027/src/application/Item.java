package application;

public class Item {
	private int id;
	private String productName;
	private int price;
	private String category;
	private int stock;

	public Item(int id, String productName, int price, String category, int stock) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.stock = stock;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getProductName() { return productName; }

	public void setProductName(String productName) { this.productName = productName; }

	public int getPrice() { return price; }

	public void setPrice(int price) { this.price = price; }

	public String getCategory() { return category; }

	public void setCategory(String category) { this.category = category; }

	public int getStock() {	return stock; }

	public void setStock(int stock) { this.stock = stock; }
}