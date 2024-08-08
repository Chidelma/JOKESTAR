package com.jokecompany;


public class Main {
    public static void main(String[] args) throws Exception {

        Prompter prompter = new Prompter();

        Feed feed = new Feed();
        
        prompter.ask("Press ? to get instructions: ", new String[] { "?" });

        while(true) {                
            
            String answer = prompter.ask("\nPress c to get categories.\nPress r to get random jokes: ", new String[] { "c", "r" });
            
            if(answer.equals("c")) {
                
                Prompter.formatResults(feed.getCategories());
            }
            
            if(answer.equals("r")) {
                
                String category = prompter.ask("\nWant to specify a category? y/n: ", new String[] { "y", "n" });
                
                if(category.equals("y")) {
                    
                    System.out.println("\nHere are the categories: ");

                    String[] categories = feed.getCategories();
                    
                    Prompter.formatResults(categories);
                    
                    String categoryName = prompter.ask("\nEnter a category: ", categories);                        
                    
                    int number = Integer.parseInt(prompter.ask("\nHow many jokes do you want? (1-9): ", new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
                    
                    Prompter.formatResults(feed.getRandomJokes(number, categoryName));
                }
                else {
                    
                    int number = Integer.parseInt(prompter.ask("\nHow many jokes do you want? (1-9): ", new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
                    
                    Prompter.formatResults(feed.getRandomJokes(number, null));
                }
            }
        }
    }
}