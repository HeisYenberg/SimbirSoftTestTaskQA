package com.simbirsoft.bankingproject.utils;

import io.qameta.allure.Attachment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class CSVWriter {
    private static final String TRANSACTIONS_CSV = "target/transactions.csv";

    @Attachment(value = "Transaction History", type = "text/csv")
    public static String writeCSV(List<String[]> transactions) {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_CSV)) {
            StringJoiner joiner = new StringJoiner("\n");
            for (String[] transaction : transactions) {
                joiner.add(transaction[0] + "," + transaction[1] + "," +
                        transaction[2]);
            }
            writer.write(joiner.toString());
            return joiner.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
