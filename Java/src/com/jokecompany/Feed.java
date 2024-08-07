package com.jokecompany;

import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class Feed {

    private static String invokeURL(String url, HashMap<String, String> params) throws URISyntaxException, IOException, InterruptedException {

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

    public static String[] getRandomJokes(String url, int total, String category) throws URISyntaxException, IOException, InterruptedException {

        String[] jokes = new String[total];

        int i = 0;

        do { 

            String data = "";
            
            if(category != null) {
                HashMap<String, String> params = new HashMap<>();
                params.put("category", category);
                data = invokeURL(url, params);
            } else {
                data = invokeURL(url, null);
            }

            var jsonObject = new JsonParser().parse(data).getAsJsonObject();
            String joke = jsonObject.get("value").getAsString();

            if(!Arrays.stream(jokes).anyMatch(joke::equals)) {
                jokes[i] = joke;
                i++;
            }
            
        } while (i < total);

        return jokes;
    }

    public static String[] getCategories(String url) throws URISyntaxException, IOException, InterruptedException {

        var carArray = new JsonParser().parse(invokeURL(url, null)).getAsJsonArray();

        String[] categories = new String[carArray.size()];

        for (int i = 0; i < carArray.size(); i++) {
            categories[i] = carArray.get(i).getAsString();
        }

        return categories;
    }
}