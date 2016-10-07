package com.primenumbersapi.util;

import com.primenumbersapi.exception.InvalidDigitException;

public class NumberUtil {

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

            answer = answer % 10;

            number = answer + number;
        }

        if (carry == 1) {
            number = carry + number;
        }

        return number;
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
