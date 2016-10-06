package com.primenumbersapi.manager;

import java.security.InvalidParameterException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private Connection connection;
    private String table;

    public DatabaseManager(String url, String table) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.table = table;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return this.table;
    }

    /**
     * Check if the integer n is in the currently set table under column.
     *
     * @param column The column to check.
     * @param n The integer to check.
     * @return True or false, depending on whether or not n is in the table.
     */
    public boolean isIntegerInTable(String column, int n) {
        PreparedStatement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT " + column + " FROM " + this.table + " WHERE " +
                    column + " = ?;");
            statement.setInt(1, n);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet != null && resultSet.next()) {
                try {
                    if (resultSet.getInt(column) == n) {
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the row number of n.
     * @param column The column to check.
     * @param n The number to acquire the row number for.
     * @return The row number, or 0 if the number is not in the table.
     */
    public int getRow(String column, int n) {
        if (!isIntegerInTable(column, n)) {
            return 0;
        }

        int row = 1;
        for (int i = 0; i < n; i++) {
            if (isIntegerInTable(column, i)) {
                row += 1;
            }
        }

        return row;
    }

    /**
     * Get the values in the row between start and end in column. Assumes that the first row is row number 1 (so not a
     * 0-based numbering system).
     * @param column The column to get values from.
     * @param start The row to start at. Must be greater than 0.
     * @param end The row to end at. Must be greater than the start row and must be greater than 1.
     * @return List of values obtained. Or an empty list if no values exist between the specified start and end points.
     * @throws InvalidParameterException if start row is greater than 0.
     * @throws InvalidParameterException if start row is greater than the end row.
     * @throws InvalidParameterException if end row is less than 2.
     * @throws InvalidParameterException if the start row is greater than 1000000.
     */
    public List<Integer> getValuesInRange(String column, int start, int end) {
        if (start < 1) {
            throw new InvalidParameterException("You cannot have a row number that is less than 1!");
        }

        if (end < 2) {
            throw new InvalidParameterException("Your end row cannot be less than 2!");
        }

        if (end < start) {
            throw new InvalidParameterException("Your start row cannot be greater than your end row!");
        }

        if (start > 1000000) {
            throw new InvalidParameterException("Currently, the PrimeNumbersAPI only supports the first 1 million primes.");
        }

        int range = (end + 1) - start;

        PreparedStatement statement;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + this.table + " LIMIT " + String.valueOf(start - 1)
                    + " , " + String.valueOf(range) + ";");
            resultSet = statement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        List<Integer> values = new ArrayList<>();
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    int value = resultSet.getInt(column);
                    values.add(value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return values;
    }

    /**
     * Get the value in the table at the column "column" in row number "row". Assumes that the first row is row 1 (so not
     * a 0-based numbering system).
     * @param column The column to search through.
     * @param row The row to select.
     * @return The value.
     * @throws InvalidParameterException if the row number is less than 1.
     * @throws InvalidParameterException if the row number is greater than 1000000.
     */
    public int getValue(String column, int row) {
        if (row < 1) {
            throw new InvalidParameterException("Your row number can't be less than 1!");
        }

        if (row > 1000000) {
            throw new InvalidParameterException("Currently, the PrimeNumbersAPI only supports the first 1 million primes.");
        }

        row -= 1;
        String range = "1";

        PreparedStatement statement;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + this.table + " LIMIT " + String.valueOf(row)
                    + " , " + range + ";");
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet != null) {
                if (resultSet.next()) {
                    return resultSet.getInt(column);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
