package com.primenumbersapi.math;

import com.primenumbersapi.api.Prime;
import com.primenumbersapi.exception.InvalidDigitException;

public class MPrime extends MNumber implements Prime {

    // Represents a prime.
    protected MPrime(String val) throws InvalidDigitException {
        super(val);

        // MPrime will ALWAYS represent a prime. Or things will go wrong.
        /*
        if (!isPrime(val))
            throw new InvalidNumberException();
         */
    }

    // If you're REALLY sure that it's a prime, and don't want to waste (potentially) lots of processing power.
    // May come in useful.
    private MPrime(String val, boolean force) throws InvalidDigitException {
        super(val);
        if (!force) {
            /*
        if (!isPrime(val))
            throw new InvalidNumberException();
         */
        }
    }
}
