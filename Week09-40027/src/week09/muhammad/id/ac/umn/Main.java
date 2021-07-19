package week09.muhammad.id.ac.umn;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Manager> managers = new ArrayList<>();
    static List<Tetap> tetaps = new ArrayList<>();
    static List<Magang> magangs = new ArrayList<>();

    public static String formatGaji(int gaji) {
        DecimalFormat kursIDR = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIDR.setDecimalFormatSymbols(formatRp);

        return kursIDR.format(gaji);
    }

    public static void showKaryawan() {
        System.out.println("----- Daftar Manager -----");
        if(!managers.isEmpty()) {
            for(int i = 0; i < managers.size(); i++) {
                System.out.println("ID      : " + managers.get(i).getId());
                System.out.println("Nama    : " + managers.get(i).getNama());
                System.out.println("Gaji    : " + formatGaji(managers.get(i).getGaji()));
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("Tidak ada");
        }

        System.out.println("----- Daftar Pegawai Tetap -----");
        if(!tetaps.isEmpty()) {
            for(int i = 0; i < tetaps.size(); i++) {
                System.out.println("ID      : " + tetaps.get(i).getId());
                System.out.println("Nama    : " + tetaps.get(i).getNama());
                System.out.println("Gaji    : " + formatGaji(tetaps.get(i).getGaji()));
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("Tidak ada");
        }

        System.out.println("----- Daftar Pegawai Magang -----");
        if(!magangs.isEmpty()) {
            for(int i = 0; i < magangs.size(); i++) {
                System.out.println("ID      : " + magangs.get(i).getId());
                System.out.println("Nama    : " + magangs.get(i).getNama());
                System.out.println("Gaji    : " + formatGaji(magangs.get(i).getGaji()));
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("Tidak ada");
        }

        System.out.println();
    }

    public static void addKaryawan(Scanner s) {
        System.out.println("----- Tambah Karyawan -----");
        System.out.println("1. Manajer");
        System.out.println("2. Karyawan Tetap");
        System.out.println("3. Karyawan Magang");
        System.out.print("Pilih: ");
        int role = s.nextInt();
        s.nextLine();

        System.out.print("Nama\t: ");
        String nama = s.nextLine();
        String id;
        switch (role) {
            case 1:
                id = String.format("M%d", (managers.size() + 1));
                managers.add(new Manager(id, nama));
                System.out.println("Manajer baru telah ditambahkan");
                break;
            case 2:
                id = String.format("R%d", (tetaps.size() + 1));
                tetaps.add(new Tetap(id, nama));
                System.out.println("Karyawan Tetap baru telah ditambahkan");
                break;
            case 3:
                id = String.format("I%d", (magangs.size() + 1));
                magangs.add(new Magang(id, nama));
                System.out.println("Karyawan Magang baru telah ditambahkan");
                break;
        }

        System.out.println();
    }

    public static void mainMenu() {
        System.out.println("----- Program Data Karyawan -----");
        System.out.println("1. Lihat Karyawan");
        System.out.println("2. Tambah Karyawan");
        System.out.println("3. Keluar");
        System.out.print("Pilih: ");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int menu;

        do {
            mainMenu();
            menu = s.nextInt();
            s.nextLine();

            switch (menu) {
                case 1:
                    showKaryawan();
                    break;
                case 2:
                    addKaryawan(s);
                    break;
                default:
                    System.out.println();
            }
        } while(menu != 3);
    }
}
