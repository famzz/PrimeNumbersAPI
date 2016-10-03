package com.primenumbersapi;

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
}
