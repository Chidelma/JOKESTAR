package com.jokecompany;

import java.io.*;
import java.util.*;

public class Prompter {

    // Constructor
    public Prompter() {}

    /**
     * Asks the user for input and returns the answer.
     * 
     * @param prompt The prompt to display to the user.
     * @param allowedAnswers The allowed answers.
     * @return The answer.
     * @throws Exception
     */
    public String ask(String prompt, String[] allowedAnswers) throws Exception {

        String answer = "";

        System.out.println(prompt);

        String line = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {

            line = reader.readLine();

            System.out.println(line);

            // If the answer is in the allowed answers, set the answer and break the loop
            if (Arrays.stream(allowedAnswers).anyMatch(line::equals)) {
                answer = line.toLowerCase();
                break;
            }

            System.out.println("\nInvalid answer. Please try again. ");

        } while (!Arrays.stream(allowedAnswers).anyMatch(line::equals));

        return answer;
    }

    /**
     * Formats the results.
     * 
     * @param results The results to format.
     */
    public static void formatResults(String[] results) {

        // Print each result with a number
        for (int i = 0; i < results.length; i++) {
            System.out.println(i + 1 + ". " + results[i]);
        }
    }
}