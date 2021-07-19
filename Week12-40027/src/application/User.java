package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class User {
    private String firstName, lastName, address;
    private Character gender;
    private List<Transaction> transactions;

    public User(String firstName, String lastName, Character gender, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.transactions = new ArrayList<>();
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getGender() {
        switch (gender) {
            case 'M':
                return "Male";
            case 'F':
                return "Female";
            default:
                return "Other";
        }
    }

    public String getAddress() {
        return this.address;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getPurchases() {
        return transactions
                .stream()
                .filter(transaction -> transaction instanceof Purchase)
                .collect(Collectors.toList());
    }

    public List<Transaction> getSales() {
        return transactions
                .stream()
                .filter(transaction -> transaction instanceof Sale)
                .collect(Collectors.toList());
    }

    public Long getIncome() {
        LongAdder income = new LongAdder();
        transactions.stream().forEach(t -> income.add(t.calculateTransaction()));
        return income.longValue();
    }
}
