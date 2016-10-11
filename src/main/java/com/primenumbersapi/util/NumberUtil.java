package com.primenumbersapi.util;

import com.primenumbersapi.exception.InvalidDigitException;

public class NumberUtil {

    /**
     * A method that adds two strings together, inspired by column method.
     * @param number1 The first string to add. Must be an integer.
     * @param number2 The second string to add. Must be an integer.
     * @return The result of the sum, as a string.
     * @throws InvalidDigitException If any of the arguments supplied are not integers.
     */
    public static String add(String number1, String number2) throws InvalidDigitException {
        isInteger(number1);
        isInteger(number2);

        String number = "";

        if (number2.length() > number1.length()) {
            while (number2.length() > number1.length()) {
                number1 = "0" + number1;
            }
        } else {
            while (number1.length() > number2.length()) {
                number2 = "0" + number2;
            }
        }

        int length = number1.length();
        int carry = 0;

        for (int i = 1; i <= length; i++) {
            int digit1 = Character.getNumericValue(number1.charAt(number1.length() - i));
            int digit2 = Character.getNumericValue(number2.charAt(number2.length() - i));

            int answer = digit1 + digit2 + carry;
            if (answer > 9) {
                carry = 1;
            } else {
                carry = 0;
            }

            // Get the last digit of answer and set it to answer.
            answer = answer % 10;

            number = answer + number;
        }

        if (carry == 1) {
            number = carry + number;
        }

        return number;
    }

    /**
     * A method for subtracting two strings based on the two's complement system that computers use for subtraction.
     * This method follows the non-commutative property of subtraction, so the position of the arguments supplied
     * matters! Currently, this method cannot deal with negative answers, so if it is told to subtract a smaller number
     * from a larger one then it will return an empty string.
     * @param number1 The number to subtract from. Must be an integer.
     * @param number2 The amount to subtract. Cannot be greater than number1. Must be an integer.
     * @return The answer of the subtraction, as a string.
     * @throws InvalidDigitException If non integer strings are supplied as arguments.
     */
    public static String subtract(String number1, String number2) throws InvalidDigitException {
        isInteger(number1);
        isInteger(number2);

        String answer;
        String complement = "";
        boolean noMoreZeroes = false;
        boolean doneFirstDigit = false;
        int numberOfZeroes = 0;

        for (int i = 1; i <= number2.length(); i++) {
            int digit = Character.getNumericValue(number2.charAt(number2.length() - i));

            if (digit == 0 && !noMoreZeroes) {
                numberOfZeroes++;
                // If the digit we are subtracting is 0 and the previous digit was 0 and we haven't encountered a non
                // zero integer yet, then we don't process the digit.
                continue;
            }

            // Set to true when we encounter our first non zero integer.
            noMoreZeroes = true;

            // We may not have subtracted from any digits yet if number2 ends in zeroes.
            if (!doneFirstDigit) {
                digit = 10 - digit;
                doneFirstDigit = true;
            } else {
                digit = 9 - digit;
            }

            complement = digit + complement;
        }

        for (int i = 0; i < numberOfZeroes; i++) {
            // Pad the complement with zeroes if number2 ends in zeroes.
            complement = complement + "0";
        }

        if (complement.length() < number1.length()) {
            while (complement.length() < number1.length()) {
                // Pad the complement with nines at the beginning if it is shorter than number1.
                complement = "9" + complement;
            }
        }

        answer = add(number1, complement);

        // Remove the leading character from the final answer.
        answer = answer.substring(1);

        return answer;
    }

    private static void isInteger(String number) throws InvalidDigitException {
        for (char c : number.toCharArray()) {
            if (!isDigit(c)) {
                throw new InvalidDigitException();
            }
        }
    }

    private static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

}
