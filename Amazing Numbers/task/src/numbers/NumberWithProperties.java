package numbers;

import java.util.ArrayList;
import java.util.List;

public class NumberWithProperties {
    long number;
    List<String> properties = new ArrayList<>();

    NumberWithProperties(long number) {
        this.number = number;
        if (isBuzzNumber(number)) {
            properties.add("buzz");
        }
        if (isDuckNumber(number)) {
            properties.add("duck");
        }
        if (isPalindromicNumber(number)) {
            properties.add("palindromic");
        }
        if (isGapfulNumber(number)) {
            properties.add("gapful");
        }
        if (isSpyNumber(number)) {
            properties.add("spy");
        }
        if (isSunnyNumber(number)) {
            properties.add("sunny");
        }
        if (isSquareNumber(number)) {
            properties.add("square");
        }
        if (isJumpingNumber(number)) {
            properties.add("jumping");
        }
        properties.add(isHappyNumber(number) ? "happy" : "sad");
        properties.add(isOddNumber(number) ? "odd" : "even");
    }

    private boolean isHappyNumber(long number) {
        long sumOfSquares = sumSquareOfDigits(number);
        long amountOfAttempts = 0;

        while (sumOfSquares != 1 && sumOfSquares != number) {
            sumOfSquares = sumSquareOfDigits(sumOfSquares);
            if (amountOfAttempts > 100) {
                break;
            }
            amountOfAttempts++;
        }
        return sumOfSquares == 1;
    }

    private long sumSquareOfDigits(long number) {
        long firstDigit = number;
        long lastDigit = number % 10;
        long sumOfSquares = (long) Math.pow(lastDigit, 2);
        while (firstDigit >= 10) {
            firstDigit /= 10;
            sumOfSquares += (long) Math.pow(firstDigit % 10, 2);

        }
        return sumOfSquares;
    }

    private static boolean isSunnyNumber(long number) {
        return isSquareNumber(number + 1);
    }

    private static boolean isSquareNumber(long number) {

        return Math.sqrt(number) % 1 == 0;
    }

    private static boolean isDuckNumber(long number) {
        while (number >= 10) {
            if (number % 10 == 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

    private static boolean isGapfulNumber(long number) {
        if (number / 100 > 0) {
            long firstDigit = number;
            long lastDigit = number % 10;
            while (firstDigit >= 10) {
                firstDigit /= 10;
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

    private static boolean isPalindromicNumber(long number) {
        char[] numberInCharArr = String.valueOf(number).toCharArray();
        for (int i = 0; i < numberInCharArr.length / 2; i++) {
            if (numberInCharArr[i] != numberInCharArr[numberInCharArr.length - i - 1]) {
                return false;
            }
        }
        return true;
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

    private static boolean isJumpingNumber(long number) {
        char[] numberCharArr = String.valueOf(number).toCharArray();
        if (numberCharArr.length < 2) {
            return true;
        } else if (numberCharArr.length < 3) {
            int sequence1 = Math.abs((int) numberCharArr[0] - (int) numberCharArr[1]);
            return sequence1 == 1;

        } else {
            for (int i = 1; i < numberCharArr.length - 1; i++) {
                int sequence1 = Math.abs((int) numberCharArr[i] - (int) numberCharArr[i - 1]);
                int sequence2 = Math.abs((int) numberCharArr[i] - (int) numberCharArr[i + 1]);

                if (sequence1 != 1 || sequence2 != 1) {
                    return false;
                }

            }
        }
        return true;
    }


}
