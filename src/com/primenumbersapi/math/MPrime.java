package com.primenumbersapi.math;

import com.primenumbersapi.api.Prime;
import com.primenumbersapi.exception.InvalidDigitException;

public class MPrime extends MNumber implements Prime {

    // Represents a prime.
    protected MPrime(String val) throws InvalidDigitException {
        super(val);

        /*
        if (!isPrime(val))
            throw new InvalidNumberException();
         */
    }
}
