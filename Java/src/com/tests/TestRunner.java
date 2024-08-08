package com.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    
    public static void main(String[] args) {
        
        Result feedResult = JUnitCore.runClasses(FeedTest.class);
        
        for (Failure failure : feedResult.getFailures()) {
            System.out.println(failure.toString());
        }
        
        System.out.println(feedResult.wasSuccessful());

        Result prompterResult = JUnitCore.runClasses(PrompterTest.class);
        
        for (Failure failure : prompterResult.getFailures()) {
            System.out.println(failure.toString());
        }
        
        System.out.println(prompterResult.wasSuccessful());
    }
}
