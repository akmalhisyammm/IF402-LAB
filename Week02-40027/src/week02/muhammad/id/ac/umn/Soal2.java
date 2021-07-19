package week02.muhammad.id.ac.umn;

import java.util.Scanner;

public class Soal2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan angka : ");
        int input = scanner.nextInt();

        String output = isPrime(input) ? "Angka " + input + " adalah angka prima" : "Angka " + input + " adalah bukan angka prima";

        System.out.println(output);
    }

    public static boolean isPrime(int number) {
        if(number <= 1) {
            return false;
        }
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
