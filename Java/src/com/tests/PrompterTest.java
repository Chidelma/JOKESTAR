package com.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;

import com.jokecompany.Prompter;

public class PrompterTest {

    private Prompter prompter = new Prompter();

    @Test
    public void testAsk() throws Exception {

        BufferedReader reader = mock(BufferedReader.class);
        
        when(reader.readLine()).thenReturn("?");

        assertEquals("?", prompter.ask("Press ? to get instructions: ", new String[] { "?" }));
    }
}