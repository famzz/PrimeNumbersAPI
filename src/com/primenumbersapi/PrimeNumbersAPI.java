package com.primenumbersapi;

import com.primenumbersapi.api.Prime;

import java.util.List;

public class PrimeNumbersAPI {
    
    private PrimeNumbersAPI() {
        // Example code showing how to initialise the database manager.
        // String path = new File("src\\resources\\primes.db").getAbsolutePath();
        // DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:" + path, "primes");
    }
    
    public static PrimeNumbersAPI getInstance() {
        //TODO: Setup.
        return new PrimeNumbersAPI();
    }

    public Prime getPrime(String number) {
        return null;
    }

    public Number getNumber(String number) {
        return null;
    }

    public List<Prime> getPrimesInRange(Number min, Number max) {
        return null;
    }
}
