package numbers;

import java.util.*;

public class Main {
    private static boolean isExit = false;

    public static void main(String[] args) {
        showGreeting();
        while (!isExit) {
            showMenu();
        }

    }

    private static void showGreeting() {
        System.out.println(" Welcome to Amazing Numbers!");
        System.out.print("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                                """);
    }

    public static void showMenu() {
        System.out.println();
        System.out.print("Enter a request: ");
        Scanner scanner = new Scanner(System.in);
        String[] strArrWithNumbers = scanner.nextLine().split(" ");
        System.out.println();
        if (isCorrectArguments(strArrWithNumbers)) {
            switch (strArrWithNumbers.length) {
                case 1 -> printPropertiesForOneLarge(strArrWithNumbers);
                case 2 -> printPropertiesOfRangeNumbers(strArrWithNumbers);
                case 3 -> printSearchPropertiesOfRange(strArrWithNumbers);
                case 4 -> printSearchWithTwoPropertiesOfRange(strArrWithNumbers);
                default -> System.out.println("Wrong Amount of arguments");
            }
        }

    }


    private static void printSearchWithTwoPropertiesOfRange(String[] strArrWithNumbers) {
        long number = Long.parseLong(strArrWithNumbers[0]);
        long countNumbers = Long.parseLong(strArrWithNumbers[1]);
        String property1 = strArrWithNumbers[2].toLowerCase();
        String property2 = strArrWithNumbers[3].toLowerCase();
        String strArgs = String.join(" ", strArrWithNumbers).toLowerCase();
        if ((strArgs.contains("even") && strArgs.contains("odd")
                || strArgs.contains("sunny") && strArgs.contains("square"))
                || (strArgs.contains("spy") && strArgs.contains("duck"))) {
            System.out.println("The request contains mutually exclusive properties: ");
            System.out.printf("[%s, %s]%n", property1.toUpperCase(), property2.toUpperCase());
            System.out.println("There are no numbers with these properties.");
            return;
        }
        int currentAmount = 0;
        while (countNumbers > currentAmount) {
            NumberWithProperties currentNumberWithProperties = new NumberWithProperties(number);
            if (currentNumberWithProperties.properties.contains(property1) && currentNumberWithProperties.properties.contains(property2)) {
                printPropertiesForOneShort(currentNumberWithProperties);
                currentAmount++;
            }
            number++;
        }
    }

    private static boolean isCorrectArguments(String[] strArrWithNumbers) {
        List<String> propertiesNames = Arrays.asList("BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "EVEN", "ODD");
        long firstNumber;
        long secondNumber;
        try {
            firstNumber = Long.parseLong(strArrWithNumbers[0]);
            if (isNotNaturalNumber(firstNumber) && firstNumber != 0) {
                throw new NumberFormatException();
            } else if (firstNumber == 0) {
                isExit = true;
                System.out.println("Goodbye!");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
        if (strArrWithNumbers.length > 1) {
            try {
                secondNumber = Long.parseLong(strArrWithNumbers[1]);
                if (isNotNaturalNumber(secondNumber)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("The second parameter should be a natural number");
                return false;
            }
            if (strArrWithNumbers.length > 2) {
                int indexOfWrongArgument = -1;
                for (int i = 2; i < strArrWithNumbers.length; i++) {
                    if (!propertiesNames.contains(strArrWithNumbers[i].toUpperCase())) {
                        indexOfWrongArgument = indexOfWrongArgument == -1 ? i : -2;
                    }
                }

                if (indexOfWrongArgument == -2) {
                    System.out.printf("The properties [%s, %s] are wrong.%n", strArrWithNumbers[2].toUpperCase(), strArrWithNumbers[3].toUpperCase());
                } else if (indexOfWrongArgument == -1) {
                    return true;
                } else {
                    System.out.printf("The property [%s] is wrong.%n", strArrWithNumbers[indexOfWrongArgument].toUpperCase());
                }
                System.out.printf("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]%n");
                return false;
            }
        }
        return true;
    }

    private static void printSearchPropertiesOfRange(String[] strArrWithNumbers) {
        long number = Long.parseLong(strArrWithNumbers[0]);
        long counter = Long.parseLong(strArrWithNumbers[1]);
        String property = strArrWithNumbers[2];
        int currentAmount = 0;
        while (counter > currentAmount) {
            NumberWithProperties currentNumberWithProperties = new NumberWithProperties(number);
            if (currentNumberWithProperties.properties.contains(property.toLowerCase())) {
                printPropertiesForOneShort(currentNumberWithProperties);
                currentAmount++;
            }
            number++;
        }
    }

    private static void printPropertiesOfRangeNumbers(String[] strArrWithNumbers) {
        long number = Long.parseLong(strArrWithNumbers[0]);
        long countNumbers = Long.parseLong(strArrWithNumbers[1]);
        for (int i = 0; i < countNumbers; i++) {
            NumberWithProperties currentNumberWithProperties = new NumberWithProperties(number);
            printPropertiesForOneShort(currentNumberWithProperties);
            number++;
        }

    }

    private static void printPropertiesForOneLarge(String[] strArrWithNumbers) {
        long number = Long.parseLong(strArrWithNumbers[0]);
        NumberWithProperties currentNumber = new NumberWithProperties(number);
        System.out.printf("Properties of %d%n", number);
        System.out.println("        even: " + currentNumber.properties.contains("even"));
        System.out.println("         odd: " + currentNumber.properties.contains("odd"));
        System.out.println("        buzz: " + currentNumber.properties.contains("buzz"));
        System.out.println("        duck: " + currentNumber.properties.contains("duck"));
        System.out.println(" palindromic: " + currentNumber.properties.contains("palindromic"));
        System.out.println("      gapful: " + currentNumber.properties.contains("gapful"));
        System.out.println("         spy: " + currentNumber.properties.contains("spy"));
        System.out.println("       sunny: " + currentNumber.properties.contains("sunny"));
        System.out.println("      square: " + currentNumber.properties.contains("square"));
    }

    private static boolean isNotNaturalNumber(long number) {
        return number <= 0;
    }

    private static void printPropertiesForOneShort(NumberWithProperties currentNumber) {
        List<String> listOfNumberProperties = currentNumber.properties;
        Collections.sort(listOfNumberProperties);
        String propertiesString = String.join(", ", listOfNumberProperties);
        System.out.printf("        %s is %s%n", currentNumber.number, propertiesString);
    }

}
