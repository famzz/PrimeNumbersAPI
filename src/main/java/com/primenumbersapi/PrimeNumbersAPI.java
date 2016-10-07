package com.primenumbersapi;

import com.primenumbersapi.api.Number;
import com.primenumbersapi.api.Polynomial;
import com.primenumbersapi.api.Prime;
import com.primenumbersapi.math.MathFactory;

import java.util.List;

public class PrimeNumbersAPI extends MathFactory {

    private static PrimeNumbersAPI instance;

    private PrimeNumbersAPI() {
        super();
    }
    
    public static PrimeNumbersAPI getInstance() {
        //TODO: Setup.
        if (instance == null) {
            return new PrimeNumbersAPI();
        }
        else return instance;
    }

    public Prime getPrime(String number) {
        return this.getMPrime(number);
    }

    public Number getNumber(String number) {
        return this.getMNumber(number);
    }

    public Polynomial getPolynomial(String equation) {
        return this.getMPolynomial(equation);
    }

    public List<Prime> getPrimesInRange(Number min, Number max) {
        return null;
    }

}
