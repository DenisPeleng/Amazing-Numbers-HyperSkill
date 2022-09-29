package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = scanner.nextInt();
        printProperties(number);
    }

    public static boolean isDivisableBySeven(int number) {
        int smallestDivider = number;
        while (smallestDivider > 10) {
            int lastDigit = smallestDivider % 10;
            int firstPart = smallestDivider / 10;
            smallestDivider = Math.abs(firstPart - lastDigit * 2);
        }
        return smallestDivider % 7 == 0;
    }

    public static boolean isEndedBySeven(int number) {
        return number % 10 == 7;
    }

    public static boolean isOddNumber(int number) {
        return number % 2 != 0;
    }

    public static boolean isBuzzNumber(int number) {
        return isDivisableBySeven(number) || isEndedBySeven(number);
    }

    public static void printResultsWithExplanation(int number) {
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

    public static void printProperties(int number) {
        if (number <= 0) {
            System.out.println("This number is not natural!");
            return;
        }
        System.out.println("Properties of " + number);
        System.out.println("        even: " + !isOddNumber(number));
        System.out.println("         odd: " + isOddNumber(number));
        System.out.println("        buzz: " + isBuzzNumber(number));
        System.out.println("        duck: " + isDuckNumber(number));
    }

    private static boolean isDuckNumber(int number) {
        while (number >= 10) {
            if (number % 10 == 0) {
                return true;
            }
            number = number / 10;
        }
        return false;
    }
}
