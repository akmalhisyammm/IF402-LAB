package week02.muhammad.id.ac.umn;

import java.util.Scanner;

public class Soal3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nilai terkecil : ");
        int minValue = scanner.nextInt();

        System.out.print("Masukkan nilai terbesar : ");
        int maxValue = scanner.nextInt();

        int result = 0;

        for(int i = minValue + 1; i < maxValue; i++) {
            if(isPrime(i)) {
                result += i;
            }
        }

        System.out.println("Jumlah dari semua nilai prima dari " + minValue + " sampai " + maxValue + " adalah " + result);
    }

    public static boolean isPrime(int number) {
        if(number <= 1) {
            return false;
        }
        for(int i = 2; i <= number / 2; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
