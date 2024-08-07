package com.jokecompany;


public class Main {

    static String jokeURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke";
    static String jokeCategoryURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke_category";

    public static void main(String[] args) throws Exception {
        
       Prompter.ask("Press ? to get instructions: ", new String[] { "?" });

        while(true) {                
            
            String answer = Prompter.ask("\nPress c to get categories.\nPress r to get random jokes: ", new String[] { "c", "r" });
            
            if(answer.equals("c")) {
                
                Prompter.formatResults(Feed.getCategories(Main.jokeCategoryURL));
            }
            
            if(answer.equals("r")) {
                
                String category = Prompter.ask("\nWant to specify a category? y/n: ", new String[] { "y", "n" });
                
                if(category.equals("y")) {
                    
                    System.out.println("\nHere are the categories: ");

                    String[] categories = Feed.getCategories(Main.jokeCategoryURL);
                    
                    Prompter.formatResults(categories);
                    
                    String categoryName = Prompter.ask("\nEnter a category: ", categories);                        
                    
                    int number = Integer.parseInt(Prompter.ask("\nHow many jokes do you want? (1-9): ", new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
                    
                    Prompter.formatResults(Feed.getRandomJokes(Main.jokeURL, number, categoryName));
                }
                else {
                    
                    int number = Integer.parseInt(Prompter.ask("\nHow many jokes do you want? (1-9): ", new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
                    
                    Prompter.formatResults(Feed.getRandomJokes(Main.jokeURL, number, null));
                }
            }
        }
    }
}