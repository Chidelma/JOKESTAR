package com.jokecompany;

import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class Feed {

    // URLs for the Joke API
    private static String jokeURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke";
    private static String jokeCategoryURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke_category";

    // Constructor
    public Feed() {}

    /**
     * Invokes the URL and returns the response as a string.
     * 
     * @param url The URL to invoke.
     * @param params The parameters to pass to the URL.
     * @return The response as a string.
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    private String invokeURL(String url, HashMap<String, String> params) throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        if (params != null && !params.isEmpty()) {
            url += "?";
            ArrayList<String> paramString = new ArrayList<>();
            for (var entry : params.entrySet()) {
                paramString.add(entry.getKey() + "=" + entry.getValue());
            }
            url += String.join("&", paramString);
        }

        URI uri = new URI(url);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    /**
     * Gets a random joke from the Joke API.
     * 
     * @param url The URL to invoke.
     * @param total The number of jokes to get.
     * @param category The category of jokes to get.
     * @return An array of jokes.
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String[] getRandomJokes(int total, String category) throws URISyntaxException, IOException, InterruptedException {

        String[] jokes = new String[total];

        int i = 0;

        do { 

            String data = "";
            
            if(category != null) {
                HashMap<String, String> params = new HashMap<>();
                params.put("category", category);
                data = invokeURL(Feed.jokeURL, params);
            } else {
                data = invokeURL(Feed.jokeURL, null);
            }

            var jsonObject = new JsonParser().parse(data).getAsJsonObject();
            String joke = jsonObject.get("value").getAsString();

            // If the joke is not already in the array, add it
            if(!Arrays.stream(jokes).anyMatch(joke::equals)) {
                jokes[i] = joke;
                i++;
            }
            
        } while (i < total);

        return jokes;
    }

    /**
     * Gets a list of categories from the Joke API.
     * 
     * @param url The URL to invoke.
     * @return An array of categories.
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String[] getCategories() throws URISyntaxException, IOException, InterruptedException {

        var carArray = new JsonParser().parse(invokeURL(Feed.jokeCategoryURL, null)).getAsJsonArray();

        String[] categories = new String[carArray.size()];

        for (int i = 0; i < carArray.size(); i++) {
            categories[i] = carArray.get(i).getAsString();
        }

        return categories;
    }
}