package week04.muhammad.id.ac.umn;

import java.util.Scanner;

public class Tugas1 {
    static Barang[] stuffs = new Barang[5];
    static Order[] orders = new Order[25];

    public static void seedStuffs() {
        stuffs[0] = new Barang(1, "Pulpen Easy Gel 0.5mm", 120, 2000);
        stuffs[1] = new Barang(2, "Penggaris 30cm", 30, 5000);
        stuffs[2] = new Barang(3, "Tipe-x Roller", 30, 7000);
        stuffs[3] = new Barang(4, "Pensil Mekanik", 50, 5000);
        stuffs[4] = new Barang(5, "Buku Tulis", 100, 6000);
    }

    public static void seedOrders(int id, Barang stuff, int amount) {
        orders[Order.total] = new Order(id, stuff, amount);
        Order.total++;
    }

    public static void mainMenu() {
        System.out.println("-------Menu Toko Multiguna-------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
    }

    public static void showBarang() {
        System.out.println("Daftar Barang Toko Multiguna");
        for(Barang stuff : stuffs) {
            System.out.println("ID      : " + stuff.getId());
            System.out.println("Nama    : " + stuff.getNama());
            System.out.println("Stock   : " + stuff.getStock());
            System.out.println("Harga   : " + stuff.getHarga());
            System.out.println("-------------------------------");
        }
    }

    public static void showOrder() {
        System.out.println("Daftar Pesanan Toko Multiguna");
        for(int i = 0; i < Order.total; i++) {
            System.out.println("ID      : " + orders[i].getId());
            System.out.println("Nama    : " + orders[i].getBarang().getNama());
            System.out.println("Jumlah  : " + orders[i].getJumlah());
            System.out.println("Total   : " + (orders[i].getBarang().getHarga() * orders[i].getJumlah()));
            System.out.println("-------------------------------");
        }
        System.out.println();
    }

    public static boolean isStuffId(int id) {
        for(Barang stuff : stuffs) {
            if(stuff.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEnoughStock(int id, int stock) {
        for(Barang stuff : stuffs) {
            if(stuff.getId() == id && stuff.getStock() >= stock) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        seedStuffs();
        int menu, id, amount, pay, totalPrice;

        for(;;) {
            mainMenu();
            System.out.print("Pilihan : ");
            menu = in.nextInt();

            switch(menu) {
                case 1:
                    showBarang();
                    System.out.println("Ketik 0 untuk batal");

                    // ID Barang
                    System.out.print("Pesan Barang (ID) : ");
                    id = in.nextInt();
                    if(!isStuffId(id)) {
                        System.out.println();
                        continue;
                    }

                    // Jumlah Barang
                    do {
                        System.out.print("Masukkan Jumlah : ");
                        amount = in.nextInt();
                    } while(!isEnoughStock(id, amount));

                    // Pembayaran
                    for(Barang stuff : stuffs) {
                        if(stuff.getId() == id) {
                            totalPrice = amount * stuff.getHarga();
                            System.out.println(amount + " @ " + stuff.getNama() + " dengan total harga " + totalPrice);
                            System.out.print("Masukkan Jumlah Uang : ");
                            pay = in.nextInt();
                            if(pay >= totalPrice) {
                                stuff.minusStock(amount);
                                seedOrders(Order.total + 1, stuff, amount);
                                System.out.println("Berhasil dipesan");
                            } else {
                                System.out.println("Jumlah uang tidak mencukupi");
                            }
                            System.out.println();
                        }
                    }
                    break;
                case 2:
                    showOrder();
                    break;
                default:
                    System.out.println("Input tidak valid!");
                    System.out.println();
            }
        }
    }
}
