import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Barang> stuffs = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static void mainMenu() {
        System.out.println("---------- Menu Toko Voucher & HP ----------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Barang Baru");
    }

    public static void showHandphone() {
        for(Barang stuff : stuffs) {
            if(stuff.getId().contains("H")) {
                Handphone handphone = (Handphone) stuff;
                System.out.println("ID      : " + handphone.getId());
                System.out.println("Nama    : " + handphone.getNama() + " " + handphone.getWarna());
                System.out.println("Stok    : " + handphone.getStok());
                System.out.println("Harga   : " + (int) handphone.getHarga());
                System.out.println("----------------------------------------");
            }
        }
    }

    public static void showVoucher() {
        for(Barang stuff : stuffs) {
            if(stuff.getId().contains("V")) {
                Voucher voucher = (Voucher) stuff;
                System.out.println("ID      : " + voucher.getId());
                System.out.println("Nama    : " + voucher.getNama());
                System.out.println("Nominal : " + (int) voucher.getHarga());
                System.out.println("Stok    : " + voucher.getStok());
                System.out.println("Harga   : " + (int) voucher.getHargaJual());
                System.out.println("----------------------------------------");
            }
        }
    }

    public static void showOrder() {
        for(Order order : orders) {
            if(order.getHandphone() != null) {
                System.out.println("ID      : " + order.getId());
                System.out.println("Nama    : " + order.getHandphone().getNama() + " " + order.getHandphone().getWarna());
                System.out.println("Jumlah  : " + order.getJumlah());
                System.out.println("Total   : " + (int) order.getHandphone().getHarga() * order.getJumlah());
            } else {
                System.out.println("ID      : " + order.getId());
                System.out.println("Nama    : " + order.getVoucher().getNama() + " " + (int) order.getVoucher().getHarga());
                System.out.println("Jumlah  : " + order.getJumlah());
                System.out.println("Total   : " + (int) order.getVoucher().getHargaJual() * order.getJumlah());
            }
            System.out.println("----------------------------------------");
        }
        System.out.println();
    }

    public static boolean isItemId(String id) {
        for(Barang stuff : stuffs) {
            if(stuff.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEnoughStock(String id, int amount) {
        for(Barang stuff : stuffs) {
            if(stuff.getId().equals(id) && stuff.getStok() >= amount) {
                return true;
            }
        }

        return false;
    }

    public static void addItem() {
        Scanner s = new Scanner(System.in);
        int harga, stok;
        String id;
        float ppn;
        String item, nama, warna;

        System.out.print("Voucher / Handphone (V/H) : ");
        item = s.nextLine();
        System.out.print("Nama                      : ");
        nama = s.nextLine();
        System.out.print("Harga                     : ");
        harga = s.nextInt();
        System.out.print("Stok                      : ");
        stok = s.nextInt();

        if(item.equalsIgnoreCase("V")) {
            System.out.print("PPN                       : ");
            ppn = s.nextFloat();
            id = String.format("V%02d", ++Voucher.total);
            stuffs.add(new Voucher(id, nama, harga, stok, ppn));
            System.out.println("Voucher telah berhasil diinput");
        } else if(item.equalsIgnoreCase("H")) {
            s.nextLine();
            System.out.print("Warna                     : ");
            warna = s.nextLine();
            id = String.format("H%02d", ++Handphone.total);
            stuffs.add(new Handphone(id, nama, harga, stok, warna));
            System.out.println("Handphone telah berhasil diinput");
        } else {
            System.out.println("Input tidak valid!");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int menu, item, amount, pay, totalPrice;
        String id;

        for(;;) {
            mainMenu();
            System.out.print("Pilihan: ");
            menu = s.nextInt();

            switch(menu) {
                case 1:
                    System.out.println("Daftar Barang Toko Voucher & HP");
                    System.out.println("1. Handphone");
                    System.out.println("2. Voucher");
                    System.out.print("Pilihan: ");
                    item = s.nextInt();
                    s.nextLine();
                    switch(item) {
                        case 1:
                            showHandphone();
                            System.out.println("Ketik 0 untuk batal");

                            // ID Barang
                            System.out.print("Pesan Barang (ID)     : ");
                            id = s.nextLine();
                            if(!isItemId(id)) {
                                System.out.println();
                                continue;
                            }

                            // Jumlah Barang
                            System.out.print("Masukkan Jumlah       : ");
                            amount = s.nextInt();
                            if(!isEnoughStock(id, amount)) {
                                System.out.println("Stok tidak mencukupi");
                                System.out.println();
                                continue;
                            }

                            // Pembayaran
                            for(Barang stuff : stuffs) {
                                if(stuff.getId().equalsIgnoreCase(id)) {
                                    Handphone handphone = (Handphone) stuff;
                                    totalPrice = (int) (amount * handphone.getHarga());
                                    System.out.println(amount + " @ " + handphone.getNama() + " dengan total harga " + totalPrice);
                                    System.out.print("Masukkan jumlah uang  : ");
                                    pay = s.nextInt();
                                    if(pay >= totalPrice) {
                                        handphone.minusStok(amount);
                                        String orderId = String.format("O%s-%d", stuff.getId(), Order.total++);
                                        orders.add(new Order(orderId, handphone, amount));
                                        System.out.println("Berhasil dipesan");
                                    } else {
                                        System.out.println("Jumlah uang tidak mencukupi");
                                    }
                                    System.out.println();
                                }
                            }
                            break;
                        case 2:
                            showVoucher();

                            System.out.println("Ketik 0 untuk batal");

                            // ID Barang
                            System.out.print("Pesan Barang (ID)     : ");
                            id = s.nextLine();
                            if(!isItemId(id)) {
                                System.out.println();
                                continue;
                            }

                            // Jumlah Barang
                            System.out.print("Masukkan Jumlah       : ");
                            amount = s.nextInt();
                            if(!isEnoughStock(id, amount)) {
                                System.out.println("Stok tidak mencukupi");
                                System.out.println();
                                continue;
                            }

                            // Pembayaran
                            for(Barang stuff : stuffs) {
                                if(stuff.getId().equalsIgnoreCase(id)) {
                                    Voucher voucher = (Voucher) stuff;
                                    totalPrice = (int) (amount * voucher.getHargaJual());
                                    System.out.println(amount + " @ " + voucher.getNama() + " " + (int) voucher.getHarga() + " dengan harga " + totalPrice);
                                    System.out.print("Masukkan jumlah uang  : ");
                                    pay = s.nextInt();
                                    if(pay >= totalPrice) {
                                        voucher.minusStok(amount);
                                        String orderId = String.format("O%s-%d", stuff.getId(), Order.total++);
                                        orders.add(new Order(orderId, voucher, amount));
                                        System.out.println("Berhasil dipesan");
                                    } else {
                                        System.out.println("Jumlah uang tidak mencukupi");
                                    }
                                    System.out.println();
                                }
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Daftar Pesanan Toko Multiguna");
                    showOrder();
                    break;
                case 3:
                    addItem();
                    break;
                default:
                    System.out.println("Input tidak valid!");
                    System.out.println();
            }
        }
    }
}
