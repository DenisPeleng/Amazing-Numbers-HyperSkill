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
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
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
                default -> printSearchWithTwoPropertiesOfRange(strArrWithNumbers);
            }
        }

    }


    private static void printSearchWithTwoPropertiesOfRange(String[] strArrWithNumbers) {
        long number = Long.parseLong(strArrWithNumbers[0]);
        long countNumbers = Long.parseLong(strArrWithNumbers[1]);
        List<String> propertiesToSearch = new ArrayList<>();
        List<String> propertiesToSearchToExclude = new ArrayList<>();
        for (int i = 2; i < strArrWithNumbers.length; i++) {
            String currentArgument = strArrWithNumbers[i].toLowerCase();
            if (currentArgument.startsWith("-")) {
                propertiesToSearchToExclude.add(currentArgument.substring(1));
            } else {
                propertiesToSearch.add(currentArgument);
            }
        }

        int currentAmount = 0;
        while (countNumbers > currentAmount) {
            boolean isNotFinded = false;
            NumberWithProperties currentNumberWithProperties = new NumberWithProperties(number);
            for (String currentProperty : propertiesToSearch
            ) {
                if (!currentNumberWithProperties.properties.contains(currentProperty)) {
                    isNotFinded = true;
                    break;
                } else {
                    for (String currentPropertyToExclude : propertiesToSearchToExclude
                    ) {
                        if (currentNumberWithProperties.properties.contains(currentPropertyToExclude)) {
                            isNotFinded = true;
                            break;
                        }
                    }
                }
            }
            if (propertiesToSearch.size() == 0) {
                for (String currentPropertyToExclude : propertiesToSearchToExclude
                ) {
                    if (currentNumberWithProperties.properties.contains(currentPropertyToExclude)) {
                        isNotFinded = true;
                        break;
                    }
                }
            }
            if (!isNotFinded) {
                printPropertiesForOneShort(currentNumberWithProperties);
                currentAmount++;
            }

            number++;
        }
    }

    private static boolean isCorrectArguments(String[] strArrWithNumbers) {
        List<String> propertiesNames = Arrays.asList("BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "EVEN", "ODD", "JUMPING", "HAPPY", "SAD");
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
                List<String> wrongArguments = new ArrayList<>();
                for (int i = 2; i < strArrWithNumbers.length; i++) {
                    String currentArgToCheck = strArrWithNumbers[i].toUpperCase();
                    if (currentArgToCheck.startsWith("-")) {
                        if (!propertiesNames.contains(currentArgToCheck.substring(1))) {
                            wrongArguments.add(currentArgToCheck);
                        }
                    } else {
                        if (!propertiesNames.contains(currentArgToCheck)) {
                            wrongArguments.add(currentArgToCheck);
                        }
                    }
                }
                switch (wrongArguments.size()) {
                    case 0 -> {
                        return !isMutuallyExclusiveNumbers(strArrWithNumbers);
                    }
                    case 1 -> {
                        System.out.printf("The property [%s] is wrong.%n", wrongArguments.get(0));
                        printAvaliableProperties(propertiesNames);
                        return false;
                    }
                    default -> {
                        System.out.printf("The properties [%s", wrongArguments.get(0));

                        for (int i = 1; i < wrongArguments.size(); i++) {
                            System.out.printf(", %s", wrongArguments.get(i));
                        }
                        System.out.printf("] are wrong.%n");
                        printAvaliableProperties(propertiesNames);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void printAvaliableProperties(List<String> avaliableProperties) {
        String strAvaliableProperties = String.join(", ", avaliableProperties);
        System.out.printf("Available properties: [%s]%n", strAvaliableProperties);

    }

    private static boolean isMutuallyExclusiveNumbers(String[] strArrWithNumbers) {
        List<String> propertiesToSearch = new ArrayList<>();
        for (int i = 2; i < strArrWithNumbers.length; i++) {
            propertiesToSearch.add(strArrWithNumbers[i].toLowerCase());
        }
        List<String> wrongPropertiesToSearch = new ArrayList<>();
        for (String currProperty : propertiesToSearch
        ) {
            if (propertiesToSearch.contains("-" + currProperty)) {
                wrongPropertiesToSearch.add(currProperty);
                wrongPropertiesToSearch.add("-" + currProperty);
            }


        }

        if (propertiesToSearch.contains("sunny") && propertiesToSearch.contains("square")) {
            wrongPropertiesToSearch.add("sunny");
            wrongPropertiesToSearch.add("square");
        }
        if (propertiesToSearch.contains("even") && propertiesToSearch.contains("odd")) {
            wrongPropertiesToSearch.add("even");
            wrongPropertiesToSearch.add("odd");
        }
        if (propertiesToSearch.contains("-even") && propertiesToSearch.contains("-odd")) {
            wrongPropertiesToSearch.add("-even");
            wrongPropertiesToSearch.add("-odd");
        }
        if (propertiesToSearch.contains("happy") && propertiesToSearch.contains("sad")) {
            wrongPropertiesToSearch.add("happy");
            wrongPropertiesToSearch.add("sad");
        }
        if (propertiesToSearch.contains("-happy") && propertiesToSearch.contains("-sad")) {
            wrongPropertiesToSearch.add("-happy");
            wrongPropertiesToSearch.add("-sad");
        }
        if (propertiesToSearch.contains("spy") && propertiesToSearch.contains("duck")) {
            wrongPropertiesToSearch.add("spy");
            wrongPropertiesToSearch.add("duck");
        }
        if (wrongPropertiesToSearch.size() > 0) {
            System.out.println("The request contains mutually exclusive properties: ");
            System.out.printf("[%s", wrongPropertiesToSearch.get(0).toUpperCase());
            for (int i = 1; i < wrongPropertiesToSearch.size(); i++) {

                System.out.printf(", %s", wrongPropertiesToSearch.get(i).toUpperCase());
            }
            System.out.printf("]%n");
            System.out.println("There are no numbers with these properties.");
            return true;
        }

        return false;
    }

    private static void printSearchPropertiesOfRange(String[] strArrWithNumbers) {
        long number = Long.parseLong(strArrWithNumbers[0]);
        long counter = Long.parseLong(strArrWithNumbers[1]);
        String property = strArrWithNumbers[2];
        int currentAmount = 0;
        while (counter > currentAmount) {
            NumberWithProperties currentNumberWithProperties = new NumberWithProperties(number);
            if (property.contains("-")) {
                if (!currentNumberWithProperties.properties.contains(property.toLowerCase().substring(1))) {
                    printPropertiesForOneShort(currentNumberWithProperties);
                    currentAmount++;
                }
            } else {
                if (currentNumberWithProperties.properties.contains(property.toLowerCase())) {
                    printPropertiesForOneShort(currentNumberWithProperties);
                    currentAmount++;
                }
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
        System.out.println("     jumping: " + currentNumber.properties.contains("jumping"));
        System.out.println("       happy: " + currentNumber.properties.contains("happy"));
        System.out.println("         sad: " + currentNumber.properties.contains("sad"));
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
