package com.tests;

import com.jokecompany.Feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class FeedTest {

    private Feed feed = new Feed();

    @Test
    public void testGetRandomJokes() throws Exception {
        String[] jokes = feed.getRandomJokes(5, null);
        assertEquals(5, jokes.length);
    }

    @Test
    public void testGetRandomJokesWithCategory() throws Exception {
        String[] categories = feed.getCategories();
        assertTrue(categories.length > 0);
        
        String[] jokes = feed.getRandomJokes(3, categories[0]);
        assertEquals(3, jokes.length);
    }

    @Test
    public void testGetJokeCategories() throws Exception {
        String[] categories = feed.getCategories();
        assertNotNull(categories);
        assertTrue(categories.length > 0);
    }
}
