package numbers;

import java.util.Scanner;

public class Main {
    private static boolean isExit = false;

    public static void main(String[] args) {
        System.out.print("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                """);

        while (!isExit) {
            showMenu();
        }

    }

    public static void showMenu() {
        System.out.println("");
        System.out.print("Enter a request: ");
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        System.out.println("");
        if (isNatural(number)) {
            printProperties(number);
        } else if (number == 0) {
            isExit = true;
            System.out.println("Goodbye!");
        } else {
            System.out.println("The first parameter should be a natural number or zero.");
        }

    }

    private static boolean isDivisableBySeven(long number) {
        long smallestDivider = number;
        while (smallestDivider > 10) {
            long lastDigit = smallestDivider % 10;
            long firstPart = smallestDivider / 10;
            smallestDivider = Math.abs(firstPart - lastDigit * 2);
        }
        return smallestDivider % 7 == 0;
    }

    private static boolean isEndedBySeven(long number) {
        return number % 10 == 7;
    }

    private static boolean isOddNumber(long number) {
        return number % 2 != 0;
    }

    private static boolean isBuzzNumber(long number) {
        return isDivisableBySeven(number) || isEndedBySeven(number);
    }

    private static void printResultsWithExplanation(long number) {
        boolean isDivisableBySeven = isDivisableBySeven(number);
        boolean isEndedBySeven = isEndedBySeven(number);
        boolean isOddNumber = isOddNumber(number);
        if (number <= 0) {
            System.out.println("This number is not natural!");
            return;
        }
        if (isOddNumber) {
            System.out.println("This number is Odd.");
        } else {
            System.out.println("This number is Even.");
        }
        if (isEndedBySeven || isDivisableBySeven) {
            System.out.println("It is a Buzz number.");
        } else {
            System.out.println("It is not a Buzz number.");
        }
        System.out.println("Explanation:");
        if (isEndedBySeven && isDivisableBySeven) {
            System.out.println(number + " is divisible by 7 and ends with 7");
        } else if (isEndedBySeven) {
            System.out.println(number + " ends with 7");
        } else if (isDivisableBySeven) {
            System.out.println(number + " is divisible by 7");
        } else {
            System.out.println(number + " is neither divisible by 7 nor does it end with 7");
        }
    }

    private static void printProperties(long number) {
        System.out.printf("Properties of %d%n", number);
        System.out.println("        even: " + !isOddNumber(number));
        System.out.println("         odd: " + isOddNumber(number));
        System.out.println("        buzz: " + isBuzzNumber(number));
        System.out.println("        duck: " + isDuckNumber(number));
        System.out.println(" palindromic: " + isPalindromic(number));
    }

    private static boolean isPalindromic(long number) {
        char[] numberInCharArr = String.valueOf(number).toCharArray();
        for (int i = 0; i < numberInCharArr.length / 2; i++) {
            if (numberInCharArr[i] != numberInCharArr[numberInCharArr.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNatural(long number) {
        return number > 0;
    }

    private static boolean isDuckNumber(long number) {
        while (number >= 10) {
            if (number % 10 == 0) {
                return true;
            }
            number = number / 10;
        }
        return false;
    }
}
