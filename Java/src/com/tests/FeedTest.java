package com.tests;

import com.jokecompany.Feed;

import org.junit.Test;

import static org.junit.Assert.*;

public class FeedTest {

    private static String jokeURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke";
    private static String jokeCategoryURL = "https://us-central1-geotab-interviews.cloudfunctions.net/joke_category";

    private Feed feed = new Feed();

    @Test
    public void testGetRandomJokes() throws Exception {

        String[] jokes = feed.getRandomJokes(FeedTest.jokeURL, 5, null);

        assertEquals(5, jokes.length);
    }

    @Test
    public void testGetRandomJokesWithCategory() throws Exception {

        String[] categories = feed.getCategories(FeedTest.jokeCategoryURL);

        String[] jokes = feed.getRandomJokes(FeedTest.jokeURL, 3, categories[0]);

        assertEquals(3, jokes.length);
    }

    @Test
    public void testGetJokeCategories() throws Exception {

        String[] categories = feed.getCategories(FeedTest.jokeCategoryURL);

        assertNotNull(categories);
    }
}