package com.primenumbersapi.util;

import com.primenumbersapi.exception.InvalidDigitException;

public class NumberUtil {

    /**
     * A method that adds two strings together, inspired by column method. Supports negatives (add a - (hyphen) sign to
     * the addend that you wish to make negative).
     * @param addend1 The first string to add. Must be an integer.
     * @param addend2 The second string to add. Must be an integer.
     * @return The result of the sum, as a string.
     * @throws InvalidDigitException If any of the arguments supplied are not integers.
     */
    public static String add(String addend1, String addend2) throws InvalidDigitException {
        isInteger(addend1);
        isInteger(addend2);

        String number = "";

        boolean isAddend1Negative = false;
        boolean isAddend2Negative = false;

        if ("-".equals(String.valueOf(addend1.charAt(0)))) {
            isAddend1Negative = true;
        }

        if ("-".equals(String.valueOf(addend2.charAt(0)))) {
            isAddend2Negative = true;
        }

        if (!isAddend1Negative && isAddend2Negative) {
            addend2 = addend2.substring(1);
            return subtract(addend1, addend2);
        }

        if (isAddend1Negative && !isAddend2Negative) {
            addend1 = addend1.substring(1);
            return subtract(addend2, addend1);
        }

        if (isAddend1Negative) {
            addend1 = addend1.substring(1);
            addend2 = addend2.substring(1);
        }

        if (addend2.length() > addend1.length()) {
            while (addend2.length() > addend1.length()) {
                addend1 = "0" + addend1;
            }
        } else {
            while (addend1.length() > addend2.length()) {
                addend2 = "0" + addend2;
            }
        }

        int length = addend1.length();
        int carry = 0;

        for (int i = 1; i <= length; i++) {
            int digit1 = Character.getNumericValue(addend1.charAt(addend1.length() - i));
            int digit2 = Character.getNumericValue(addend2.charAt(addend2.length() - i));

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

        if (isAddend1Negative) {
            number = "-" + number;
        }

        return number;
    }

    /**
     * A method for subtracting two strings based on the two's complement system that computers use for subtraction.
     * This method follows the non-commutative property of subtraction, so the position of the arguments supplied
     * matters! Supports negatives (add a - (hyphen) sign to the number that you wish to make negative).
     * @param minuere The number to subtract from. Must be an integer.
     * @param subtrahend The amount to subtract. Must be an integer.
     * @return The answer of the subtraction, as a string.
     * @throws InvalidDigitException If non integer strings are supplied as arguments.
     */
    public static String subtract(String minuere, String subtrahend) throws InvalidDigitException {
        isInteger(minuere);
        isInteger(subtrahend);

        boolean isLess = isLess(minuere, subtrahend);

        boolean isMinuereNegative = false;
        boolean isSubtrahendNegative = false;

        if ("-".equals(String.valueOf(minuere.charAt(0)))) {
            isMinuereNegative = true;
        }

        if ("-".equals(String.valueOf(subtrahend.charAt(0)))) {
            isSubtrahendNegative = true;
        }

        if (!isMinuereNegative && isSubtrahendNegative) {
            subtrahend = subtrahend.substring(1);
            return add(minuere, subtrahend);
        }

        if (isMinuereNegative && !isSubtrahendNegative) {
            return "-" + add(minuere.substring(1), subtrahend);
        }

        if (isMinuereNegative) {
            return add(subtrahend.substring(1), minuere);
        }

        // If the minuere is less than the subtrahend then our answer will be negative.
        if (isLess) {
            // Swap the minuere and the subtrahend and carry out the absolute value of the subtraction.
            String tempMinuere = minuere;

            minuere = subtrahend;
            subtrahend = tempMinuere;
        }

        String answer;
        String complement = "";
        boolean noMoreZeroes = false;
        boolean doneFirstDigit = false;
        int numberOfZeroes = 0;

        for (int i = 1; i <= subtrahend.length(); i++) {
            int digit = Character.getNumericValue(subtrahend.charAt(subtrahend.length() - i));

            if (digit == 0 && !noMoreZeroes) {
                numberOfZeroes++;
                // If the digit we are subtracting is 0 and we haven't encountered a non zero integer yet, then we don't
                // process the digit.
                continue;
            }

            // Set to true when we encounter our first non zero integer.
            noMoreZeroes = true;

            // We may not have subtracted from any digits yet if the subtrahend ends in zeroes.
            if (!doneFirstDigit) {
                digit = 10 - digit;
                doneFirstDigit = true;
            } else {
                digit = 9 - digit;
            }

            complement = digit + complement;
        }

        for (int i = 0; i < numberOfZeroes; i++) {
            // Pad the complement with zeroes if the subtrahend ends in zeroes.
            complement = complement + "0";
        }

        if (complement.length() < minuere.length()) {
            while (complement.length() < minuere.length()) {
                // Pad the complement with nines at the beginning if it is shorter than the minuere.
                complement = "9" + complement;
            }
        }

        answer = add(minuere, complement);

        // Remove the leading character from the final answer.
        answer = answer.substring(1);

        if (isLess) {
            // Add a negative sign to the final answer if the minuere was less than the subtrahend.
            answer = "-" + answer;
        }

        return answer;
    }

    /**
     * A "place holder" method that does multiplication using repeated addition. This method is very inefficient when
     * handling large numbers, so it will be replaced by a more efficient multiplication method in the future.
     * @param multiplicand The first number to multiply. Must be an integer.
     * @param multiplier The second number to multiply. Must be an integer.
     * @return The answer of the multiplication as a string.
     * @throws InvalidDigitException If non integer strings are supplied as arguments.
     */
    public static String multiply(String multiplicand, String multiplier) throws InvalidDigitException {
        isInteger(multiplicand);
        isInteger(multiplier);

        if (multiplier.equals("0")) {
            return "0";
        } else if (multiplier.equals("1")) {
            return multiplicand;
        }

        if (isGreater(multiplier, multiplicand)) {
            String tempMultiplier = multiplier;

            multiplier = multiplicand;
            multiplicand = tempMultiplier;
        }

        String product = "";
        while (isGreater(multiplier, "1")) {
            if (product.length() == 0) {
                product = add(multiplicand, multiplicand);
            } else {
                product = add(product, multiplicand);
            }

            multiplier = subtract(multiplier, "1");
        }

        return product;
    }

    /**
     * Compare two strings and determine if one is greater than the other. Supports negatives (add a - (hyphen) sign to
     * the number that you wish to make negative).
     * @param number1 The first number to compare. Must be an integer.
     * @param number2 The second number to compare. Must be an integer.
     * @return True or false, depending on the result of the comparison.
     * @throws InvalidDigitException If non integer strings are supplied as arguments.
     */
    public static boolean isGreater(String number1, String number2) throws InvalidDigitException {
        isInteger(number1);
        isInteger(number2);

        boolean isNumber1Negative = false;
        boolean isNumber2Negative = false;

        if ("-".equals(String.valueOf(number1.charAt(0)))) {
            isNumber1Negative = true;
        }

        if ("-".equals(String.valueOf(number2.charAt(0)))) {
            isNumber2Negative = true;
        }

        // Remove the negative signs and process them normally.
        if (isNumber1Negative) {
            number1 = number1.substring(1);
        }

        if (isNumber2Negative) {
            number2 = number2.substring(1);
        }

        if (Character.getNumericValue(number1.charAt(0)) == 0 && number1.length() != 1) {
            while (Character.getNumericValue(number1.charAt(0)) == 0) {
                number1 = number1.substring(1);
            }
        }

        if (Character.getNumericValue(number2.charAt(0)) == 0 && number2.length() != 1) {
            while (Character.getNumericValue(number2.charAt(0)) == 0) {
                number2 = number2.substring(1);
            }
        }

        if (number1.length() > number2.length()) {
            return true;
        } else if (number1.length() < number2.length()) {
            return false;
        } else {
            for (int i = 0; i < number1.length(); i++) {
                int digit1 = Character.getNumericValue(number1.charAt(i));
                int digit2 = Character.getNumericValue(number2.charAt(i));

                // I have purposely chosen not to simplify the following if statements to preserve readability!
                if (digit1 > digit2) {
                    if (isNumber1Negative && !isNumber2Negative) {
                        return false;
                    } else if (!isNumber1Negative && isNumber2Negative) {
                        return true;
                    } else return !isNumber1Negative;
                } else if (digit1 < digit2) {
                    if (isNumber1Negative && !isNumber2Negative) {
                        return false;
                    } else if (!isNumber1Negative && isNumber2Negative) {
                        return true;
                    } else return isNumber1Negative;
                }
            }
        }

        return false;
    }

    /**
     * Compare two strings and determine whether one is less than the other. Supports negatives (add a - (hyphen) sign
     * to the number that you wish to make negative).
     * @param number1 The first number to compare. Must be an integer.
     * @param number2 The second number to compare. Must be an integer.
     * @return True or false, depending on the result of the comparison.
     * @throws InvalidDigitException If non integer strings are supplied as arguments.
     */
    public static boolean isLess(String number1, String number2) throws InvalidDigitException {
        return !isGreater(number1, number2) && !isEqual(number1, number2);
    }

    /**
     * Compare two strings and determine whether one is equal to another. Supports negatives (add a - (hyphen) sign to
     * the number that you wish to make negative).
     * @param number1 The first number to compare. Must be an integer.
     * @param number2 The second number to compare. Must be an integer.
     * @return True or false, depending on the result of the comparison.
     * @throws InvalidDigitException If non integer strings are supplied as arguments.
     */
    public static boolean isEqual(String number1, String number2) throws InvalidDigitException {
        isInteger(number1);
        isInteger(number2);

        if ("-".equals(String.valueOf(number1.charAt(0))) && "-".equals(String.valueOf(number2.charAt(0)))) {
            number1 = number1.substring(1);
            number2 = number2.substring(1);
        }

        if (Character.getNumericValue(number1.charAt(0)) == 0 && number1.length() != 1) {
            while (Character.getNumericValue(number1.charAt(0)) == 0) {
                number1 = number1.substring(1);
            }
        }

        if (Character.getNumericValue(number2.charAt(0)) == 0 && number2.length() != 1) {
            while (Character.getNumericValue(number2.charAt(0)) == 0) {
                number2 = number2.substring(1);
            }
        }

        if (number1.length() != number2.length()) {
            return false;
        } else {
            for (int i = 0; i < number1.length(); i++) {
                int digit1 = Character.getNumericValue(number1.charAt(i));
                int digit2 = Character.getNumericValue(number2.charAt(i));

                if (digit1 != digit2) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void isInteger(String number) throws InvalidDigitException {
        for (char c : number.toCharArray()) {
            if (!isDigit(c)) {
                throw new InvalidDigitException();
            }
        }
    }

    private static boolean isDigit(char c) {
        return ((c >= '0' && c <= '9') || ("-").equals(String.valueOf(c)));
    }

}
