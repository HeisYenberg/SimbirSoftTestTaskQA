package com.simbirsoft.bankingproject.utils;

import java.time.LocalDate;

public class Fibonacci {
    public static Integer getFibonacci() {
        LocalDate date = LocalDate.now();
        int dayOfMonth = date.getDayOfMonth();
        int a = 1;
        int b = 1;
        int result = 1;
        for (int i = 3; i <= dayOfMonth; ++i) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }
}
