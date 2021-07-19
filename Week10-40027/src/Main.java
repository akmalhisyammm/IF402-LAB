import exceptions.AuthenticationException;
import exceptions.ExcessiveFailedLoginException;
import exceptions.InvalidPropertyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<User> listOfUsers = new ArrayList<>();

    public static void initialize() {
        listOfUsers.add(new User("John", "Doe", 'L', "Jl. Merpati No. 1 RT 1 RW 1, Banten", "admin", "admin"));
    }

    public static void handleLogin(String userName, String password) throws AuthenticationException {
        boolean flag = false;

        for(int i = 0; i < listOfUsers.size(); i++) {
            try {
                if(listOfUsers.get(i).login(userName, password)) {
                    System.out.println(listOfUsers.get(i).greeting());
                    flag = true;
                }
            } catch(ExcessiveFailedLoginException e) {
                System.out.println(e.getMessage());
                flag = true;
            }
        }

        if(!flag) {
            throw new AuthenticationException("Username / password salah");
        }
    }

    public static void handleSignUp(String userName, String password) throws InvalidPropertyException {
        boolean flagCase = false, flagDigit = false;

        if(userName.length() <= 8) {
            throw new InvalidPropertyException("Username harus lebih dari 8 karakter");
        }

        if(password.length() == 0) {
            return;
        } else if(password.length() >= 6 && password.length() <= 16) {
            for(int i = 0; i < password.length(); i++) {
                if(Character.isUpperCase(password.charAt(i))) {
                    flagCase = true;
                }
                if(Character.isDigit(password.charAt(i))) {
                    flagDigit = true;
                }
            }
            if(!flagCase || !flagDigit) {
                throw new InvalidPropertyException("Password harus mengandung huruf besar dan angka");
            }
        } else {
            throw new InvalidPropertyException("Password harus mengandung huruf besar, angka, minimum 6 karakter, dan maksimum 16 karakter");
        }
    }

    public static void main(String[] args) {
        initialize();

        Scanner s = new Scanner(System.in);
        String userName, password, firstName, lastName, address;
        Character gender;

        for(;;) {
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan: ");
            int menu = s.nextInt();
            s.nextLine();

            switch(menu) {
                case 1:
                    System.out.print("Username: ");
                    userName = s.nextLine();
                    System.out.print("Password: ");
                    password = s.nextLine();
                    try {
                        handleLogin(userName, password);
                    } catch(AuthenticationException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    break;
                case 2:
                    boolean checkRegister = false;

                    while(!checkRegister) {
                        System.out.print("Nama Depan            : ");
                        firstName = s.nextLine();
                        System.out.print("Nama Belakang         : ");
                        lastName = s.nextLine();
                        System.out.print("Jenis Kelamin (L/P)   : ");
                        gender = s.next().charAt(0);
                        s.nextLine();
                        System.out.print("Alamat                : ");
                        address = s.nextLine();
                        System.out.print("Username              : ");
                        userName = s.nextLine();
                        try {
                            handleSignUp(userName, "");
                            checkRegister = true;
                        } catch(InvalidPropertyException e) {
                            System.out.println(e.getMessage());
                        }
                        if(checkRegister) {
                            System.out.print("Password              : ");
                            password = s.nextLine();
                            try {
                                handleSignUp(userName, password);
                                listOfUsers.add(new User(firstName, lastName, gender, address, userName, password));
                            } catch(InvalidPropertyException e) {
                                System.out.println(e.getMessage());
                                checkRegister = false;
                            }
                        }
                    }
                    System.out.println("User telah berhasil didaftarkan");
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println();
            }
        }
    }
}
