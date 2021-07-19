package application;

public class Sale extends Transaction {
    private Integer sellingPrice;

    public Sale(Item item, Integer amount, Integer sellingPrice) {
        this.item = item;
        this.amount = amount;
        this.sellingPrice = sellingPrice;
    }

    @Override
    public Integer calculateTransaction() {
        return sellingPrice * amount;
    }

    @Override
    public String getTransactionInfo() {
        return item.getName() + "(" + amount + " * " + sellingPrice + ")";
    }
}
