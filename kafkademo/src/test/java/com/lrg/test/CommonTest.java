package com.lrg.test;

import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonTest {
    @Test
    public void test1() {
        CompletionService completionService = new ExecutorCompletionService<>(Executors.newFixedThreadPool(3));

        completionService.submit(()->{
            Thread.sleep(5000);
            return null;
        });
    }
}
