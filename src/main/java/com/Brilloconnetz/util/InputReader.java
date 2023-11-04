package com.Brilloconnetz.util;

import java.util.Scanner;

public class InputReader {

    private static Scanner scanner;

    private InputReader() {

    }

    public static Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        return scanner;
    }
}
