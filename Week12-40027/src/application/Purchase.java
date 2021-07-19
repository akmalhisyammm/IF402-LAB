package application;

public class Purchase extends Transaction {

    public Purchase(Item item, Integer amount) {
        this.item = item;
        this.amount = amount;
    }

    @Override
    public Integer calculateTransaction() {
        return item.getPrice() * amount;
    }

    @Override
    public String getTransactionInfo() {
        return item.getName() + "(" + amount + ")";
    }
}
