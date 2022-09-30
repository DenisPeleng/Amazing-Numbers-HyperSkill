package numbers;

import java.util.*;

public class Main {
    private static boolean isExit = false;

    public static void main(String[] args) {
        System.out.print("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - separate the parameters with one space;
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
        String[] strArrWithNumbers = scanner.nextLine().split(" ");
        System.out.println("");
        long firstNumber;
        long secondNumber;
        try {
            firstNumber = Long.parseLong(strArrWithNumbers[0]);
        } catch (NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        }
        if (isNatural(firstNumber)) {
            if (strArrWithNumbers.length == 1) {
                printPropertiesForOneLarge(firstNumber);
            } else {
                try {
                    secondNumber = Long.parseLong(strArrWithNumbers[1]);
                } catch (NumberFormatException e) {
                    System.out.println("The second parameter should be a natural number");
                    return;
                }
                if (isNatural(secondNumber)) {
                    printPropertiesForArray(firstNumber, secondNumber);
                } else {
                    System.out.println("The second parameter should be a natural number");
                }
            }
        } else if (firstNumber == 0) {
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

    private static void printPropertiesForOneLarge(long number) {
        System.out.printf("Properties of %d%n", number);
        System.out.println("        even: " + !isOddNumber(number));
        System.out.println("         odd: " + isOddNumber(number));
        System.out.println("        buzz: " + isBuzzNumber(number));
        System.out.println("        duck: " + isDuckNumber(number));
        System.out.println(" palindromic: " + isPalindromic(number));
        System.out.println("      gapful: " + isGapfulNumber(number));
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

    private static boolean isGapfulNumber(long number) {
        if (number / 100 > 0) {
            long firstDigit = number;
            long lastDigit = number % 10;
            while (firstDigit >= 10) {
                firstDigit = firstDigit / 10;
            }
            String remainderForGapful = firstDigit + String.valueOf(lastDigit);
            return number % (Long.parseLong(remainderForGapful)) == 0;
        }
        return false;
    }

    private static void printPropertiesForArray(long number, long count) {
        for (int i = 0; i < count; i++) {
            printPropertiesForOneShort(number);
            number++;
        }
    }

    private static void printPropertiesForOneShort(long number) {
        List<String> listOfNumberProperties = new ArrayList<>();
        listOfNumberProperties.add(isOddNumber(number) ? "odd" : "even");
        if (isBuzzNumber(number)) {
            listOfNumberProperties.add("buzz");
        }
        if (isDuckNumber(number)) {
            listOfNumberProperties.add("duck");
        }
        if (isPalindromic(number)) {
            listOfNumberProperties.add("palindromic");
        }
        if (isGapfulNumber(number)) {
            listOfNumberProperties.add("gapful");
        }
        Collections.sort(listOfNumberProperties);
        String propertiesString = String.join(", ", listOfNumberProperties);
        System.out.printf("        %s is %s%n", number, propertiesString);
    }
}
