package tugas;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Item> listOfItems = new ArrayList<>();
    static ArrayList<Payment> listOfPayments = new ArrayList<>();

    public static void seedData() {
        listOfItems.add(new Item("Kulkas", "Electronic", 4800000));
        listOfItems.add(new Item("TV", "Electronic", 1280000));
        listOfItems.add(new Item("Laptop", "Computer", 6000000));
        listOfItems.add(new Item("PC", "Computer", 12000000));
    }

    public static void printItem(Item item) {
        System.out.println("Nama    : " + item.getName());
        System.out.println("Tipe    : " + item.getType());
        System.out.println("Harga   : " + item.getPrice());
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opt, id, paymentType, pay;
        String approval;
        seedData();

        do {
            System.out.println("---Program Toko Elektronik---");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            opt = s.nextInt();
            if(opt == 1) {
                System.out.println("----Daftar Barang----");
                for(int i = 0; i < listOfItems.size(); i++) {
                    System.out.println("No      : " + (i+1));
                    printItem(listOfItems.get(i));
                    System.out.println("--------------------------");
                }
                System.out.print("Pilih: ");
                id = s.nextInt();

                if(id > 0 && id <= listOfItems.size()) {
                    printItem(listOfItems.get(id-1));

                    System.out.println("----Tipe Pembayaran----");
                    System.out.println("1. Cash");
                    System.out.println("2. Credit");
                    System.out.print("Pilih: ");
                    paymentType = s.nextInt();
                    s.nextLine();

                    switch(paymentType) {
                        case 1:
                            System.out.println("Bayar (Y/N): ");
                            approval = s.nextLine();
                            if(approval.equals("Y") || approval.equals("y")) {
                                System.out.println("Harga Pembayaran: " + listOfItems.get(id-1).getPrice());
                                System.out.print("Bayar: ");
                                pay = s.nextInt();
                                if(pay >= listOfItems.get(id-1).getPrice()) {
                                    listOfPayments.add(new Cash(listOfItems.get(id-1)));
                                    listOfPayments.get(listOfPayments.size()-1).setPaidOff();
                                    System.out.println("Transaksi telah dibayar lunas!");
                                }
                            } else if(approval.equals("N") || approval.equals("n")) {
                                listOfPayments.add(new Cash(listOfItems.get(id-1)));
                                System.out.println("Transaksi telah disimpan");
                            }
                            break;
                        case 2:
                            int installmentTime = 0;
                            while(installmentTime != 3 && installmentTime != 6 && installmentTime != 9 && installmentTime != 12) {
                                System.out.println("Lama Cicilan (3/6/9/12): ");
                                installmentTime = s.nextInt();
                            }
                            System.out.println("Harga Pembayaran: " + (listOfItems.get(id-1).getPrice() / installmentTime));
                            System.out.print("Bayar: ");
                            pay = s.nextInt();
                            if(pay >= listOfItems.get(id-1).getPrice() / installmentTime) {
                                listOfPayments.add(new Credit(listOfItems.get(id-1), installmentTime));
                                listOfPayments.get(listOfPayments.size()-1).setPaidOff();
                                System.out.println("Transaksi telah dibayar");
                            }

                            break;
                        default:
                            break;
                    }
                }
                System.out.println();
            } else if(opt == 2) {
                if(listOfPayments.isEmpty()) {
                    System.out.println("Belum ada transaksi");
                    System.out.println();
                } else {
                    for(int i = 0; i < listOfPayments.size(); i++) {
                        System.out.println("No              : " + (i+1));
                        System.out.println("Nama            : " + listOfPayments.get(i).getItemName());
                        System.out.println("Tipe            : " + listOfPayments.get(i).getItem().getType());
                        System.out.println("Status          : " + listOfPayments.get(i).getStatus());
                        System.out.println("Sisa Pembayaran : " + listOfPayments.get(i).getRemainingAmount());
                        System.out.println("--------------------------------");
                    }
                    System.out.print("Pilih no. transaksi: ");
                    int transaction = s.nextInt();

                    if(transaction <= listOfPayments.size() && transaction > 0) {
                        System.out.println("No                  : " + transaction);
                        System.out.println("Nama                : " + listOfPayments.get(transaction-1).getItemName());
                        System.out.println("Tipe                : " + listOfPayments.get(transaction-1).getItem().getType());
                        System.out.println("Status              : " + listOfPayments.get(transaction-1).getStatus());
                        System.out.println("Sisa Pembayaran     : " + listOfPayments.get(transaction-1).getRemainingAmount());
                        System.out.println("Harga Pembayaran    : " + listOfPayments.get(transaction-1).pay());

                        if(!listOfPayments.get(transaction-1).getPaidOff()) {
                            System.out.print("Bayar: ");
                            pay = s.nextInt();
                            switch(listOfPayments.get(transaction-1).getClassName()) {
                                case "CASH":
                                    if(pay >= listOfPayments.get(transaction-1).pay()) {
                                        listOfPayments.get(transaction-1).setPaidOff();
                                        System.out.println("Transaksi anda telah dibayar lunas. Terima kasih!");
                                    }
                                    break;
                                case "CREDIT":
                                    if(listOfPayments.get(transaction-1).getRemainingAmount() > 0 && pay >= listOfPayments.get(transaction-1).pay()) {
                                        listOfPayments.get(transaction-1).setPaidOff();
                                        if(listOfPayments.get(transaction-1).getPaidOff()) {
                                            System.out.println("Transaksi anda telah dibayar lunas. Terima kasih!");
                                        } else {
                                            System.out.println("Transaksi anda telah dibayar");
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    System.out.println();
                }
            } else if(opt < 0 || opt > 2) {
                System.out.println("Salah Input");
                System.out.println();
            }
        } while(opt != 0);
    }
}
