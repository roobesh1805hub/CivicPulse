package com.smartcity.utils;

import java.util.Scanner;

public class InputUtil {

    private static Scanner sc = new Scanner(System.in);


    // =====================================================
    // READ INTEGER
    // =====================================================

    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(
                        sc.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }


    // =====================================================
    // READ STRING
    // =====================================================

    public static String readString(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    sc.nextLine().trim();

            if (!input.isEmpty()) {

                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }


    // =====================================================
    // READ EMAIL
    // =====================================================

    public static String readEmail(String message) {

        while (true) {

            System.out.print(message);

            String email =
                    sc.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            )) {

                return email;
            }

            System.out.println(
                    "Invalid email format. Please try again."
            );
        }
    }


    // =====================================================
    // READ PHONE NUMBER
    // =====================================================

    public static String readPhone(String message) {

        while (true) {

            System.out.print(message);

            String phone =
                    sc.nextLine().trim();

            if (phone.matches("\\d{10}")) {

                return phone;
            }

            System.out.println(
                    "Invalid phone number. Enter exactly 10 digits."
            );
        }
    }
}