package com.primenumbersapi.math;

import com.primenumbersapi.api.Number;
import com.primenumbersapi.exception.InvalidDigitException;

import java.util.Arrays;
import java.util.List;

public class MNumber implements Number {

    // Easier to manipulate a 50 character string than a 50 digit number.
    private String raw;
    private static final List<Integer> even = Arrays.asList(2, 4, 6, 8, 0);

    protected MNumber(String val) throws InvalidDigitException {
        for (char c : val.toCharArray()) {
            if (!isDigit(c)) {
                throw new InvalidDigitException();
            }
        }
        this.raw = val;
    }

    @Override
    public boolean isEven() {
        //TODO: Assumes we don't have a decimal.
        char digit = raw.charAt(raw.length() - 1);
        return even.contains((int) digit);
    }

    @Override
    public boolean isOdd() {
        //TODO: Assume we don't have a decimal.
        return !isEven();
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    protected String getRawValue() {
        return this.raw;
    }

    private void setRawValue(String raw) {
        this.raw = raw;
    }
}
