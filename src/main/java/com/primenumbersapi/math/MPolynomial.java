package com.primenumbersapi.math;

import com.primenumbersapi.api.Number;
import com.primenumbersapi.api.Polynomial;
import com.primenumbersapi.exception.InvalidNumberException;

public class MPolynomial implements Polynomial {

    private String rawEquation;
    private Number power;

    private static final char[] operations = new char[] {'+', '-', '/', '*', '^', '(', ')'};

    protected MPolynomial(String rawEquation, String variable) throws InvalidNumberException {
        if (!isValid(rawEquation, variable)) throw new InvalidNumberException();

        if (this.rawEquation.contains("^")) {
            //TODO:
        }
    }

    protected MPolynomial(String rawEquation) throws InvalidNumberException {
        this(rawEquation, "x");
    }

    private boolean isValid(String rawEquation, String variable) {
        if (rawEquation != null && rawEquation.contains(variable)) {

            // Equations must be in x!
            this.rawEquation = rawEquation.replace(variable, "x");

            // Is there only one variable? Are all characters valid?
            for (char c : this.rawEquation.toCharArray()) {
                if (MNumber.isDigit(c) || isOperation(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOperation(char c) {
        for (char op : operations) {
            if (op == c) return true;
        }
        return false;
    }
}
