package com.primenumbersapi;

import com.primenumbersapi.api.Number;
import com.primenumbersapi.api.Polynomial;
import com.primenumbersapi.api.Prime;
import com.primenumbersapi.math.MathFactory;

import java.util.List;

public class PrimeNumbersAPI {

    private MathFactory factory;
    private PrimeNumbersAPI instance;

    private PrimeNumbersAPI() {
        this.factory = new MathFactory();
    }
    
    public static PrimeNumbersAPI getInstance() {
        //TODO: Setup.
        if (instance == null) {
            return new PrimeNumbersAPI();
        }
        else return instance;
    }

    public Prime getPrime(String number) {
        return factory.getPrime(number);
    }

    public Number getNumber(String number) {
        return factory.getNumber(number);
    }

    public Polynomial getPolynomial(String equation) {
        return factory.getPolynomial(equation);
    }

    public List<Prime> getPrimesInRange(Number min, Number max) {
        return null;
    }

}
