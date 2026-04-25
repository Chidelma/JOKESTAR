package com.jokecompany;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class Feed {

    private static String jokeURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke";
    private static String jokeCategoryURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke_category";

    public Feed() {}

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

            JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
            String joke = jsonObject.get("value").getAsString();

            if(!Arrays.stream(jokes).anyMatch(joke::equals)) {
                jokes[i] = joke;
                i++;
            }
            
        } while (i < total);

        return jokes;
    }

    public String[] getCategories() throws URISyntaxException, IOException, InterruptedException {
        JsonArray carArray = JsonParser.parseString(invokeURL(Feed.jokeCategoryURL, null)).getAsJsonArray();

        String[] categories = new String[carArray.size()];

        for (int i = 0; i < carArray.size(); i++) {
            categories[i] = carArray.get(i).getAsString();
        }

        return categories;
    }
}
