package application;

public abstract class Transaction {
    protected Item item;
    protected Integer amount;

    public abstract Integer calculateTransaction();
    public abstract String getTransactionInfo();
}
