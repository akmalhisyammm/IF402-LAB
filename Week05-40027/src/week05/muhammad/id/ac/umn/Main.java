package week05.muhammad.id.ac.umn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int menu;

        do {
            System.out.println("-------- Program Menghitung Bangun Ruang --------");
            System.out.println("1. Lingkaran");
            System.out.println("2. Persegi");
            System.out.println("3. Persegi Panjang");
            System.out.println("4. Segitiga");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            menu = s.nextInt();

            switch(menu) {
                case 1:
                    System.out.println("---------------- Lingkaran ----------------");
                    // Input
                    System.out.print("Masukkan jari-jari  : ");
                    double radius = s.nextDouble();
                    // Print
                    Circle circle = new Circle(radius, "Biru");
                    System.out.println("Warna               : " + circle.getColor());
                    System.out.println("Jari-jari           : " + circle.getRadius());
                    System.out.println("Keliling Lingkaran  : " + circle.getPerimeter());
                    System.out.println("Luas Lingkaran      : " + circle.getArea());
                    System.out.println();
                    break;
                case 2:
                    System.out.println("---------------- Persegi ----------------");
                    // Input
                    System.out.print("Masukkan panjang sisi   : ");
                    int side = s.nextInt();
                    // Print
                    Square square = new Square(side, "Merah");
                    System.out.println("Warna                   : " + square.getColor());
                    System.out.println("Panjang sisi            : " + square.getSide());
                    System.out.println("Keliling Persegi        : " + square.getPerimeter());
                    System.out.println("Luas Persegi            : " + square.getArea());
                    System.out.println();
                    break;
                case 3:
                    System.out.println("---------------- Persegi Panjang ----------------");
                    // Input
                    System.out.print("Masukkan panjang            : ");
                    int length = s.nextInt();
                    System.out.print("Masukkan lebar              : ");
                    int width = s.nextInt();
                    // Print
                    Rectangle rectangle = new Rectangle(length, width, "Ungu");
                    System.out.println("Warna                       : " + rectangle.getColor());
                    System.out.println("Panjang & Lebar             : " + rectangle.getLength() + " & " + rectangle.getWidth());
                    System.out.println("Keliling Persegi Panjang    : " + rectangle.getPerimeter());
                    System.out.println("Luas Persegi Panjang        : " + rectangle.getArea());
                    System.out.println();
                    break;
                case 4:
                    System.out.println("---------------- Segitiga Siku-Siku ----------------");
                    // Input
                    System.out.print("Masukkan alas       : ");
                    int base = s.nextInt();
                    System.out.print("Masukkan tinggi     : ");
                    int height = s.nextInt();
                    // Print
                    Triangle triangle = new Triangle(base, height, "Hitam");
                    System.out.println("Warna               : " + triangle.getColor());
                    System.out.println("Alas & Tinggi       : " + triangle.getBase() + " & " + triangle.getHeight());
                    System.out.println("Keliling Segitiga   : " + triangle.getPerimeter());
                    System.out.println("Luas Segitiga       : " + triangle.getArea());
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    break;
                default:
                    System.out.println("Input tidak valid!");
                    System.out.println();
            }
        } while(menu != 5);
    }
}
