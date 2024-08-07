package com.jokecompany;

import java.io.*;
import java.util.*;

public class Prompter {

    public static String ask(String prompt, String[] allowedAnswers) throws Exception {

        String answer = "";

        System.out.println(prompt);

        String line = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {

            line = reader.readLine();

            if (Arrays.stream(allowedAnswers).anyMatch(line::equals)) {
                answer = line.toLowerCase();
                break;
            }

            System.out.println("\nInvalid answer. Please try again. ");

        } while (!Arrays.stream(allowedAnswers).anyMatch(line::equals));

        return answer;
    }

    public static void formatResults(String[] results) {

        for (int i = 0; i < results.length; i++) {
            System.out.println(i + 1 + ". " + results[i]);
        }
    }
}