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
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                                """);

        while (!isExit) {
            showMenu();
        }

    }

    public static void showMenu() {
        System.out.println();
        System.out.print("Enter a request: ");
        Scanner scanner = new Scanner(System.in);
        String[] strArrWithNumbers = scanner.nextLine().split(" ");
        System.out.println();
        switch (strArrWithNumbers.length) {
            case 1 -> printPropertiesOfNumber(strArrWithNumbers);
            case 2 -> printPropertiesOfRangeNumbers(strArrWithNumbers);
            case 3 -> printSearchPropertiesOfRange(strArrWithNumbers);
            default -> System.out.println("Wrong Amount of arguments");
        }

    }

    private static void printSearchPropertiesOfRange(String[] strArrWithNumbers) {
        long number = 0;
        long counter = 0;
        try {
            number = Long.parseLong(strArrWithNumbers[0]);
            if (!isNatural(number)) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
        }
        try {
            counter = Long.parseLong(strArrWithNumbers[1]);
            if (!isNatural(counter)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("The second parameter should be a natural number");
        }
        String property = strArrWithNumbers[2];

        searchAndPrintByProperty(number, counter, property);
    }

    private static void printPropertiesOfRangeNumbers(String[] strArrWithNumbers) {
        long firstNumber = 0;
        long secondNumber;
        try {
            firstNumber = Long.parseLong(strArrWithNumbers[0]);
            if (!isNatural(firstNumber)) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
        }
        try {
            secondNumber = Long.parseLong(strArrWithNumbers[1]);
            if (isNatural(secondNumber)) {
                printPropertiesForArray(firstNumber, secondNumber);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("The second parameter should be a natural number");
        }


    }

    private static void printPropertiesOfNumber(String[] strArrWithNumbers) {
        long firstNumber;
        try {
            firstNumber = Long.parseLong(strArrWithNumbers[0]);
            if (isNatural(firstNumber)) {

                printPropertiesForOneLarge(firstNumber);
            } else if (firstNumber == 0) {
                isExit = true;
                System.out.println("Goodbye!");
            } else {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
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
        System.out.println(" palindromic: " + isPalindromicNumber(number));
        System.out.println("      gapful: " + isGapfulNumber(number));
        System.out.println("         spy: " + isSpyNumber(number));
    }

    private static boolean isPalindromicNumber(long number) {
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

    private static boolean isSpyNumber(long number) {
        long multiplyDigits = 1;
        long sumDigits = 0;
        long currentPartNumber = number;
        while (currentPartNumber >= 10) {
            long digit = currentPartNumber % 10;
            multiplyDigits *= digit;
            sumDigits += digit;
            currentPartNumber = currentPartNumber / 10;
        }
        long digit = currentPartNumber % 10;
        multiplyDigits *= digit;
        sumDigits += digit;
        return sumDigits == multiplyDigits;
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
        if (isPalindromicNumber(number)) {
            listOfNumberProperties.add("palindromic");
        }
        if (isGapfulNumber(number)) {
            listOfNumberProperties.add("gapful");
        }
        if (isSpyNumber(number)) {
            listOfNumberProperties.add("spy");
        }
        Collections.sort(listOfNumberProperties);
        String propertiesString = String.join(", ", listOfNumberProperties);
        System.out.printf("        %s is %s%n", number, propertiesString);
    }

    private static void searchAndPrintByProperty(long number, long countNumbers, String property) {
        int currentAmount = 0;
        while (countNumbers > currentAmount) {
            switch (property.toUpperCase()) {
                case "BUZZ" -> {
                    if (isBuzzNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;
                    }
                }
                case "DUCK" -> {
                    if (isDuckNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;
                    }
                }
                case "PALINDROMIC" -> {
                    if (isPalindromicNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;

                    }
                }
                case "GAPFUL" -> {
                    if (isGapfulNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;
                    }
                }
                case "SPY" -> {
                    if (isSpyNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;
                    }
                }
                case "EVEN" -> {
                    if (!isOddNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;
                    }
                }
                case "ODD" -> {
                    if (isOddNumber(number)) {
                        printPropertiesForOneShort(number);
                        currentAmount++;
                    }
                }
                default -> {
                    System.out.printf("The property [%s] is wrong.%n", property);
                    System.out.printf("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]%n");
                    return;
                }
            }
            number++;
        }
    }
}
