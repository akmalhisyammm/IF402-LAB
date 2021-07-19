package week03.muhammad.id.ac.umn;

import java.util.Scanner;

public class Tugas2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Nama Anda : ");
        String nama = scanner.nextLine();

        System.out.println("--------------------------------------");
        System.out.println("String anda : " + nama);
        System.out.println("1. charAt           2. length");
        System.out.println("3. substring(n)     4. substring(m, n)");
        System.out.println("5. contains         6. concat");
        System.out.println("7. replace          8. split");
        System.out.println("9. lowerCase        10. upperCase");
        System.out.print("Pilih menu : ");
        int input = scanner.nextInt();
        int idx, firstIdx, lastIdx;
        String str, oldStr, newStr;

        switch(input) {
            case 1:
                System.out.println("-----charAt-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input : ");
                idx = scanner.nextInt();
                System.out.println("Hasil : " + nama.charAt(idx));
                break;
            case 2:
                System.out.println("-----length-----");
                System.out.println("Nama : " + nama);
                System.out.println("Input : " + nama.length());
                break;
            case 3:
                System.out.println("-----substring(n)-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input : ");
                idx = scanner.nextInt();
                System.out.println("Hasil : " + nama.substring(idx));
                break;
            case 4:
                System.out.println("-----substring(m,n)-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input mulai : ");
                firstIdx = scanner.nextInt();
                System.out.print("Input akhir : ");
                lastIdx = scanner.nextInt();
                System.out.println("Hasil : " + nama.substring(firstIdx, lastIdx));
                break;
            case 5:
                System.out.println("-----contains-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input : ");
                str = scanner.next();
                System.out.println("Hasil : " + nama.contains(str));
                break;
            case 6:
                System.out.println("-----concat-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input : ");
                scanner.nextLine();
                str = scanner.nextLine();
                System.out.println("Hasil : " + nama.concat(str));
                break;
            case 7:
                System.out.println("-----replace-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input kata yang diganti : ");
                oldStr = scanner.next();
                System.out.print("Input kata pengganti : ");
                newStr = scanner.next();
                System.out.println("Hasil : " + nama.replace(oldStr, newStr));
                break;
            case 8:
                System.out.println("-----split-----");
                System.out.println("Nama : " + nama);
                System.out.print("Input : ");
                str = scanner.next();
                String[] arrOfStr = nama.split(str);
                for(String results : arrOfStr) {
                    System.out.println("Hasil : " + results);
                }
                break;
            case 9:
                System.out.println("-----lowerCase-----");
                System.out.println("Nama : " + nama);
                System.out.println("Hasil : " + nama.toLowerCase());
                break;
            case 10:
                System.out.println("-----upperCase-----");
                System.out.println("Nama : " + nama);
                System.out.println("Hasil : " + nama.toUpperCase());
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
}
